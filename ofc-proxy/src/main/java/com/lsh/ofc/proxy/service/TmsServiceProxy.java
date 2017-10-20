package com.lsh.ofc.proxy.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.proxy.model.ReceiptOrderDetail;
import com.lsh.ofc.proxy.model.ReceiptOrderHead;
import com.lsh.ofc.proxy.util.HttpClientUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangdong on 16/8/26.
 */
@Service
public class TmsServiceProxy {

    private final Logger logger = Logger.getLogger(TmsServiceProxy.class);

    @Value("${tms.server.path}")
    private String tmsServerPath;

    private static final String GET_DELIVER_ORDER_URI = "/bill/shipping/getinfo";

    private static final String GET_RECEIPT_ORDER_URI = "/bill/receipt/getinfo";

    private static final String ADD_ORDER_URI = "/order/order/addorder";

    public Map<Long, BigDecimal> getDeliverSkus(Long orderCode) throws BusinessException {
        String uri = this.tmsServerPath + GET_DELIVER_ORDER_URI;
        String content = JSON.toJSONString(Collections.singletonMap("orderIds", Arrays.asList(orderCode)));
        new BasicNameValuePair("orderIds", Arrays.asList(orderCode).toString());
        HttpEntity entity = new StringEntity(content, ContentType.APPLICATION_JSON);
        String ret = HttpClientUtils.post(uri, entity).getData();
        JSONObject obj = JSON.parseObject(ret).getJSONObject("content");
        if (obj == null) {
            return Collections.emptyMap();
        }
        obj = obj.getJSONObject(orderCode.toString());
        if (obj == null) {
            return Collections.emptyMap();
        }
        JSONArray array = obj.getJSONArray("detail");
        if (array.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<Long, BigDecimal> skus = new HashMap<>();
        for (int i = 0; i < array.size(); i++) {
            JSONObject detail = array.getJSONObject(i);
            Long skuCode = detail.getLong("item_id");
            BigDecimal skuQty = detail.getBigDecimal("qty").multiply(detail.getBigDecimal("sale_unit"));
            BigDecimal qty = skus.get(skuCode);
            if (qty == null) {
                qty = skuQty;
            } else {
                qty = qty.add(skuQty);
            }
            skus.put(skuCode, qty);
        }
        return skus;
    }

    public ReceiptOrderHead getReceiptOrder(Long orderCode) throws BusinessException {
        String uri = this.tmsServerPath + GET_RECEIPT_ORDER_URI;
        String content = JSON.toJSONString(Collections.singletonMap("orderIds", Arrays.asList(orderCode)));
        HttpEntity entity = new StringEntity(content, ContentType.APPLICATION_JSON);
        String ret = HttpClientUtils.post(uri, entity).getData();
        JSONObject obj = JSON.parseObject(ret).getJSONObject("content").getJSONObject(orderCode.toString());
        if (obj == null) {
            return null;
        }
        ReceiptOrderHead head = new ReceiptOrderHead();
        JSONObject hobj = obj.getJSONObject("head");
        head.setOrderId(hobj.getLong("order_id"));
        head.setAddressId(hobj.getLong("address_id"));
        head.setCouponMoney(hobj.getBigDecimal("coupon_money"));
        head.setTicketCouponMoney(hobj.getBigDecimal("ticket_coupon_money"));
        head.setDiscountCouponMoney(hobj.getBigDecimal("discount_coupon_money"));
        head.setPayCouponMoney(hobj.getBigDecimal("pay_coupon_money"));
        head.setMoney(hobj.getBigDecimal("money"));
        head.setReceiptAt(hobj.getInteger("receipt_at"));
        JSONArray darr = obj.getJSONArray("detail");
        List<ReceiptOrderDetail> details = new ArrayList<>();
        for (int i = 0; i < darr.size(); i++) {
            ReceiptOrderDetail detail = new ReceiptOrderDetail();
            JSONObject itm = darr.getJSONObject(i);
            detail.setItemId(itm.getLong("item_id"));
            detail.setMoney(itm.getBigDecimal("money"));
            details.add(detail);
        }
        head.setDetails(details);
        return head;
    }

    public boolean addOrder(String info) throws BusinessException {
        String url = this.tmsServerPath + ADD_ORDER_URI;
        logger.info("[POST][URL=" + url + "]" + info);
        List<BasicNameValuePair> pairs = new ArrayList<>(2);
        pairs.add(new BasicNameValuePair("info", info)); // 参数
        pairs.add(new BasicNameValuePair("from_system", "YG")); // 参数
        HttpEntity entity = new UrlEncodedFormEntity(pairs, Consts.UTF_8);
        String content = HttpClientUtils.post(url, entity).getData();
        Integer ret = JSON.parseObject(content).getInteger("ret");
        return Integer.valueOf(0).equals(ret);
    }
}
