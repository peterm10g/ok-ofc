package com.lsh.ofc.proxy.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.proxy.model.CreateLshRoReqHead;
import com.lsh.ofc.proxy.model.CreateLshSoReqHead;
import com.lsh.ofc.proxy.model.CreateLshSoRespDetail;
import com.lsh.ofc.proxy.model.CreateLshSoRespHead;
import com.lsh.ofc.proxy.util.HttpClientUtils;
import com.lsh.ofc.proxy.util.MethodCallLogCollector;
import lombok.Getter;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by huangdong on 16/8/26.
 */
@Service
public class WmsServiceProxy {

    private static final String URI_CREATE_SO_URL = "/API/V1/openApi/saveObd";
    //    private static final String URI_UPDATE_SO_STATUS = "/API/V1/updateSoStatus";
    private static final String URI_UPDATE_SO_STATUS = "/API/V1/callBackSoInfoByOfc";
    private static final String URI_CREATE_RETURN_URL = "/api/wms/java/v1/ibd/add";
    private static final String URI_IBD_SEACH_SO_BACK = "/api/wms/java/v1/ibd/seachSoBack";
    private static final String URI_CANCEL_SO_URL = "/API/V1/openApi/cancelOrder";
    private final Logger logger = Logger.getLogger(WmsServiceProxy.class);

    @Value("${wms.server.proxy.path}")
    private String wmsServerProxyPath;

    public CreateLshSoRespHead createSo(CreateLshSoReqHead order, String wmsPath) throws BusinessException {
        return this.sendOrder(JSON.toJSONString(order), false, wmsPath);
    }

    public CreateLshSoRespHead createRo(CreateLshRoReqHead order, String wmsPath) throws BusinessException {
        return this.sendOrder(JSON.toJSONString(order), true, wmsPath);
    }

