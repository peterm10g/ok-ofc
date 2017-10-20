package com.lsh.ofc.core.service.impl;

import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.service.CancelOrderService;

import java.util.List;

/**
 * Created by panxudong on 17/3/28.
 */
public abstract class AbstractCancelOrderService implements CancelOrderService {

    protected abstract List<String> buildRequest(List<OfcSoHead> list);

}
