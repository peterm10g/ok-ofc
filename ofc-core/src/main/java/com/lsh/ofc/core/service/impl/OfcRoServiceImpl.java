package com.lsh.ofc.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.core.dao.OfcRoDetailDAO;
import com.lsh.ofc.core.dao.OfcRoHeadDAO;
import com.lsh.ofc.core.entity.OfcRoDetail;
import com.lsh.ofc.core.entity.OfcRoHead;
import com.lsh.ofc.core.enums.BillType;
import com.lsh.ofc.core.enums.FulfillChannel;
import com.lsh.ofc.core.enums.FulfillWms;
import com.lsh.ofc.core.enums.OfcOperateEnum;
import com.lsh.ofc.core.enums.Region;
import com.lsh.ofc.core.enums.RoStatus;
import com.lsh.ofc.core.enums.SupplierOrg;
import com.lsh.ofc.core.enums.Valid;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.proxy.service.WumartBasicService;
import com.lsh.ofc.core.proxy.service.WumartOfcServiceProxy;
import com.lsh.ofc.core.proxy.service.WumartSapServiceProxy;
import com.lsh.ofc.core.service.OfcBillService;
import com.lsh.ofc.core.service.OfcOperateLogService;
import com.lsh.ofc.core.service.OfcRoCreateService;
import com.lsh.ofc.core.service.OfcRoService;
import com.lsh.ofc.core.util.OFCUtils;
import com.lsh.ofc.proxy.context.WumartBasicContext;
import com.lsh.ofc.proxy.model.ObdHead;
import com.lsh.ofc.proxy.service.WmsServiceProxy;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class OfcRoServiceImpl implements OfcRoService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private OfcRoHeadDAO ofcRoHeadDAO;

    @Autowired
    private OfcRoDetailDAO ofcRoDetailDAO;

    @Autowired
    private OfcRoCreateService ofcRoCreateService;

    @Autowired
    private OfcBillService ofcBillService;

    @Autowired
    private WmsServiceProxy wmsServiceProxy;

    @Autowired
    private WumartSapServiceProxy wumartSapServiceProxy;

    @Autowired
    private WumartOfcServiceProxy wumartOfcServiceProxy;

    @Autowired
    private OfcOperateLogService ofcOperateLogService;

    @Autowired
    private WumartBasicService wumartBasicService;

    @Override
    public int count(OfcRoHead filter) throws BusinessException {
        return this.ofcRoHeadDAO.count(filter);
    }

    @Override
    public List<OfcRoHead> findList(OfcRoHead filter, boolean fillDetails) throws BusinessException {
        List<OfcRoHead> ros = this.ofcRoHeadDAO.findList(filter);
        if (CollectionUtils.isEmpty(ros)) {
            return Collections.emptyList();
        }
        for (OfcRoHead ro : ros) {
            OfcRoDetail param = new OfcRoDetail();
            param.setRoBillCode(ro.getRoBillCode());
            if (fillDetails) {
                ro.setDetails(this.ofcRoDetailDAO.findList(param));
            }
        }
        return ros;
    }

    @Transactional
    @Override
    public int insert(List<OfcRoHead> ros) throws BusinessException {
        if (CollectionUtils.isEmpty(ros)) {
            return 0;
        }

        int cnt = 0;
        boolean isAllConsign = true;
        int ts = OFCUtils.currentTime();
        for (OfcRoHead ro : ros) {
            if (ro == null) {
                continue;
            }
            List<OfcRoDetail> details = ro.getDetails();
            if (CollectionUtils.isEmpty(details)) {
                continue;
            }
            if (SupplierOrg.isConsign(ro.getSupplierOrg()) && !FulfillWms.LSH.getValue().equals(ro.getFulfillWms())) { //TODO:目前非WMS履约的寄售不提交
                ro.setRoStatus(RoStatus.IGNORED.getValue());
            } else {
                isAllConsign = false;
            }
            //TODO: 物美SAP限制最多20位，目前一单中相同货主不会拆成两单
            String billCode = new StringBuilder(20).append(ro.getReturnCode()).append(ro.getSupplierOrg()).toString();
            ro.setRoBillCode(billCode);
            ro.setCreateTime(ts);
            ro.setUpdateTime(ts);
            ro.setValid(Valid.enable.getValue());
            cnt += this.ofcRoHeadDAO.insert(ro);
            for (OfcRoDetail detail : ro.getDetails()) {
                detail.setRoBillCode(billCode);
                detail.setCreateTime(ts);
                detail.setUpdateTime(ts);
                this.ofcRoDetailDAO.insert(detail);
            }
            //记录操作日志
            this.ofcOperateLogService.insert(billCode, BillType.RO, OfcOperateEnum.RO_UNCREATED, ro.getTotalSkuReturnQty().toString());
        }
        if (isAllConsign) {
            throw EC.ERROR.exception("没有非寄售品，无法提交物美SO");
        }
        return cnt;
    }

    @Transactional
    @Override
    public int updateStatus(OfcRoHead filter, OfcRoHead entity) throws BusinessException {
        if (filter == null || entity == null) {
            return 0;
        }
        if (filter.getId() == null && !StringUtils.hasText(filter.getRoBillCode())) {
            return 0;
        }
        RoStatus expectStatus = RoStatus.valueOf(filter.getRoStatus());
        RoStatus updateStatus = RoStatus.valueOf(entity.getRoStatus());
        if (expectStatus == null || updateStatus == null) {
            return 0;
        }
        if (expectStatus.equals(updateStatus)) {
            return 0;
        }
        int ret = this.ofcRoHeadDAO.updateByFilter(entity, filter);
        if (ret < 1) {
            return ret;
        }
        if (!CollectionUtils.isEmpty(entity.getDetails())) {
            for (OfcRoDetail detail : entity.getDetails()) {
                this.ofcRoDetailDAO.update(detail);
            }
        }

        OfcRoHead param = new OfcRoHead();
        param.setId(filter.getId());
        param.setRoBillCode(filter.getRoBillCode());
        entity = this.ofcRoHeadDAO.findOne(param);
        switch (updateStatus) {
            case CREATED:
                this.ofcBillService.insert(entity);
                //记录操作日志
                this.ofcOperateLogService.insert(entity.getRoBillCode(), BillType.RO, OfcOperateEnum.RO_CREATED, entity.getRoCode());
                break;
            case CREATING:
                //记录操作日志
                this.ofcOperateLogService.insert(entity.getRoBillCode(), BillType.RO, OfcOperateEnum.RO_CREATING, null);
                break;
            case CREATE_FAIL:
                //记录操作日志
                this.ofcOperateLogService.insert(entity.getRoBillCode(), BillType.RO, OfcOperateEnum.RO_CREATE_FAIL, null);
                break;
            case COMPLETED:
                //记录操作日志
                this.ofcOperateLogService.insert(entity.getRoBillCode(), BillType.RO, OfcOperateEnum.RO_COMPLETED, null);
                break;
            default:
                break;
        }
        return ret;
    }

    @Override
    public Integer refreshRoStatus(OfcRoHead ro) throws BusinessException {
        Integer roStatus = ro.getRoStatus();
        Integer fulfillChannel = ro.getFulfillChannel();
        Integer fulfillWms = ro.getFulfillWms();
        boolean completed = false;
        try {
            if (FulfillChannel.WUMART_OFC.getValue().equals(fulfillChannel)) { //通过物美OFC履约
                boolean created = RoStatus.CREATED.getValue().equals(roStatus);
                if (RoStatus.CREATING.getValue().equals(roStatus) || (created && FulfillWms.Wumart.getValue().equals(fulfillWms))) {
                    completed = this.isReturnedToWumart(ro.getSoBillCode(), ro.getId(), created, ro.getRegionCode(), ro.getSupplierDc());
                    if (completed) {
                        roStatus = RoStatus.COMPLETED.getValue();
                    } else {
                        roStatus = this.ofcRoHeadDAO.get(ro.getId()).getRoStatus();
                    }
                }
                if (RoStatus.CREATED.getValue().equals(roStatus) && FulfillWms.LSH.getValue().equals(fulfillWms)) {
//                    Boolean isBeiJing = ro.getRegionCode().equals(Region.Beijing.getCode())
//                            || ro.getRegionCode().equals(Region.BeijingKA.getCode())
//                            || ro.getRegionCode().equals(Region.Beijingcg.getCode());
                    String wmsPath = this.wumartBasicService.getWmsPath(WumartBasicContext.buildContext(ro.getRegionCode(), ro.getSupplierDc()));
                    completed = this.wmsServiceProxy.isReturnCompleted(ro.getRoCode(), wmsPath);
                    if (completed) {
                        roStatus = RoStatus.COMPLETED.getValue();
                    }
                }
            } else if (FulfillChannel.WUMART_SAP.getValue().equals(fulfillChannel)) { //通过物美SAP履约
                if (!RoStatus.CREATED.getValue().equals(roStatus)) { //仅创建完成的需要刷新状态
                    return roStatus;
                }
                boolean ret = this.isReturnedToWumart(ro.getRoCode(), ro.getRegionCode());
                if (ret) {
                    roStatus = RoStatus.COMPLETED.getValue();
                }
            } else if (FulfillChannel.LSH_WMS.getValue().equals(fulfillChannel)) {//通过链商WMS履约
                if (!RoStatus.CREATED.getValue().equals(roStatus)) { //仅创建完成的需要刷新状态
                    return roStatus;
                }
//                Boolean isBeiJing = ro.getRegionCode().equals(Region.Beijing.getCode())
//                        || ro.getRegionCode().equals(Region.BeijingKA.getCode())
//                        || ro.getRegionCode().equals(Region.Beijingcg.getCode());
                String wmsPath = this.wumartBasicService.getWmsPath(WumartBasicContext.buildContext(ro.getRegionCode(), ro.getSupplierDc()));
                completed = this.wmsServiceProxy.isReturnCompleted(ro.getRoCode(), wmsPath);
                if (completed) {
                    roStatus = RoStatus.COMPLETED.getValue();
                }
            }
        } catch (Throwable e) {
            if (e instanceof BusinessException) {
                throw e;
            }
            logger.info("【" + ro.getReturnCode() + "】【" + ro.getRoBillCode() + "】查询返仓结果异常...RO单号=" + ro.getRoCode() + " ..." + e.getMessage(), e);
            throw new BusinessException(EC.ERROR.getCode(), e.getMessage(), e);
        }
        if (completed) {
            OfcRoHead filter = new OfcRoHead();
            filter.setId(ro.getId());
            filter.setRoBillCode(ro.getRoBillCode());
            filter.setRoStatus(RoStatus.CREATED.getValue());
            OfcRoHead update = new OfcRoHead();
            update.setRoStatus(RoStatus.COMPLETED.getValue());
            this.updateStatus(filter, update);
        }
        return roStatus;
    }

    @Override
    public List<OfcRoHead> fetchRoByStatusAndTimeStamp(Set<RoStatus> roStatusSet, int timeInterval, long offset, int size) {
        if (size <= 0) {
            return Collections.emptyList();
        }
        List<Integer> statuses = new ArrayList<>(roStatusSet.size());
        for (RoStatus roStatus : roStatusSet) {
            statuses.add(roStatus.getValue());
        }
        List<OfcRoHead> list = this.ofcRoHeadDAO.fetchRoByStatusAndTimeStamp(statuses, timeInterval, offset, size);
        return list;
    }

    /**
     * 物美返仓是否已完成（物美SAP）
     *
     * @param roCode
     * @param regionCode
     * @return
     * @throws BusinessException
     */
    private boolean isReturnedToWumart(String roCode, Integer regionCode) throws BusinessException {
        logger.info("查询物美返仓结果开始，RO单号=" + roCode);
        ObdHead ret = this.wumartSapServiceProxy.queryObdStatus4Ro(roCode, regionCode);
        logger.info("查询物美返仓结果完成，RO单号=" + roCode + ", ret=" + ret);
        if (ret == null || CollectionUtils.isEmpty(ret.getDetails())) {
            logger.info("退货OBD记录已存在！退货单号=" + roCode);
            return false;
        }
        return true;
    }

    /**
     * 物美返仓是否已完成（物美OFC）
     *
     * @param billCode
     * @param batchNo
     * @param created
     * @return
     * @throws BusinessException
     */
    private boolean isReturnedToWumart(String billCode, Long batchNo, boolean created, Integer regionCode, String supplierDc) throws BusinessException {
        logger.info("【物美OFC】查询物美返仓结果开始，单号=" + billCode + "，批次号=" + batchNo);
        String content = this.wumartOfcServiceProxy.queryMeipiOrder(WumartOfcServiceProxy.OrderType.RETURN, billCode, batchNo, WumartBasicContext.buildContext(regionCode, supplierDc));
        logger.info("【物美OFC】查询物美返仓结果开始，单号=" + billCode + "，批次号=" + batchNo + ", content=" + content);
        JSONObject json = JSON.parseObject(content);
        if (!created) {
            JSONObject soInfo = json.getJSONObject("soInfo");
            if (soInfo == null) {
                return false;
            }
            CommonResult<Boolean> ret = this.ofcRoCreateService.callback(soInfo);
            if (ret == null || !Boolean.TRUE.equals(ret.getData())) {
                return false;
            }
        }
        JSONObject obdInfo = json.getJSONObject("obdInfo");
        if (obdInfo == null) {
            return false;
        }
        if (CollectionUtils.isEmpty(obdInfo.getJSONArray("details"))) {
            return false;
        }
        return true;
    }
}