    /**
     * 取消订单
     *
     * @param warehouseCode
     * @param soBillCodes
     * @param cancelType
     * @return
     * @throws BusinessException
     */
    public boolean cancelOrder(String warehouseCode, List<String> soBillCodes, CancelType cancelType) throws BusinessException {
        JSONObject jsonObject = new JSONObject(6);
        jsonObject.put("warehouseCode", warehouseCode);
        jsonObject.put("orderOtherIds", soBillCodes);
        jsonObject.put("orderType", cancelType.getValue());

        String uri = this.wmsServerProxyPath + URI_CANCEL_SO_URL;
        String content = jsonObject.toJSONString();

        StringEntity entity = new StringEntity(content, Consts.UTF_8);
        entity.setContentType(ContentType.APPLICATION_JSON.toString());

        logger.info("[POST][URI= " + uri + " ][request:" + content + "]");
        CommonResult<String> result = HttpClientUtils.post(uri, entity, this.buildHeaders());
        logger.info("[POST][URI= " + uri + " ][response:" + JSON.toJSONString(result) + "]");

        if (!CommonResult.SUCCESS.equals(result.getCode())) {
            throw new BusinessException(CommonResult.ERROR, result.getMessage());
        }

        String data = result.getData();
        JSONObject json = JSONObject.parseObject(data);
        JSONObject head = JSONObject.parseObject(json.getString("head"));
        int status = head.getInteger("status").intValue();
        if (status == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 提交订单
     *
     * @param content
     * @return
     * @throws BusinessException
     */
    public CreateLshSoRespHead sendOrder(String content, Boolean isReturn, String wmsPath) throws BusinessException {
        String uri;
        if (!isReturn) {
            uri = this.wmsServerProxyPath + URI_CREATE_SO_URL;
        } else {
            uri = wmsPath + URI_CREATE_RETURN_URL;
        }

        StringEntity entity = new StringEntity(content, Consts.UTF_8);
        entity.setContentType(ContentType.APPLICATION_JSON.toString());

        JSONObject obj = new JSONObject(4);
        obj.put("URI", uri);
        obj.put("content", content);
        MethodCallLogCollector.params(obj.toJSONString());
        logger.info("[POST][URI=" + uri + "][Request:" + content + "]");
        CommonResult<String> result = HttpClientUtils.post(uri, entity, this.buildHeaders());
        logger.info("[POST][URI=" + uri + "][Response:" + JSON.toJSONString(result) + "]");

        if (!CommonResult.SUCCESS.equals(result.getCode())) {
            throw new BusinessException(CommonResult.ERROR, result.getMessage());
        }

        String data = result.getData();
        JSONObject json = JSONObject.parseObject(data);
        JSONObject head = JSONObject.parseObject(json.getString("head"));
        Integer status = head.getInteger("status");
        if (Integer.valueOf(1).equals(status)) { //成功
            if (isReturn) {
                return this.getCreateLshSoRespHead4Return(json.getString("body"));
            } else {
                return this.getCreateLshSoRespHead4Create(json.getString("body"));
            }
        } else {
            throw new BusinessException(CommonResult.ERROR, head.getString("msg"));
        }
    }

    private Header[] buildHeaders() {
        Header[] headers = {};
        return headers;
    }

    private CreateLshSoRespHead getCreateLshSoRespHead4Return(String content) {
        JSONObject body = JSONObject.parseObject(content);

        CreateLshSoRespHead order = new CreateLshSoRespHead();
        order.setOrderId(body.getString("orderId"));

        return order;
    }

    private CreateLshSoRespHead getCreateLshSoRespHead4Create(String content) {
        JSONObject body = JSONObject.parseObject(content);

        CreateLshSoRespHead order = new CreateLshSoRespHead();
        order.setOrderId(body.getString("orderId"));
        order.setWarehouseCode(body.getString("warehouseCode"));

        JSONArray respDetails = body.getJSONArray("detailList");
        List<CreateLshSoRespDetail> details = new ArrayList<>(respDetails.size());

        Iterator<Object> iterable = respDetails.iterator();
        while (iterable.hasNext()) {
            JSONObject respDetail = (JSONObject) iterable.next();

            CreateLshSoRespDetail detail = new CreateLshSoRespDetail();
            detail.setDetailOtherId(respDetail.getInteger("detailOtherId"));
            detail.setSkuCode(respDetail.getString("skuCode"));
            detail.setUnitName(respDetail.getString("unitName"));
            detail.setUnitQty(respDetail.getBigDecimal("unitQty"));
            details.add(detail);
        }

        order.setDetailList(details);
        return order;
    }

    /**
     * 返仓是否完成
     *
     * @param soCode
     * @return
     * @throws BusinessException
     */
    public boolean isReturnCompleted(String soCode, String wmsPath) throws BusinessException {
        String uri = wmsPath + URI_IBD_SEACH_SO_BACK;
        String content = JSON.toJSONString(Collections.singletonMap("orderOtherId", soCode));
        logger.info("[POST][URI=" + uri + "] request:" + content);
        StringEntity entity = new StringEntity(content, ContentType.APPLICATION_JSON);//解决中文乱码问题
        String json = HttpClientUtils.post(uri, entity, this.buildHeaders()).getData();
        if (json == null) {
            return false;
        }
        logger.info("[POST][URI=" + uri + "] response:" + json);
        JSONObject obj = JSON.parseObject(json);
        if (obj == null) {
            return false;
        }
        JSONObject body = obj.getJSONObject("body");
        if (body == null) {
            return false;
        }
        return Integer.valueOf(5).equals(body.getInteger("orderStatus"));
    }

    public boolean updateSoStatus(String content) throws BusinessException {
        String uri = this.wmsServerProxyPath + URI_UPDATE_SO_STATUS;
        logger.info("[POST][URI=" + uri + "]" + content);
        StringEntity entity = new StringEntity(content, ContentType.APPLICATION_JSON);//解决中文乱码问题
        String json = HttpClientUtils.post(uri, entity, this.buildHeaders()).getData();
        if (json == null) {
            return false;
        }
        logger.info("[POST][URI=" + uri + "]" + json);
        JSONObject obj = JSON.parseObject(json);
        if (obj == null) {
            return false;
        }
        JSONObject head = obj.getJSONObject("head");
        if (head == null) {
            return false;
        }
        return Integer.valueOf(1).equals(head.getInteger("status"));
    }

    @Getter
    public enum CancelType {
        SO(1), RO(2);

        private int value;

        CancelType(int value) {
            this.value = value;
        }
    }

}
