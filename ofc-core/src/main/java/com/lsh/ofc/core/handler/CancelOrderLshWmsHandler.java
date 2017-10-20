package com.lsh.ofc.core.handler;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.service.impl.AbstractCancelOrderService;
import com.lsh.ofc.proxy.service.WmsServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panxudong on 17/3/28.
 */
@Service("cancelOrderLshWmsHandler")
public class CancelOrderLshWmsHandler extends AbstractCancelOrderService {

    @Autowired
    private WmsServiceProxy wmsServiceProxy;

    @Override
    public boolean cancelOrder(List<OfcSoHead> list) throws BusinessException {
        List<String> request = this.buildRequest(list);
        String warehouseCode = list.get(0).getWarehouseCode();
        return this.wmsServiceProxy.cancelOrder(warehouseCode, request, WmsServiceProxy.CancelType.SO);
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
