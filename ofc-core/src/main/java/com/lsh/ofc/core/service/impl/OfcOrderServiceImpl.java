package com.lsh.ofc.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.dao.OfcOrderDetailDAO;
import com.lsh.ofc.core.dao.OfcOrderHeadDAO;
import com.lsh.ofc.core.dao.OfcSoHeadDAO;
import com.lsh.ofc.core.entity.OfcOrderDetail;
import com.lsh.ofc.core.entity.OfcOrderHead;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.entity.OfcTask;
import com.lsh.ofc.core.enums.BillType;
import com.lsh.ofc.core.enums.FulfillChannel;
import com.lsh.ofc.core.enums.FulfillStatus;
import com.lsh.ofc.core.enums.OfcOperateEnum;
import com.lsh.ofc.core.enums.OfcTaskStatus;
import com.lsh.ofc.core.enums.OfcTaskType;
import com.lsh.ofc.core.enums.SoStatus;
import com.lsh.ofc.core.enums.Valid;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.handler.AsyncTaskHandler;
import com.lsh.ofc.core.handler.CancelOrderHandler;
import com.lsh.ofc.core.proxy.service.WumartBasicService;
import com.lsh.ofc.core.redis.RedisTemplate;
import com.lsh.ofc.core.service.OfcOperateLogService;
import com.lsh.ofc.core.service.OfcOrderService;
import com.lsh.ofc.core.service.OfcTaskService;
import com.lsh.ofc.proxy.context.WumartBasicContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by huangdong on 16/9/9.
 */
