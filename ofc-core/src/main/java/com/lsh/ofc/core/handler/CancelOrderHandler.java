package com.lsh.ofc.core.handler;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.enums.FulfillChannel;
import com.lsh.ofc.core.service.CancelOrderService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by panxudong on 17/3/28.
 */
@Service
public class CancelOrderHandler {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Resource(name = "cancelOrderLshWmsHandler")
    private CancelOrderService cancelOrderLshWmsService;

    @Resource(name = "cancelOrderWumartOfcHandler")
    private CancelOrderService cancelOrderWumartOfcService;

    public boolean execute(List<OfcSoHead> list, FulfillChannel fulfillChannel) throws BusinessException {
        CancelOrderService cancelOrderService = this.getHandler(fulfillChannel);
        return cancelOrderService.cancelOrder(list);
    }

    private CancelOrderService getHandler(FulfillChannel fulfillChannel) {
        if (fulfillChannel == FulfillChannel.LSH_WMS) {
            return cancelOrderLshWmsService;
        } else {
            return cancelOrderWumartOfcService;
        }
    }

}
