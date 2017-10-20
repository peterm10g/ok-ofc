package com.lsh.ofc.core.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.OfcOrderHead;
import com.lsh.ofc.core.entity.OfcSoHead;

import java.util.List;

/**
 * OFC订单拆分Service
 * Created by huangdong on 16/9/9.
 */
public interface OfcSoSplitService {

    /**
     * 订单拆分
     *
     * @param order
     * @return
     * @throws BusinessException
     */
    List<OfcSoHead> split(OfcOrderHead order) throws BusinessException;
}
