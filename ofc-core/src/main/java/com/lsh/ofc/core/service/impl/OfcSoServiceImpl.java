package com.lsh.ofc.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.dao.OfcSoDetailDAO;
import com.lsh.ofc.core.dao.OfcSoHeadDAO;
import com.lsh.ofc.core.entity.OfcSoDetail;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.entity.OfcTask;
import com.lsh.ofc.core.enums.*;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.model.Costs;
import com.lsh.ofc.core.service.OfcBillService;
import com.lsh.ofc.core.service.OfcOperateLogService;
import com.lsh.ofc.core.service.OfcSoService;
import com.lsh.ofc.core.service.OfcTaskService;
import com.lsh.ofc.core.util.OFCUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
public class OfcSoServiceImpl implements OfcSoService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private OfcSoHeadDAO ofcSoHeadDAO;

    @Autowired
    private OfcSoDetailDAO ofcSoDetailDAO;

    @Autowired
    private OfcBillService ofcBillService;

    @Autowired
    private OfcOperateLogService ofcOperateLogService;

    @Autowired
    private OfcTaskService ofcTaskService;

    @Override
    public int count(OfcSoHead filter) throws BusinessException {
        if (filter.getValid() == null) {
            filter.setValid(Valid.enable.getValue());
        }
        return this.ofcSoHeadDAO.count(filter);
    }

    @Override
    public OfcSoHead findOne(OfcSoHead filter, boolean fillDetails) throws BusinessException {
        if (filter.getValid() == null) {
            filter.setValid(Valid.enable.getValue());
        }
        OfcSoHead so = this.ofcSoHeadDAO.findOne(filter);
        if (so == null) {
            return null;
        }
        if (fillDetails) {
            so.setDetails(this.findDtails(so.getSoBillCode()));
        }
        return so;
    }

    @Override
    public List<OfcSoHead> findList(OfcSoHead filter, boolean fillDetails) throws BusinessException {
        if (filter.getValid() == null) {
            filter.setValid(Valid.enable.getValue());
        }
        List<OfcSoHead> sos = this.ofcSoHeadDAO.findList(filter);
        if (CollectionUtils.isEmpty(sos)) {
            return Collections.emptyList();
        }
        if (fillDetails) {
            for (OfcSoHead so : sos) {
                so.setDetails(this.findDtails(so.getSoBillCode()));
            }
        }
        return sos;
    }

    @Override
    public List<OfcSoDetail> findDtails(String soBillCode) throws BusinessException {
        if (!StringUtils.hasLength(soBillCode)) {
            return Collections.emptyList();
        }
        OfcSoDetail param = new OfcSoDetail();
        param.setSoBillCode(soBillCode);
        return this.ofcSoDetailDAO.findList(param);
    }

    @Transactional
    @Override
    public int insert(List<OfcSoHead> sos) throws BusinessException {
        if (CollectionUtils.isEmpty(sos)) {
            return 0;
        }

        int cnt = 0;
        int ts = OFCUtils.currentTime();
        for (OfcSoHead so : sos) {
            if (so == null) {
                continue;
            }
            List<OfcSoDetail> details = so.getDetails();
            if (CollectionUtils.isEmpty(details)) {
                continue;
            }
            String billCode;
            if (FulfillWms.NONE.getValue().equals(so.getFulfillWms())) { //TODO: shit for higou
                billCode = so.getOrderCode().toString();
            } else { //TODO: 物美SAP限制最多20位，目前一单中相同货主不会拆成两单
                billCode = new StringBuilder(20).append(so.getOrderCode()).append(so.getSupplierOrg()).toString();
            }
            so.setSoBillCode(billCode);
            so.setCreateTime(ts);
            so.setUpdateTime(ts);
            so.setValid(Valid.enable.getValue());

            cnt += this.ofcSoHeadDAO.insert(so);
            for (OfcSoDetail detail : so.getDetails()) {
                detail.setSoBillCode(billCode);
                detail.setCreateTime(ts);
                detail.setUpdateTime(ts);
                this.ofcSoDetailDAO.insert(detail);
            }
            //记录操作日志
            this.ofcOperateLogService.insert(billCode, BillType.SO, OfcOperateEnum.SO_UNCREATED, so.getTotalSkuOrderQty().toString());
        }
        return cnt;
    }

    @Transactional
    @Override
    public int update4Create(OfcSoHead so, SoStatus expect, SoStatus update) throws BusinessException {
        if (!SoStatus.UNCREATED.equals(expect) && !SoStatus.CREATING.equals(expect) && !SoStatus.CREATE_FAIL.equals(expect)) {
            throw EC.ERROR.exception("【更新SO信息(创建)】参数错误！expectStatus" + expect);
        }
        if (!SoStatus.CREATED.equals(update) && !SoStatus.CREATING.equals(update) && !SoStatus.CREATE_FAIL.equals(update)) {
            throw EC.ERROR.exception("【更新SO信息(创建)】参数错误！updateStatus" + update);
        }
        Long id = so.getId();
        String billCode = so.getSoBillCode();
        if (id == null && billCode == null) {
            throw EC.ERROR.exception("【更新SO信息(创建)】参数错误！更新SO头主键为空！");
        }

        OfcSoHead filter = new OfcSoHead();
        filter.setId(id);
        filter.setSoBillCode(billCode);
        filter.setSoStatus(expect.getValue());
        so.setSoStatus(update.getValue());
        int ret = this.ofcSoHeadDAO.updateByFilter(so, filter);
        if (ret < 1) {
            return ret;
        }
        List<OfcSoDetail> details = so.getDetails();
        if (!CollectionUtils.isEmpty(details)) {
            for (OfcSoDetail detail : details) {
                if (detail.getId() == null) {
                    throw EC.ERROR.exception("【更新SO信息(创建)】参数错误！更新SO明细主键为空！");
                }
                this.ofcSoDetailDAO.update(detail);
            }
        }
        if (SoStatus.CREATED.equals(update)) {
            OfcSoHead param = new OfcSoHead();
            param.setId(id);
            OfcSoHead entity = this.findList(param, true).get(0);

            //插入合单并下发订单任务
            Long orderCode = entity.getOrderCode();
            OfcTask task = new OfcTask();
            task.setRefId(orderCode);
            task.setType(OfcTaskType.SO_MERGE.getValue());
            task.setStatus(OfcTaskStatus.NEW.getValue());
            if (this.ofcTaskService.countTask(task) <= 0) {
                logger.info("插入合单并下发订单任务。订单号=" + orderCode);
                task.setContent(orderCode.toString());
                this.ofcTaskService.addTask(task);
            }

            //插入OFC_BILL
            this.ofcBillService.insert(entity);
            //记录操作日志
            this.ofcOperateLogService.insert(billCode, BillType.SO, OfcOperateEnum.SO_CREATED, so.getSoCode() + ":" + so.getTotalSkuSupplyQty().toString());
        } else if (SoStatus.CREATING.equals(update)) {
            //记录操作日志
            this.ofcOperateLogService.insert(billCode, BillType.SO, OfcOperateEnum.SO_CREATING, null);
        } else if (SoStatus.CREATE_FAIL.equals(update)) {
            //记录操作日志
            this.ofcOperateLogService.insert(billCode, BillType.SO, OfcOperateEnum.SO_CREATE_FAIL, null);
        }
        return ret;
    }

    @Transactional
    @Override
    public int update4Deliver(String billCode, BigDecimal skuDeliverQty) throws BusinessException {
        if (billCode == null) {
            throw EC.ERROR.exception("【更新SO信息(发货)】参数错误！更新SO头主键为空！");
        }
        OfcSoHead entity = new OfcSoHead();
        entity.setTotalSkuDeliverQty(skuDeliverQty);
        entity.setSoStatus(SoStatus.DELIVERED.getValue());
        OfcSoHead filter = new OfcSoHead();
        filter.setSoBillCode(billCode);
        filter.setSoStatus(SoStatus.CREATED.getValue());
        int ret = this.ofcSoHeadDAO.updateByFilter(entity, filter);
        if (ret > 0) {
            //记录操作日志
            this.ofcOperateLogService.insert(billCode, BillType.SO, OfcOperateEnum.SO_DELIVERED, skuDeliverQty.toString());
        }
        return ret;
    }

    @Transactional
    @Override
    public BigDecimal update4Return(String billCode, Map<Long, BigDecimal> returnQtyMap) throws BusinessException {
        if (billCode == null) {
            throw EC.ERROR.exception("【更新SO信息(返仓)】参数错误！更新SO头主键为空！");
        }

        BigDecimal sum = BigDecimal.ZERO;
        for (Map.Entry<Long, BigDecimal> entry : returnQtyMap.entrySet()) {
            BigDecimal qty = entry.getValue();
            int ret = this.ofcSoDetailDAO.update4Return(billCode, entry.getKey(), qty);
            if (ret != 1) {
                throw EC.ERROR.exception("【更新SO信息(返仓)】更新SO明细返仓数量失败！单号=" + billCode + "，ID=" + entry.getKey() + "，QTY=" + qty);
            }
            sum = sum.add(qty);
        }
        int ret = this.ofcSoHeadDAO.update4Return(billCode, sum);
        if (ret != 1) {
            throw EC.ERROR.exception("【更新SO信息(返仓)】更新SO返仓数量失败！单号=" + billCode + "，QTY=" + sum);
        }
        //记录操作日志
        this.ofcOperateLogService.insert(billCode, BillType.SO, OfcOperateEnum.SO_RETURN, sum.toString());
        return sum;
    }

    @Override
    public List<OfcSoHead> fetchSoByStatusAndTimeStamp(Set<SoStatus> soStatusSet, int timeInterval, long offset, int size) {
        if (size <= 0) {
            return Collections.emptyList();
        }
        List<Integer> statuses = new ArrayList<>(soStatusSet.size());
        for (SoStatus soStatus : soStatusSet) {
            statuses.add(soStatus.getValue());
        }
        List<OfcSoHead> list = this.ofcSoHeadDAO.fetchSoByStatusAndTimeStamp(statuses, timeInterval, offset, size);
        return list;
    }

    @Transactional
    @Override
    public int updateByFilter(OfcSoHead update, OfcSoHead filter) throws BusinessException {
        return ofcSoHeadDAO.updateByFilter(update, filter);
    }

    @Override
    public Costs calcCost(String billCode, Map<String, BigDecimal> items) throws BusinessException {
        List<OfcSoDetail> details = this.findDtails(billCode);
        if (CollectionUtils.isEmpty(details)) {
            throw EC.SO_DETAILS_IS_EMPTY.exception("单号=" + billCode);
        }
        BigDecimal amountSum = BigDecimal.ZERO;
        BigDecimal ntAmountSum = BigDecimal.ZERO;
        for (OfcSoDetail detail : details) {
            String code = detail.getSkuSupplyCode();
            BigDecimal qty = items.get(code);
            if (qty == null) {
                continue;
            }
            if (qty.compareTo(BigDecimal.ZERO) == 0) {
                items.remove(code);
            }
            BigDecimal itAmount = detail.getSkuSupplyPrice().multiply(qty);
            BigDecimal ntAmount = itAmount.divide(BigDecimal.ONE.add(detail.getTaxRate()), 2, BigDecimal.ROUND_HALF_UP);
            amountSum = amountSum.add(itAmount);
            ntAmountSum = ntAmountSum.add(ntAmount);
        }
        amountSum = amountSum.setScale(2, BigDecimal.ROUND_HALF_UP);
        ntAmountSum = ntAmountSum.setScale(2, BigDecimal.ROUND_HALF_UP);
        return new Costs(amountSum, ntAmountSum);
    }
}
