package com.lsh.ofc.provider.rest.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.core.entity.OfcSoDetail;
import com.lsh.ofc.core.entity.OfcSoHead;

import java.util.List;

/**
 * SO REST服务
 * Created by huangdong on 16/12/6.
 */
public interface SoRestService {

    /**
     * 查询SO单信息
     *
     * @param orderCode
     * @return
     * @throws BusinessException
     */
    CommonResult<List<OfcSoHead>> query(Long orderCode) throws BusinessException;

    /**
     * 根据SO单据号查询SO明细列表
     *
     * @param soBillCode
     * @return
     * @throws BusinessException
     */
    CommonResult<List<OfcSoDetail>> details(String soBillCode) throws BusinessException;
}
