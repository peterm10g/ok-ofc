package com.lsh.ofc.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.entity.*;
import com.lsh.ofc.core.enums.OfcTaskStatus;
import com.lsh.ofc.core.enums.OfcTaskType;
import com.lsh.ofc.core.enums.RoStatus;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.handler.CreateRoHandler;
import com.lsh.ofc.core.service.*;
import com.lsh.ofc.core.util.OFCUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class OfcRoCreateServiceImpl implements OfcRoCreateService {

    private final Logger logger = Logger.getLogger(this.getClass());

    private ExecutorService executor = Executors.newFixedThreadPool(20);

    @Autowired
    private OfcReturnService ofcReturnService;

    @Autowired
    private OfcRoService ofcRoService;

    @Autowired
    private OfcRoSplitService ofcRoSplitService;

    @Autowired
    private OfcSoService ofcSoService;

    @Autowired
    private OfcTaskService ofcTaskService;

    @Autowired
    private OfcCustomerService ofcCustomerService;

    @Autowired
    private ApplicationContext context;

    @Transactional
    @Override
    public List<OfcRoHead> prepare(OfcReturnHead bill) throws BusinessException {
        if (bill == null) {
            throw EC.ERROR.exception("入参订单信息为空！");
        }
        List<OfcReturnDetail> details = bill.getDetails();
        if (CollectionUtils.isEmpty(details)) {
            throw EC.ERROR.exception("入参订单明细为空！");
        }

        //订单拆分
        List<OfcRoHead> ros = this.ofcRoSplitService.split(bill);

        //插入订单信息
        this.ofcReturnService.insert(bill);

        //插入SO信息
        this.ofcRoService.insert(ros);

        //插入SO创建处理任务
        Long returnCode = bill.getReturnCode();
        OfcTask task = new OfcTask();
        task.setRefId(returnCode);
        task.setType(OfcTaskType.RO_CREATE.getValue());
        task.setStatus(OfcTaskStatus.NEW.getValue());
        task.setContent(returnCode.toString());
        this.ofcTaskService.addTask(task);
        return ros;
    }

    @Override
    public boolean process(Long returnCode) throws BusinessException {
        OfcRoHead filter = new OfcRoHead();
        filter.setReturnCode(returnCode);
        filter.setRoStatus(RoStatus.UNCREATED.getValue());
        List<OfcRoHead> ros = this.ofcRoService.findList(filter, true);
        if (CollectionUtils.isEmpty(ros)) {
            logger.warn("订单对应RO状态非初始化。返仓单号=" + returnCode);
            return true;
        }

        OfcCustomer customer = null;
        List<Future<Boolean>> futures = new ArrayList<>(ros.size());
        for (OfcRoHead ro : ros) {
            if (ro == null || CollectionUtils.isEmpty(ro.getDetails())) {
                continue;
            }
            if (customer == null) {
                customer = this.ofcCustomerService.getCustomer(ro.getAddressCode());
            }
            Future<Boolean> future = executor.submit(CreateRoHandler.newInstance(this.context, ro, customer));
            futures.add(future);
        }

        for (Future<Boolean> future : futures) {
            try {
                if (!future.get()) {
                    logger.error("创建RO失败，返仓单号=" + returnCode);
                    return false;
                }
            } catch (Exception e) {
                String msg = "创建RO异常，返仓单号=" + returnCode + "\n" + e.getMessage();
                logger.error(msg, e);
                throw new BusinessException(EC.ERROR.getCode(), msg, e);
            }
        }

//        //插入合单并下发订单任务
//        OfcTask task = new OfcTask();
//        task.setRefId(returnCode);
//        task.setType(OfcTaskType.RO_MERGE.getValue());
//        task.setStatus(OfcTaskStatus.NEW.getValue());
//        task.setContent(returnCode.toString());
//        this.ofcTaskService.addTask(task);
        return true;
    }


    /**
     * 创建RO回调
     *
     * @param data
     * @return
     * @throws BusinessException
     */
    @Override
    public CommonResult<Boolean> callback(JSONObject data) throws BusinessException {
        Long batchNo = data.getLong("batchNumber");
        if (batchNo == null || batchNo.longValue() <= 0) {
            throw EC.ERROR_PARAMS.exception("batchNumber[" + batchNo + "] is error! received content:" + data.toJSONString());
        }
        String soBillCode = data.getString("reqCode");
        if (!StringUtils.hasText(soBillCode) || "-1".equals(soBillCode)) {
            throw EC.ERROR_PARAMS.exception("reqCode[" + soBillCode + "] is black! received content:" + data.toJSONString());
        }

        //校验RO单ID是否正确
        OfcRoHead filter1 = new OfcRoHead();
        filter1.setId(batchNo);
        List<OfcRoHead> ros = this.ofcRoService.findList(filter1, false);
        if (CollectionUtils.isEmpty(ros)) {
            throw EC.RO_ORDER_NOT_EXIST.exception("RO[" + soBillCode + "]batchNumber[" + batchNo + "] is not match! received content:" + data.toJSONString());
        }

        //校验SO单单据号是否正确
        OfcSoHead filter2 = new OfcSoHead();
        filter2.setSoBillCode(soBillCode);
        if (this.ofcSoService.count(filter2) == 0) {
            throw EC.SO_ORDER_NOT_EXIST.exception("RO[" + soBillCode + "] is not match! received content:" + data.toJSONString());
        }

        OfcRoHead ro = ros.get(0);
        Integer roStatus = ro.getRoStatus();

        if (RoStatus.CREATED.getValue().equals(roStatus)) {
            String msg = "RO[" + soBillCode + "]create ro callback duplicate! received content:" + data.toJSONString();
            return CommonResult.success(true, msg);
        }

        if (!RoStatus.CREATING.getValue().equals(roStatus) && !RoStatus.CREATE_FAIL.getValue().equals(roStatus)) {
            throw EC.ERROR.exception("RO[" + soBillCode + "] status is incorrect! status:" + roStatus + ", received content:" + data.toJSONString());
        }

        OfcRoHead filter = new OfcRoHead();
        filter.setId(ro.getId());
        filter.setRoBillCode(ro.getRoBillCode());
        filter.setRoStatus(roStatus);

        OfcRoHead update = new OfcRoHead();
        String roCode = data.getString("soCode");
        if (!StringUtils.hasText(roCode) || "-1".equals(roCode)) {
            update.setRoStatus(RoStatus.CREATE_FAIL.getValue());
            int ret = this.ofcRoService.updateStatus(filter, update);
            String msg = "RO[" + soBillCode + "]create ro error! ret=" + ret + "... received content:" + data.toJSONString();
            logger.warn(msg);
            return CommonResult.error(msg, false);
        }

        JSONObject ext = JSON.parseObject(ro.getExt());
        ext.put(Constants.RO_H_REF_RO_CODE, data.get("temp3"));
        ext.put(Constants.RO_H_FULFILL_CREATE_TIME, OFCUtils.currentTime());
        update.setRoCode(roCode);
        update.setRoStatus(RoStatus.CREATED.getValue());
        update.setExt(ext.toJSONString());
        int ret = this.ofcRoService.updateStatus(filter, update);
        logger.info("RO[" + soBillCode + "]create ro callback success! ret=" + ret + "... received content:" + data.toJSONString());
        return CommonResult.success(true);
    }
}
