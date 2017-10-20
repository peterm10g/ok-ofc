package com.lsh.ofc.core.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.entity.OfcCustomer;
import com.lsh.ofc.core.entity.OfcOrderHead;
import com.lsh.ofc.core.entity.OfcSoDetail;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.enums.SoStatus;
import com.lsh.ofc.core.util.OFCUtils;
import com.lsh.ofc.proxy.model.CreateSoReqHead;
import com.lsh.ofc.proxy.model.CreateSoRetDetail;
import com.lsh.ofc.proxy.model.CreateSoRetHead;
import com.lsh.ofc.proxy.model.MeipiCustomer;
import com.lsh.ofc.core.proxy.service.WumartOfcServiceProxy;
import com.lsh.ofc.core.proxy.service.WumartSapServiceProxy;
import com.lsh.ofc.proxy.util.MethodCallLogCollector;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 【物美SAP】创建SO处理Handler
 * Created by huangdong on 16/9/9.
 */
public class CreateSoByWumartSAPHandler extends CreateSoHandler {

    private final WumartSapServiceProxy wumartSapServiceProxy;

    private final WumartOfcServiceProxy wumartOfcServiceProxy;

    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    protected CreateSoByWumartSAPHandler(final ApplicationContext context, final OfcOrderHead ofcOrderHead, final OfcSoHead so, final OfcCustomer customer) {
        super(context, ofcOrderHead, so, customer);
        this.wumartSapServiceProxy = context.getBean(WumartSapServiceProxy.class);
        this.wumartOfcServiceProxy = context.getBean(WumartOfcServiceProxy.class);
    }

    @Override
    protected boolean process(final OfcSoHead so, final OfcCustomer customer) throws BusinessException {
        CreateSoReqHead createSoReq = this.getCreateSoReq();
        BigDecimal totalSkuQty = BigDecimal.ZERO;
        CreateSoRetHead createdSO;
        try {
            MethodCallLogCollector.init();
            MethodCallLogCollector.business(so.getSoBillCode(), 10);
            createdSO = this.wumartSapServiceProxy.createMeipiSo(createSoReq);
        } finally {
            MethodCallLogCollector.upload();
            MethodCallLogCollector.clear();
        }
        String supplySoCode = createdSO.getCode();
        Map<Integer, OfcSoDetail> detailMap = new HashMap<>();
        for (OfcSoDetail detail : so.getDetails()) {
            detailMap.put(detail.getItemNo(), detail);
        }
        List<OfcSoDetail> updateDetails = new ArrayList<>(detailMap.size());
        for (CreateSoRetDetail item : createdSO.getItems()) {
            OfcSoDetail detail = detailMap.get(Integer.parseInt(StringUtils.trimAllWhitespace(item.getNo())));
            BigDecimal respQty = item.getRespQty();
            if (respQty == null) {
                respQty = BigDecimal.ZERO;
            }
            JSONObject obj = JSON.parseObject(detail.getExt());
            obj.put(Constants.SO_D_OBD, item.getObd());

            detail.setSkuSupplyQty(respQty);
            detail.setExt(obj.toJSONString());

            OfcSoDetail updateDetail = new OfcSoDetail();
            updateDetail.setId(detail.getId());
            updateDetail.setSkuSupplyQty(respQty);
            updateDetail.setExt(obj.toJSONString());
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
        updateHead.setSoCode(supplySoCode);
        updateHead.setTotalSkuSupplyQty(totalSkuQty);
        updateHead.setDetails(updateDetails);
        updateHead.setExt(ext.toJSONString());
        this.ofcSoService.update4Create(updateHead, SoStatus.UNCREATED, SoStatus.CREATED);

        //TODO:创建SO单双写
        this.executor.submit(new CreateSoByWumartSAPHandler.WumartOfcTask(this.wumartOfcServiceProxy, createSoReq, customer));

        return true;
    }

    class WumartOfcTask implements Runnable {

        private final WumartOfcServiceProxy proxy;

        private final CreateSoReqHead so;

        private final OfcCustomer customer;

        public WumartOfcTask(WumartOfcServiceProxy proxy, CreateSoReqHead so, OfcCustomer customer) {
            this.proxy = proxy;
            this.so = so;
            this.customer = customer;
        }

        @Override
        public void run() {
            MeipiCustomer meipiCustomer = new MeipiCustomer();
            meipiCustomer.setMarketName(this.customer.getCustName());
            meipiCustomer.setContactName(this.customer.getContactName());
            meipiCustomer.setContactPhone(this.customer.getContactPhone());
            meipiCustomer.setProvince(this.customer.getProvince());
            meipiCustomer.setCity(this.customer.getCity());
            meipiCustomer.setDistrict(this.customer.getDistrict());
            meipiCustomer.setAddress(this.customer.getAddress());
            meipiCustomer.setRegionCode(this.customer.getRegionCode());
            meipiCustomer.setSoUserCode(this.customer.getMpCustCode());
            meipiCustomer.setSoUserRegion(this.customer.getMpCustZone());
            try {
                logger.info("物美OFC履约开始...SO=" + JSON.toJSONString(this.so));
                boolean ret = proxy.submitMeipiOrder(WumartOfcServiceProxy.OrderType.NORMAL, this.so, meipiCustomer);
                logger.info("物美OFC履约结束(" + ret + ")...SO=" + JSON.toJSONString(this.so));
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }
    }
}
