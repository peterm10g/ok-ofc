package com.lsh.ofc.api.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.api.dto.OrderHeadDTO;

/**
 * 订单DUBOO服务
 * Created by huangdong on 16/8/28.
 */
public interface OrderService {

    /**
     * 创建SO订单
     *
     * @param dto
     * @return
     * @throws BusinessException
     */
    CommonResult<Boolean> createOrder(OrderHeadDTO dto) throws BusinessException;

    /**
     * 取消订单
     *
     * @param orderCode
     * @return
     * @throws BusinessException
     */
    CommonResult<Boolean> cancelOrder(Long orderCode) throws BusinessException;

    /**
     * 取消订单通知
     *
     * @param orderCode
     * @return
     * @throws BusinessException
     */
    CommonResult<Boolean> cancelOrderNotify(Long orderCode) throws BusinessException;

    /**
     * 是否切换WumartOFC
     *
     * @return
     * @throws BusinessException
     */
    boolean isWumartOfc() throws BusinessException;

    /**
     * 是否可以强制取消
     *
     * @param orderCode
     * @return
     * @throws BusinessException
     */
    boolean isForceCancel(Long orderCode) throws BusinessException;

}
