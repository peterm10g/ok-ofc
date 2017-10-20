package com.lsh.ofc.core.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.OfcCustomer;

/**
 * OFC客户信息Service
 * Created by huangdong on 16/8/30.
 */
public interface OfcCustomerService {

    /**
     * 获取美批用户
     *
     * @param custCode
     * @return
     * @throws BusinessException
     */
    OfcCustomer getCustomer(Long custCode) throws BusinessException;

    /**
     * 更新客户信息
     *
     * @param param
     * @return
     * @throws BusinessException
     */
    OfcCustomer updateCustomer(OfcCustomer param) throws BusinessException;

    /**
     * 刷新客户信息
     *
     * @param custCode
     * @throws BusinessException
     */
    void refreshCustomer(Long custCode) throws BusinessException;
}
