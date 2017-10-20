package com.lsh123.meipi.ofc.webservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.base.SessionId;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.entity.OfcObdDetail;
import com.lsh.ofc.core.entity.OfcObdHead;
import com.lsh.ofc.core.enums.Region;
import com.lsh.ofc.core.mail.EmailHandler;
import com.lsh.ofc.core.service.OfcObdService;
import com.lsh.ofc.core.util.OFCUtils;
import com.lsh.ofc.proxy.util.MethodCallLogCollector;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@WebService(name = "OFCWebService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class OFCWebService {

    private final Logger logger = Logger.getLogger(this.getClass());

    OfcObdService service;

    EmailHandler emailHandler;

    @WebResult(name = "ret")
    public CallRet putOutboundOrder(@WebParam(name = "head") OutboundOrderHead head, @WebParam(name = "items") Vector<OutboundOrderItem> items) {
        long start = System.currentTimeMillis();
        CallRet ret = new CallRet();
        JSONObject obj = new JSONObject(4);
        obj.put("head", head);
        obj.put("items", items);
        String paramsStr = obj.toJSONString();
        MethodCallLogCollector.init();
        MethodCallLogCollector.params(paramsStr);
        try {

            SessionId.reset();
            logger.info("PUSH OBD start...");
            logger.info("PUSH OBD head=" + paramsStr);

            head.trim();
            for (OutboundOrderItem item : items) {
                item.trim();
            }
            Integer regionCode = null;
            String wm = head.temp5;
            if (StringUtils.hasText(wm)) {
                wm = wm.toUpperCase();
                if (wm.equals("WM10")) {
                    regionCode = Region.Beijing.getCode();
                } else if (wm.equals("WM09")) {
                    regionCode = Region.Tianjin.getCode();
                } else if (wm.equals("WM55") || wm.equals("WM59")) {
                    regionCode = Region.Hangzhou.getCode();
                }
            }

            BigDecimal totalSkuQty = BigDecimal.ZERO;
            List<OfcObdDetail> details = new ArrayList<>(items.size());
            for (OutboundOrderItem item : items) {
                JSONObject json = new JSONObject();
                json.put(Constants.OBD_D_PACK_NUM, ObjectUtils.toString(item.getPackNum()));
                json.put(Constants.OBD_D_BOX_NUM, ObjectUtils.toString(item.getBoxNum()));
                json.put(Constants.OBD_D_LEFT_EA_NUM, ObjectUtils.toString(item.getLeftEANum()));
                BigDecimal skuQty = new BigDecimal(item.getQty());
                OfcObdDetail detail = new OfcObdDetail();
                detail.setSkuSupplyCode(item.getItemId());
                detail.setSkuDeliverQty(skuQty);
                detail.setExt(json.toJSONString());
                details.add(detail);
                totalSkuQty = totalSkuQty.add(skuQty);
            }
            JSONObject json = new JSONObject();
            json.put(Constants.OBD_H_WAYBILL_CODE, ObjectUtils.toString(head.getWaybillId()));
            json.put(Constants.OBD_H_TURNOVER_BOX_NUM, ObjectUtils.toString(head.getTurnoverboxNum()));
            json.put(Constants.OBD_H_BOX_NUM, ObjectUtils.toString(head.getBoxNum()));
            json.put(Constants.OBD_H_DRIVER_INFO, ObjectUtils.toString(head.getDriverInfo()));
            json.put(Constants.OBD_H_VEHICLE_TYPE, ObjectUtils.toString(head.temp1));
            json.put(Constants.OBD_H_VEHICLE_TYPE_DESC, ObjectUtils.toString(head.temp2));
            json.put(Constants.OBD_H_CARRIER_CODE, ObjectUtils.toString(head.temp3));
            json.put(Constants.OBD_H_CARRIER_NAME, ObjectUtils.toString(head.temp4));
            json.put(Constants.OBD_H_CREATE_TIME, ObjectUtils.toString(head.getCreateTime()));
            json.put(Constants.OBD_H_PICK_TIME, ObjectUtils.toString(head.getPTickTime()));
            json.put(Constants.OBD_H_DELIVERY_TIME, ObjectUtils.toString(head.getDeliveryTime()));

            OfcObdHead obd = new OfcObdHead();
            obd.setRegionCode(regionCode);
            obd.setFulfillWms(1);//物美EWM
            obd.setSoCode(head.getSoId());
            obd.setObdCode(head.getObdId());
            obd.setTotalSkuDeliverQty(totalSkuQty);
            obd.setExt(json.toJSONString());
            obd.setDetails(details);
            for (int i = 3; i > 0; i--) {
                try {
                    this.service.create(obd);
                    break;
                } catch (Throwable t) {
                    if (i == 1) {
                        throw t;
                    }
                    continue;
                }
            }
            ret.code = "S";
            logger.info("PUSH OBD end... success!!!");
            MethodCallLogCollector.result(JSON.toJSONString(ret), (int) (System.currentTimeMillis() - start));
        } catch (Throwable e) {
            ret.code = "E";
            if (e instanceof BusinessException) {
                ret.err_msg = e.getMessage();
            } else {
                ret.err_msg = "系统错误";
            }
            logger.error("PUSH OBD end... error!!!", e);
            MethodCallLogCollector.except(e, (int) (System.currentTimeMillis() - start));

            //TODO: 加上预警
            String content = OFCUtils.join("参数：\n", paramsStr, "\n异常：\n", ExceptionUtils.getStackTrace(e));
            this.emailHandler.buildEmail(EmailHandler.EmailTopic.OBD, content);
        } finally {
            SessionId.clear();
            MethodCallLogCollector.upload();
            MethodCallLogCollector.clear();
        }
        return ret;
    }
}
