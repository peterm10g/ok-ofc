package com.lsh.ofc.provider.rest.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * 物美OFC REST服务
 * Created by huangdong on 16/9/13.
 */
public interface WumartOfcRestService {

    /**
     * 接口转发
     * @param content
     * @return
     * @throws Exception
     */
    CommonResult<Object> redirect(String content) throws BusinessException;

    /**
     * SO回调
     *
     * @param content
     * @return
     * @throws BusinessException
     */
    CommonResult<Boolean> callback(String content) throws BusinessException;
}
