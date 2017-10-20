package com.lsh.ofc.core.service;

import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.core.entity.OfcOrderHead;
import com.lsh.ofc.core.entity.OfcSoHead;

import java.util.List;

/**
 * OFC SO创建Service
 * Created by huangdong on 16/9/10.
 */
public interface OfcSoCreateService {

    /**
     * 准备(记录商品信息、拆单)
     *
     * @param order
     * @return
     * @throws BusinessException
     */
    List<OfcSoHead> prepare(OfcOrderHead order) throws BusinessException;

    /**
     * 处理(提交SO)
     *
     * @param sos
     * @return
     * @throws BusinessException
     */
    int process(OfcOrderHead ofcOrderHead, List<OfcSoHead> sos) throws BusinessException;

    /**
     * 合单
     *
     * @param orderCode
     * @return
     * @throws BusinessException
     */
    int merge(Long orderCode) throws BusinessException;

    /**
     * 回调
     * @param data
     * @return
     * @throws BusinessException
     */
    CommonResult<Boolean> callback(JSONObject data) throws BusinessException;
}
