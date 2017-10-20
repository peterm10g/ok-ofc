package com.lsh.ofc.core.service;

import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.core.entity.OfcReturnHead;
import com.lsh.ofc.core.entity.OfcRoHead;

import java.util.List;

/**
 * OFC RO创建Service
 * Created by huangdong on 16/9/10.
 */
public interface OfcRoCreateService {

    /**
     * 准备
     *
     * @param bill
     * @return
     * @throws BusinessException
     */
    List<OfcRoHead> prepare(OfcReturnHead bill) throws BusinessException;

    /**
     * 处理
     *
     * @param returnCode
     * @return
     * @throws BusinessException
     */
    boolean process(Long returnCode) throws BusinessException;

    /**
     * 回调
     *
     * @param data
     * @return
     * @throws BusinessException
     */
    CommonResult<Boolean> callback(JSONObject data) throws BusinessException;
}
