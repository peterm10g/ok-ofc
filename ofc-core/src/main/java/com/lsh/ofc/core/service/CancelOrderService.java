package com.lsh.ofc.core.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.OfcSoHead;

import java.util.List;

/**
 * Created by panxudong on 17/3/28.
 */
public interface CancelOrderService {

    boolean cancelOrder(List<OfcSoHead> list) throws BusinessException;

}
