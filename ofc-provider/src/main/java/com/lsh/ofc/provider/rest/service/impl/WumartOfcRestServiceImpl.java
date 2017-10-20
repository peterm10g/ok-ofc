package com.lsh.ofc.provider.rest.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.core.mail.EmailHandler;
import com.lsh.ofc.core.service.OfcRoCreateService;
import com.lsh.ofc.core.service.OfcSoCreateService;
import com.lsh.ofc.core.util.OFCUtils;
import com.lsh.ofc.provider.rest.service.WumartOfcRestService;
import com.lsh.ofc.proxy.service.WmsServiceProxy;
import com.lsh.ofc.core.proxy.service.WumartOfcServiceProxy;
import com.lsh.ofc.proxy.util.MethodCallLogCollector;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Service(protocol = "rest", timeout = 30000)
@Path("/wumart/ofc")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class WumartOfcRestServiceImpl implements WumartOfcRestService {

    private static final String WMSSO = "WMSSO";
    private static final String BUSINESS_ID = "businessId";
    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private OfcSoCreateService ofcSoCreateService;
    @Autowired
    private OfcRoCreateService ofcRoCreateService;
    @Autowired
    private WumartOfcServiceProxy wumartOfcServiceProxy;
    @Autowired
    private WmsServiceProxy wmsServiceProxy;
    @Autowired
    private EmailHandler emailHandler;

    @POST
    @Path("/redirect")
    @Override
    public CommonResult<Object> redirect(String content) throws BusinessException {
        logger.info("redirect wumart ofc... receive content:" + content);
        JSONObject data = JSON.parseObject(content);
        JSONObject consumer = data.getJSONObject("ofcConsumer");
        if (consumer != null) {
            String bizCode = consumer.getString(BUSINESS_ID);
            if (StringUtils.hasText(bizCode)) {
                consumer.put(BUSINESS_ID, WMSSO + bizCode);
            }
        }
        JSONObject order = data.getJSONObject("ofcOrder");
        if (order != null) {
            JSONArray items = order.getJSONArray("items");
            if (!CollectionUtils.isEmpty(items)) {
                for (int i = 0; i < items.size(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    if (item == null) {
                        continue;
                    }
                    String bizCode = item.getString(BUSINESS_ID);
                    if (StringUtils.hasText(bizCode)) {
                        item.put(BUSINESS_ID, WMSSO + bizCode);
                    }
                }
            }
            String bizCode = order.getString(BUSINESS_ID);
            if (StringUtils.hasText(bizCode)) {
                order.put(BUSINESS_ID, WMSSO + bizCode);
            }
        }
        String json = data.toJSONString();
        logger.info("redirect wumart ofc... redirect content:" + json);
        Object ret = this.wumartOfcServiceProxy.submitMeipiOrder(json);
        return CommonResult.success(ret);
    }

    @POST
    @Path("/so/callback")
    @Override
    public CommonResult<Boolean> callback(String content) throws BusinessException {
        long start = System.currentTimeMillis();
        try {
            logger.info("WumartOfc callback content:" + content);
            JSONObject data = JSON.parseObject(content);
            String bizCode = data.getString("reqCode");
            if (bizCode != null && bizCode.startsWith(WMSSO)) {//WMS请求
                data.put("reqCode", bizCode.substring(5));
                String json = data.toJSONString();
                logger.info("redirect wumart ofc callback... redirect content:" + json);
                boolean ret = this.wmsServiceProxy.updateSoStatus(json);
                return CommonResult.success(ret);
            }

            MethodCallLogCollector.init();
            MethodCallLogCollector.params(content);
            WumartOfcServiceProxy.OrderType orderType = WumartOfcServiceProxy.OrderType.valueOf(data.getInteger("orderType"));
            if (orderType == null) {
                String msg = "orderType(" + data.get("orderType") + ") is error!";
                return CommonResult.error(msg, false);
            }
            CommonResult<Boolean> ret = null;
            EmailHandler.EmailTopic emailTopic = null;
            for (int i = 3; i > 0; i--) {
                try {
                    switch (orderType) {
                        case NORMAL:
                            emailTopic = EmailHandler.EmailTopic.SO_CALLBACK;
                            ret = this.ofcSoCreateService.callback(data);
                            break;
                        case RETURN:
                            emailTopic = EmailHandler.EmailTopic.RO_CALLBACK;
                            ret = this.ofcRoCreateService.callback(data);
                            break;
                        case CANCEL:
                            return CommonResult.success(true, "ignore");
                        default:
                            String msg = "orderType(" + data.get("orderType") + " is not support!";
                            return CommonResult.error(msg, false);
                    }
                    break;
                } catch (Throwable t) {
                    if (i == 1) {
                        String emailContent = OFCUtils.join("参数：\n", data.toJSONString(), "\n异常：\n", ExceptionUtils.getStackTrace(t));
                        this.emailHandler.buildEmail(emailTopic, emailContent);
                        throw t;
                    }
                    continue;
                }
            }

            if (ret != null && !Boolean.TRUE.equals(ret.getData())) {
                String emailContent = OFCUtils.join("参数：\n", data.toJSONString(), "\n异常：\n", ret.getMessage());
                //TODO: 加上预警
                this.emailHandler.buildEmail(emailTopic, emailContent);
            }
            MethodCallLogCollector.result(JSON.toJSONString(ret), (int) (System.currentTimeMillis() - start));

            return ret;
        } catch (Throwable t) {
            MethodCallLogCollector.except(t, (int) (System.currentTimeMillis() - start));
            throw t;
        } finally {
            MethodCallLogCollector.upload();
            MethodCallLogCollector.clear();
        }
    }
}
