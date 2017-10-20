package com.lsh.ofc.core.handler;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.OfcCustomer;
import com.lsh.ofc.core.entity.OfcOrderHead;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.enums.SoStatus;
import com.lsh.ofc.proxy.model.CreateSoReqHead;
import com.lsh.ofc.proxy.model.MeipiCustomer;
import com.lsh.ofc.core.proxy.service.WumartOfcServiceProxy;
import com.lsh.ofc.proxy.util.MethodCallLogCollector;
import org.springframework.context.ApplicationContext;

/**
 * 【物美OFC】创建SO处理Handler
 * Created by huangdong on 16/9/9.
 */
public class CreateSoByWumartOFCHandler extends CreateSoHandler {

    private final WumartOfcServiceProxy wumartOfcServiceProxy;

    protected CreateSoByWumartOFCHandler(final ApplicationContext context, final OfcOrderHead ofcOrderHead, final OfcSoHead so, final OfcCustomer customer) {
        super(context, ofcOrderHead, so, customer);
        this.wumartOfcServiceProxy = context.getBean(WumartOfcServiceProxy.class);
    }

    @Override
    protected boolean process(final OfcSoHead so, final OfcCustomer customer) throws BusinessException {
        CreateSoReqHead createSoReq = this.getCreateSoReq();
        MeipiCustomer meipiCustomer = this.getMeipiCustomer();
        boolean ret;
        try {
            MethodCallLogCollector.init();
            MethodCallLogCollector.business(so.getSoBillCode(), 10);
            ret = this.wumartOfcServiceProxy.submitMeipiOrder(WumartOfcServiceProxy.OrderType.NORMAL, createSoReq, meipiCustomer);
        } finally {
            MethodCallLogCollector.upload();
            MethodCallLogCollector.clear();
        }
        if (!ret) {
            return false;
        }

        OfcSoHead updateHead = new OfcSoHead();
        updateHead.setId(so.getId());
        updateHead.setSoBillCode(so.getSoBillCode());
        updateHead.setSoStatus(SoStatus.CREATING.getValue());
        int r = this.ofcSoService.update4Create(updateHead, SoStatus.UNCREATED, SoStatus.CREATING);
        logger.info("更新SO状态，单据号=" + so.getSoBillCode() + "。。。" + SoStatus.CREATING + " " + r);
        return true;
    }
}
