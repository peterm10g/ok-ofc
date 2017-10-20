package com.lsh.ofc.core.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.entity.OfcCustomer;
import com.lsh.ofc.core.entity.OfcRoHead;
import com.lsh.ofc.core.enums.RoStatus;
import com.lsh.ofc.core.util.OFCUtils;
import com.lsh.ofc.proxy.model.CreateSoReqHead;
import com.lsh.ofc.proxy.model.CreateSoRetHead;
import com.lsh.ofc.core.proxy.service.WumartSapServiceProxy;
import org.springframework.context.ApplicationContext;

/**
 * 【物美SAP】创建返仓处理Handler
 * Created by huangdong on 16/9/9.
 */
public class CreateRoByWumartSAPHandler extends CreateRoHandler {

    private final WumartSapServiceProxy wumartSapServiceProxy;

    protected CreateRoByWumartSAPHandler(final ApplicationContext context, final OfcRoHead ro, final OfcCustomer customer) {
        super(context, ro, customer);
        this.wumartSapServiceProxy = context.getBean(WumartSapServiceProxy.class);
    }

    @Override
    protected boolean process(final OfcRoHead ro, final OfcCustomer customer) throws BusinessException {
        boolean first = JSON.parseObject(ro.getExt()).getInteger(Constants.RETURN_H_BATCH) == 1;
        CreateSoReqHead createSoReq = this.getCreateRoReq();
        CreateSoRetHead createdSO = this.wumartSapServiceProxy.createMeipiRo(createSoReq, createSoReq.getSoCode(), first);

        OfcRoHead filter = new OfcRoHead();
        filter.setId(ro.getId());
        filter.setRoBillCode(ro.getRoBillCode());
        filter.setRoStatus(RoStatus.UNCREATED.getValue());
        JSONObject ext = JSON.parseObject(ro.getExt());
        ext.put(Constants.RO_H_REF_RO_CODE, createdSO.getCode());
        ext.put(Constants.RO_H_FULFILL_CREATE_TIME, OFCUtils.currentTime());
        OfcRoHead update = new OfcRoHead();
        update.setRoCode(createdSO.getCode());
        update.setRoStatus(RoStatus.CREATED.getValue());
        int r = this.ofcRoService.updateStatus(filter, update);
        logger.info("更新RO状态，单据号=" + ro.getRoBillCode() + "。。。" + RoStatus.CREATED + " " + r);
        return true;
    }
}
