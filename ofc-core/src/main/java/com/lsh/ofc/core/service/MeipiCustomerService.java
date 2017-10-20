package com.lsh.ofc.core.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.MeipiCustomer;

import java.util.List;

/**
 * OFC客户信息Service
 * Created by huangdong on 16/8/30.
 */
public interface MeipiCustomerService {

    /**
     * 创建美批客户
     *
     * @param regionCode
     * @return
     * @throws BusinessException
     */
    String addMpCust(Integer regionCode) throws BusinessException;

    /**
     * 修改美批客户
     *
     * @param mpCustomer
     * @return
     * @throws BusinessException
     */
    String modMpCust(MeipiCustomer mpCustomer) throws BusinessException;

    /**
     * 提取美批客户信息
     *
     * @param regionCode
     * @return
     * @throws BusinessException
     */
    MeipiCustomer popMpCust(Integer regionCode) throws BusinessException;
}