@Service
public class OfcOrderServiceImpl implements OfcOrderService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private OfcOrderHeadDAO ofcOrderHeadDAO;

    @Autowired
    private OfcOrderDetailDAO ofcOrderDetailDAO;

    @Autowired
    private OfcOperateLogService ofcOperateLogService;

    @Autowired
    private OfcTaskService ofcTaskService;

    @Autowired
    private OfcSoHeadDAO ofcSoHeadDAO;

    @Autowired
    private CancelOrderHandler cancelOrderHandler;

    @Autowired
    private AsyncTaskHandler asyncTaskHandler;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private WumartBasicService wumartBasicService;

    @Override
    public List<OfcOrderHead> findList(OfcOrderHead filter, boolean fillDetails) throws BusinessException {
        List<OfcOrderHead> bills = this.ofcOrderHeadDAO.findList(filter);
        if (CollectionUtils.isEmpty(bills)) {
            return Collections.emptyList();
        }
        if (fillDetails) {
            for (OfcOrderHead bill : bills) {
                OfcOrderDetail param = new OfcOrderDetail();
                param.setOrderCode(bill.getOrderCode());
                bill.setDetails(this.ofcOrderDetailDAO.findList(param));
            }
        }
        return bills;
    }

    @Override
    public int count(OfcOrderHead filter) throws BusinessException {
        return this.ofcOrderHeadDAO.count(filter);
    }

    @Transactional
    @Override
    public void create(OfcOrderHead order) throws BusinessException {
        if (order == null) {
            throw EC.ERROR.exception("入参订单信息为空！");
        }
        List<OfcOrderDetail> details = order.getDetails();
        if (CollectionUtils.isEmpty(details)) {
            throw EC.ERROR.exception("入参订单明细为空！");
        }

        //设置trans_time
        this.filltTransTime(order);

        int ts = (int) (System.currentTimeMillis() / 1000);
        order.setCreateTime(ts);
        order.setUpdateTime(ts);
        order.setValid(Valid.enable.getValue());
        this.ofcOrderHeadDAO.insert(order);
        for (OfcOrderDetail detail : details) {
            detail.setCreateTime(ts);
            detail.setUpdateTime(ts);
            this.ofcOrderDetailDAO.insert(detail);
        }
        //记录操作日志
        this.ofcOperateLogService.insert(order.getOrderCode().toString(), BillType.ORDER, OfcOperateEnum.ORDER_NEW, null);

        //插入SO创建处理任务
        Long orderCode = order.getOrderCode();
        OfcTask task = new OfcTask();
        task.setRefId(orderCode);
        task.setType(OfcTaskType.SO_CREATE.getValue());
        task.setStatus(OfcTaskStatus.NEW.getValue());
        task.setContent(orderCode.toString());
        this.ofcTaskService.addTask(task);
    }

    @Transactional
    @Override
    public void cancel(Long orderCode) throws BusinessException {
        String prefix = "[CancelOrder][OrderCode:" + orderCode + "]";

        logger.info(prefix + "[start]");
        if (orderCode == null) {
            String msg = "订单号为空！";
            logger.error(prefix + "[error][" + msg + "]");
            throw EC.ERROR.exception(msg);
        }

        String key = orderCode.toString();
        if (!this.redisTemplate.lock(key, 30)) {
            String msg = "订单正在处理.";
            logger.warn(prefix + "[warn][" + msg + "]");
            throw EC.ERROR.exception(msg);
        }

        try {
            //加载订单
            OfcOrderHead orderFilter = new OfcOrderHead();
            orderFilter.setOrderCode(orderCode);
            List<OfcOrderHead> orderList = this.findList(orderFilter, false);
            if (orderList == null || orderList.size() != 1) {
                throw EC.ERROR.exception("订单信息不存在，或者订单数量不唯一");
            }
            OfcOrderHead orderHead = orderList.get(0);

            this.validateOrderForCancel(orderHead);

            //加载So
            OfcSoHead soFilter = new OfcSoHead();
            soFilter.setOrderCode(orderCode);
            List<OfcSoHead> soList = ofcSoHeadDAO.findList(soFilter);
            if (CollectionUtils.isEmpty(soList)) {
                throw EC.ERROR.exception("So信息为空");
            }

            this.validateSoForCancel(soList);

            //履约渠道
            FulfillChannel fulfillChannel = FulfillChannel.valueOf(soList.get(0).getFulfillChannel());
            boolean flag = cancelOrderHandler.execute(soList, fulfillChannel);
            if (!flag) {
                throw EC.ERROR.exception("订单取消失败");
            }

            this.updateForCancel(orderHead, soList);

            if (FulfillChannel.isWumartOfc(fulfillChannel)) {
                asyncTaskHandler.buildAsyncTask(soList, OfcTaskType.CANCEL_VALIDATE);
            }
        } catch (Throwable e) {
            logger.error(prefix + "[error][" + e.getMessage() + "]");
            throw e;
        } finally {
            this.redisTemplate.unlock(key);
        }

        logger.info(prefix + "[end]");
    }

    @Transactional
    @Override
    public void cancelNotify(Long orderCode) throws BusinessException {
        String prefix = "[CancelOrderNotify][OrderCode:" + orderCode + "]";

        logger.info(prefix + "[start]");
        if (orderCode == null) {
            String msg = "订单号为空！";
            logger.error(prefix + "[error][" + msg + "]");
            throw EC.ERROR.exception(msg);
        }

        String key = orderCode.toString();
        if (!this.redisTemplate.lock(key, 30)) {
            String msg = "订单正在处理.";
            logger.warn(prefix + "[warn][" + msg + "]");
            throw EC.ERROR.exception(msg);
        }

        try {
            //加载订单
            OfcOrderHead orderFilter = new OfcOrderHead();
            orderFilter.setOrderCode(orderCode);
            List<OfcOrderHead> orderList = this.findList(orderFilter, false);
            if (orderList == null || orderList.size() != 1) {
                throw EC.ERROR.exception("订单信息不存在，或者订单数量不唯一");
            }
            OfcOrderHead orderHead = orderList.get(0);

            this.validateOrderForCancel(orderHead);

            //加载So
            OfcSoHead soFilter = new OfcSoHead();
            soFilter.setOrderCode(orderCode);
            List<OfcSoHead> soList = ofcSoHeadDAO.findList(soFilter);
            if (CollectionUtils.isEmpty(soList)) {
                throw EC.ERROR.exception("So信息为空");
            }

            this.validateSoForCancel(soList);

            this.updateForCancel(orderHead, soList);
        } catch (Throwable e) {
            logger.error(prefix + "[error][" + e.getMessage() + "]");
            throw e;
        } finally {
            this.redisTemplate.unlock(key);
        }

        logger.info(prefix + "[end]");
    }

    @Override
    public boolean isForceCancel(Long orderCode) throws BusinessException {
        //加载So
        OfcSoHead soFilter = new OfcSoHead();
        soFilter.setOrderCode(orderCode);
        List<OfcSoHead> soList = ofcSoHeadDAO.findList(soFilter);
        if (CollectionUtils.isEmpty(soList)) {
            throw EC.ERROR.exception("So信息为空");
        }

        for (OfcSoHead so : soList) {
            boolean isForceCancel = this.wumartBasicService.isForceCancel(WumartBasicContext.buildContext(so.getRegionCode(), so.getSupplierDc()));
            if (!isForceCancel) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int update(OfcOrderHead entity, OfcOrderHead filter) throws BusinessException {
        int ret = this.ofcOrderHeadDAO.updateByFilter(entity, filter);
        if (ret < 1) {
            return ret;
        }
        List<OfcOrderDetail> details = entity.getDetails();
        if (!CollectionUtils.isEmpty(details)) {
            for (OfcOrderDetail detail : details) {
                this.ofcOrderDetailDAO.update(detail);
            }
        }
        return ret;
    }

    @Override
    public void update4Create(Long orderCode, BigDecimal takeSkuQty) throws BusinessException {
        OfcOrderHead entity = new OfcOrderHead();
        entity.setTotalSkuSupplyQty(takeSkuQty);
        entity.setFulfillStatus(FulfillStatus.CREATED.getValue());
        OfcOrderHead filter = new OfcOrderHead();
        filter.setOrderCode(orderCode);
        this.ofcOrderHeadDAO.updateByFilter(entity, filter);
        //记录操作日志
        this.ofcOperateLogService.insert(orderCode.toString(), BillType.ORDER, OfcOperateEnum.ORDER_FULFILL, null);
    }

    @Override
    public void update4Deliver(Long orderCode, BigDecimal pickSkuQty) throws BusinessException {
        OfcOrderHead entity = new OfcOrderHead();
        entity.setTotalSkuDeliverQty(pickSkuQty);
        entity.setFulfillStatus(FulfillStatus.DELIVERY.getValue());
        OfcOrderHead filter = new OfcOrderHead();
        filter.setOrderCode(orderCode);
        this.ofcOrderHeadDAO.updateByFilter(entity, filter);
        //记录操作日志
        this.ofcOperateLogService.insert(orderCode.toString(), BillType.ORDER, OfcOperateEnum.ORDER_DELIVER, null);
    }

    @Override
    public int update4Return(Long orderCode, BigDecimal returnQty) throws BusinessException {
        int ret = this.ofcOrderHeadDAO.update4Return(orderCode, returnQty);
        if (ret != 1) {
            throw EC.ERROR.exception("更新订单返仓数量失败！订单号=" + orderCode + "，QTY=" + returnQty);
        }
        //记录操作日志
        this.ofcOperateLogService.insert(orderCode.toString(), BillType.ORDER, OfcOperateEnum.ORDER_RETURN, returnQty.toString());
        return ret;
    }

    /**
     * 填充TransTime
     *
     * @param order
     */
    private void filltTransTime(OfcOrderHead order) {
        JSONObject json;
        String ext = order.getExt();
        if (StringUtils.hasLength(ext)) {
            json = JSON.parseObject(ext);
        } else {
            json = new JSONObject();
        }
        //第二天下午六点
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(order.getOrderTime() * 1000L);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        json.put(Constants.ORDER_H_TRANS_TIME, (int) (cal.getTimeInMillis() / 1000));
        order.setExt(json.toJSONString());
    }

    /**
     * 取消验证订单状态
     *
     * @param orderHead
     * @throws BusinessException
     */
    private void validateOrderForCancel(OfcOrderHead orderHead) throws BusinessException {
        //判断订单状态
        int fulfillStatus = orderHead.getFulfillStatus().intValue();
        if (fulfillStatus == FulfillStatus.CANCELED.getValue().intValue()) {
            return;
        }
        if (fulfillStatus != FulfillStatus.CREATED.getValue().intValue()) {
            throw EC.ERROR.exception("订单状态不正确,状态为:" + fulfillStatus);
        }
    }

    /**
     * 取消验证so状态
     *
     * @param soList
     * @throws BusinessException
     */
    private void validateSoForCancel(List<OfcSoHead> soList) throws BusinessException {
        //判断So状态
        for (OfcSoHead ofcSoHead : soList) {
            int soStatus = ofcSoHead.getSoStatus().intValue();
            if (soStatus == SoStatus.CANCELED.getValue().intValue()) {
                continue;
            }
            if (soStatus != SoStatus.CREATED.getValue().intValue()) {
                throw EC.ERROR.exception("SO单状态不正确,状态为:" + soStatus);
            }
        }
    }

    /**
     * 取消更新数据
     *
     * @param orderHead
     * @param soList
     * @throws BusinessException
     */
    private void updateForCancel(OfcOrderHead orderHead, List<OfcSoHead> soList) throws BusinessException {
        if (orderHead.getFulfillStatus().intValue() == FulfillStatus.CREATED.getValue().intValue()) {
            OfcOrderHead updateOrder = new OfcOrderHead();
            updateOrder.setFulfillStatus(FulfillStatus.CANCELED.getValue());
            OfcOrderHead updateOrderFilter = new OfcOrderHead();
            updateOrderFilter.setId(orderHead.getId());
            updateOrderFilter.setFulfillStatus(FulfillStatus.CREATED.getValue());
            int ret = this.ofcOrderHeadDAO.updateByFilter(updateOrder, updateOrderFilter);
            if (ret < 1) {
                throw EC.ERROR.exception("取消订单更新订单失败");
            }
        }

        for (OfcSoHead ofcSoHead : soList) {
            if (ofcSoHead.getSoStatus().intValue() == SoStatus.CREATED.getValue().intValue()) {
                OfcSoHead updateSo = new OfcSoHead();
                updateSo.setSoStatus(SoStatus.CANCELED.getValue());
                OfcSoHead updateSoFilter = new OfcSoHead();
                updateSoFilter.setId(ofcSoHead.getId());
                updateSoFilter.setSoStatus(SoStatus.CREATED.getValue());
                int retSo = this.ofcSoHeadDAO.updateByFilter(updateSo, updateSoFilter);
                if (retSo < 1) {
                    throw EC.ERROR.exception("取消订单更新SO单失败");
                }
            }
        }
    }
}
