package com.lsh.ofc.core.handler;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.OfcCustomer;
import com.lsh.ofc.core.entity.OfcRoHead;
import com.lsh.ofc.core.enums.RoStatus;
import com.lsh.ofc.proxy.model.CreateSoReqHead;
import com.lsh.ofc.core.proxy.service.WumartOfcServiceProxy;
import org.springframework.context.ApplicationContext;

/**
 * 【物美OFC】创建返仓处理Handler
 * Created by huangdong on 16/9/9.
 */
public class CreateRoByWumartOFCHandler extends CreateRoHandler {

    private final WumartOfcServiceProxy wumartOfcServiceProxy;

    protected CreateRoByWumartOFCHandler(final ApplicationContext context, final OfcRoHead ro, final OfcCustomer customer) {
        super(context, ro, customer);
        this.wumartOfcServiceProxy = context.getBean(WumartOfcServiceProxy.class);
    }

    @Override
    protected boolean process(final OfcRoHead ro, final OfcCustomer customer) throws BusinessException {
        CreateSoReqHead createSoReq = this.getCreateRoReq();
        boolean ret = this.wumartOfcServiceProxy.submitMeipiOrder(WumartOfcServiceProxy.OrderType.RETURN, createSoReq, ro.getId());
        if (!ret) {
            return false;
        }

        OfcRoHead filter = new OfcRoHead();
        filter.setId(ro.getId());
        filter.setRoBillCode(ro.getRoBillCode());
        filter.setRoStatus(RoStatus.UNCREATED.getValue());
        OfcRoHead update = new OfcRoHead();
        update.setRoStatus(RoStatus.CREATING.getValue());
        int r = this.ofcRoService.updateStatus(filter, update);
        logger.info("更新RO状态，单据号=" + ro.getRoBillCode() + "。。。" + RoStatus.CREATING + " " + r);
        return true;
    }
}
