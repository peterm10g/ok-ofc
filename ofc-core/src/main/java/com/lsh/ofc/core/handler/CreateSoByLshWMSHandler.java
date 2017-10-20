package com.lsh.ofc.core.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.entity.OfcCustomer;
import com.lsh.ofc.core.entity.OfcOrderHead;
import com.lsh.ofc.core.entity.OfcSoDetail;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.enums.Region;
import com.lsh.ofc.core.enums.SoStatus;
import com.lsh.ofc.core.util.OFCUtils;
import com.lsh.ofc.proxy.context.WumartBasicContext;
import com.lsh.ofc.proxy.model.CreateLshSoReqHead;
import com.lsh.ofc.proxy.model.CreateLshSoRespDetail;
import com.lsh.ofc.proxy.model.CreateLshSoRespHead;
import com.lsh.ofc.proxy.service.WmsServiceProxy;
import com.lsh.ofc.core.proxy.service.WumartBasicService;
import com.lsh.ofc.proxy.util.MethodCallLogCollector;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 【链商WMS】创建SO处理Handler
 * Created by huangdong on 16/9/9.
 */
public class CreateSoByLshWMSHandler extends CreateSoHandler {

    private final WumartBasicService wumartBasicService;

    private final WmsServiceProxy wmsServiceProxy;

    protected CreateSoByLshWMSHandler(final ApplicationContext context, final OfcOrderHead ofcOrderHead, final OfcSoHead so, final OfcCustomer customer) {
        super(context, ofcOrderHead, so, customer);
        this.wumartBasicService = context.getBean(WumartBasicService.class);
        this.wmsServiceProxy = context.getBean(WmsServiceProxy.class);
    }

    @Override
    protected boolean process(final OfcSoHead so, final OfcCustomer customer) throws BusinessException {
        CreateLshSoReqHead createSoReq = this.getCreateLshSoReq();

        CreateLshSoRespHead createSoResp;
        try {
            MethodCallLogCollector.init();
            MethodCallLogCollector.business(so.getSoBillCode(), 10);

            String wmsPath = this.wumartBasicService.getWmsPath(WumartBasicContext.buildContext(so.getRegionCode(), so.getSupplierDc()));
//            Boolean isBeiJing = so.getRegionCode().equals(Region.Beijing.getCode())
//                    || so.getRegionCode().equals(Region.BeijingKA.getCode())
//                    || so.getRegionCode().equals(Region.Beijingcg.getCode());
            createSoResp = wmsServiceProxy.createSo(createSoReq, wmsPath);
        } finally {
            MethodCallLogCollector.upload();
            MethodCallLogCollector.clear();
        }
        String supplySoCode = createSoResp.getOrderId();
        Map<Integer, OfcSoDetail> detailMap = new HashMap<>();
        for (OfcSoDetail detail : so.getDetails()) {
            detailMap.put(detail.getItemNo(), detail);
        }
        List<OfcSoDetail> updateDetails = new ArrayList<>(detailMap.size());
        BigDecimal totalSkuQty = BigDecimal.ZERO;
        for (CreateLshSoRespDetail respDetail : createSoResp.getDetailList()) {
            OfcSoDetail detail = detailMap.get(respDetail.getDetailOtherId());
            BigDecimal respQty = respDetail.getUnitQty();
            if (respQty == null) {
                respQty = BigDecimal.ZERO;
            }
            detail.setSkuSupplyQty(respQty);
//            JSONObject obj = JSON.parseObject(detail.getExt());
//            obj.put(Constants.SO_D_OBD, item.getObd());
//            detail.setExt(obj.toJSONString());

            OfcSoDetail updateDetail = new OfcSoDetail();
            updateDetail.setId(detail.getId());
            updateDetail.setSkuSupplyQty(respQty);
            updateDetails.add(updateDetail);
            totalSkuQty = totalSkuQty.add(respQty);
        }

        JSONObject ext = JSON.parseObject(so.getExt());
        ext.put(Constants.SO_H_REF_SO_CODE, supplySoCode);
        ext.put(Constants.SO_H_FULFILL_CREATE_TIME, OFCUtils.currentTime());
        OfcSoHead updateHead = new OfcSoHead();
        updateHead.setId(so.getId());
        updateHead.setSoBillCode(so.getSoBillCode());
        updateHead.setSoStatus(SoStatus.CREATED.getValue());
        updateHead.setSoCode(so.getSoBillCode());
        updateHead.setTotalSkuSupplyQty(totalSkuQty);
        updateHead.setExt(ext.toJSONString());
        updateHead.setDetails(updateDetails);
        this.ofcSoService.update4Create(updateHead, SoStatus.UNCREATED, SoStatus.CREATED);

        return true;
    }

}
