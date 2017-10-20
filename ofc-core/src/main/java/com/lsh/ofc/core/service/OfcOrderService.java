package com.lsh.ofc.core.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.OfcOrderHead;

import java.math.BigDecimal;
import java.util.List;

/**
 * OFC订单Service
 * Created by huangdong on 16/9/9.
 */
public interface OfcOrderService {

    /**
     * 查询OFC订单信息列表
     *
     * @param filter
     * @param fillDetails
     * @return
     * @throws BusinessException
     */
    List<OfcOrderHead> findList(OfcOrderHead filter, boolean fillDetails) throws BusinessException;

    /**
     * 统计OFC订单信息数量
     *
     * @param filter
     * @return
     * @throws BusinessException
     */
    int count(OfcOrderHead filter) throws BusinessException;

    /**
     * 创建OFC订单信息
     *
     * @param order
     * @throws BusinessException
     */
    void create(OfcOrderHead order) throws BusinessException;

    /**
     * 取消订单
     *
     * @param orderCode
     * @throws BusinessException
     */
    void cancel(Long orderCode) throws BusinessException;

    /**
     * 取消订单通知
     *
     * @param orderCode
     * @throws BusinessException
     */
    void cancelNotify(Long orderCode) throws BusinessException;

    /**
     * 是否可以强制取消
     *
     * @param orderCode
     * @return
     * @throws BusinessException
     */
    boolean isForceCancel(Long orderCode) throws BusinessException;

    /**
     * 更新OFC订单信息
     *
     * @param entity
     * @param filter
     * @return
     * @throws BusinessException
     */
    int update(OfcOrderHead entity, OfcOrderHead filter) throws BusinessException;

    /**
     * 更新OFC订单履约数量
     *
     * @param orderCode
     * @param takeSkuQty
     * @throws BusinessException
     */
    void update4Create(Long orderCode, BigDecimal takeSkuQty) throws BusinessException;

    /**
     * 更新OFC订单发货数量
     * insert
     *
     * @param orderCode
     * @param pickSkuQty
     * @throws BusinessException
     */
    void update4Deliver(Long orderCode, BigDecimal pickSkuQty) throws BusinessException;

    /**
     * 更新OFC订单返仓数量（增量）
     *
     * @param orderCode
     * @param returnQty
     * @return
     * @throws BusinessException
     */
    int update4Return(Long orderCode, BigDecimal returnQty) throws BusinessException;
}
