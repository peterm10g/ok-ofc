package com.lsh.ofc.provider.rest.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.api.dto.OrderHeadDTO;

/**
 * 定单REST服务
 * Created by huangdong on 16/8/28.
 */
public interface OrderRestService {

    /**
     * 创建返仓RO
     *
     * @param dto
     * @return
     * @throws BusinessException
     */
    CommonResult<Boolean> createSo(OrderHeadDTO dto) throws BusinessException;

    /**
     * 计算订单成本
     *
     * @param json
     * @return
     * @throws BusinessException
     */
    CommonResult<Object> calcCosts(String json) throws BusinessException;

    /**
     * 查询订单寄售商品发货数据
     *
     * @param orderCodes
     * @return
     * @throws BusinessException
     */
    CommonResult<Object> queryConsignOfOBD(Long[] orderCodes) throws BusinessException;
}
