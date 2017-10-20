package com.lsh.ofc.core.handler;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.proxy.service.WumartOfcServiceProxy;
import com.lsh.ofc.core.service.impl.AbstractCancelOrderService;
import com.lsh.ofc.proxy.context.WumartBasicContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panxudong on 17/3/28.
 */
@Service("cancelOrderWumartOfcHandler")
public class CancelOrderWumartOfcHandler extends AbstractCancelOrderService {

    @Autowired
    private WumartOfcServiceProxy wumartOfcServiceProxy;

    @Override
    public boolean cancelOrder(List<OfcSoHead> list) throws BusinessException {
        List<String> request = this.buildRequest(list);
        Integer regionCode = list.get(0).getRegionCode();
        return wumartOfcServiceProxy.cancelOrder(request, WumartBasicContext.buildContext(regionCode));
    }

    @Override
    protected List<String> buildRequest(List<OfcSoHead> list) {
        List<String> requestList = new ArrayList<>();
        for (OfcSoHead ofcSoHead : list) {
            requestList.add(ofcSoHead.getSoBillCode());
        }
        return requestList;
    }
}
