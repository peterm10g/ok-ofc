package com.lsh.ofc.core.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.entity.OfcCustomer;
import com.lsh.ofc.core.entity.OfcRoHead;
import com.lsh.ofc.core.enums.Region;
import com.lsh.ofc.core.enums.RoStatus;
import com.lsh.ofc.core.util.OFCUtils;
import com.lsh.ofc.proxy.context.WumartBasicContext;
import com.lsh.ofc.proxy.model.CreateLshRoReqHead;
import com.lsh.ofc.proxy.model.CreateLshSoRespHead;
import com.lsh.ofc.proxy.service.WmsServiceProxy;
import com.lsh.ofc.core.proxy.service.WumartBasicService;
import org.springframework.context.ApplicationContext;

/**
 * 【链商WMS】创建返仓处理Handler
 * Created by huangdong on 16/9/9.
 */
public class CreateRoByLshWMSHandler extends CreateRoHandler {

    private final WumartBasicService wumartBasicService;

    private final WmsServiceProxy wmsServiceProxy;

    protected CreateRoByLshWMSHandler(final ApplicationContext context, final OfcRoHead ro, final OfcCustomer customer) {
        super(context, ro, customer);
        this.wumartBasicService = context.getBean(WumartBasicService.class);
        this.wmsServiceProxy = context.getBean(WmsServiceProxy.class);
    }

    @Override
    protected boolean process(final OfcRoHead ro, final OfcCustomer customer) throws BusinessException {
        CreateLshRoReqHead createRoReq = this.getCreateLshRoReq();

        String wmsPath = this.wumartBasicService.getWmsPath(WumartBasicContext.buildContext(ro.getRegionCode(), ro.getSupplierDc()));
//        Boolean isBeiJing = ro.getRegionCode().equals(Region.Beijing.getCode())
//                || ro.getRegionCode().equals(Region.BeijingKA.getCode())
//                || ro.getRegionCode().equals(Region.Beijingcg.getCode());
        CreateLshSoRespHead createSoResp = wmsServiceProxy.createRo(createRoReq, wmsPath);

        OfcRoHead filter = new OfcRoHead();
        filter.setId(ro.getId());
        filter.setRoBillCode(ro.getRoBillCode());
        filter.setRoStatus(RoStatus.UNCREATED.getValue());

        JSONObject ext = JSON.parseObject(ro.getExt());
        ext.put(Constants.RO_H_REF_RO_CODE, createSoResp.getOrderId());
        ext.put(Constants.RO_H_FULFILL_CREATE_TIME, OFCUtils.currentTime());
        OfcRoHead update = new OfcRoHead();
        update.setRoCode(ro.getRoBillCode());
        update.setRoStatus(RoStatus.CREATED.getValue());
        update.setExt(ext.toJSONString());
        int r = this.ofcRoService.updateStatus(filter, update);
        logger.info("更新RO状态，单据号=" + ro.getRoBillCode() + "。。。" + RoStatus.CREATED + " " + r);
        return true;
    }
}
