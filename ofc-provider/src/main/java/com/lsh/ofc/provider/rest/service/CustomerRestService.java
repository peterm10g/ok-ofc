package com.lsh.ofc.provider.rest.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;

import java.io.File;
import java.util.List;

/**
 * 客户REST服务
 * Created by huangdong on 16/9/13.
 */
public interface CustomerRestService {

    /**
     * 刷新客户信息
     *
     * @param custCode
     * @return
     * @throws BusinessException
     */
    CommonResult<Boolean> refresh(Long custCode) throws BusinessException;

    /**
     * 创建美批客户（批量）
     *
     * @param regionCode
     * @param num
     * @return
     * @throws BusinessException
     */
    CommonResult<List<String>> addMpCust(Integer regionCode, int num) throws BusinessException;
}
