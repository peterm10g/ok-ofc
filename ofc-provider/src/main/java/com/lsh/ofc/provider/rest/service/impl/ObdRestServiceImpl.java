package com.lsh.ofc.provider.rest.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.entity.OfcObdDetail;
import com.lsh.ofc.core.entity.OfcObdHead;
import com.lsh.ofc.core.service.OfcObdService;
import com.lsh.ofc.provider.rest.dto.ObdDetailDTO;
import com.lsh.ofc.provider.rest.dto.ObdHeadDTO;
import com.lsh.ofc.provider.rest.service.ObdRestService;
import com.lsh.ofc.proxy.util.MethodCallLogCollector;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service(protocol = "rest", validation = "true")
@Path("/obd")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class ObdRestServiceImpl implements ObdRestService {

    @Autowired
    private OfcObdService ofcObdService;

    @GET
    @Path("/details")
    @Override
    public CommonResult<List<OfcObdDetail>> details(@QueryParam("soBillCode") String soBillCode) throws BusinessException {
        return CommonResult.success(this.ofcObdService.findDtails(soBillCode));
    }

    @POST
    @Path("/push")
    @Override
    public CommonResult<Boolean> push(ObdHeadDTO dto) throws BusinessException {
        long start = System.currentTimeMillis();
        try {
            MethodCallLogCollector.init();
            MethodCallLogCollector.params(JSON.toJSONString(dto));
            List<ObdDetailDTO> list = dto.getDetails();
            List<OfcObdDetail> details = new ArrayList<>(list.size());
            BigDecimal totalSkuQty = BigDecimal.ZERO;
            for (ObdDetailDTO item : list) {
                JSONObject json = new JSONObject();
                json.put(Constants.OBD_D_PACK_NUM, ObjectUtils.toString(item.getPackNum()));
                json.put(Constants.OBD_D_BOX_NUM, ObjectUtils.toString(item.getBoxNum()));
                json.put(Constants.OBD_D_LEFT_EA_NUM, ObjectUtils.toString(item.getLeftEaNum()));
                BigDecimal skuQty = item.getSkuQty();
                OfcObdDetail detail = new OfcObdDetail();
                detail.setSkuSupplyCode(item.getSupplySkuCode());
                detail.setSkuDeliverQty(skuQty);
                detail.setExt(json.toJSONString());
                details.add(detail);
                totalSkuQty = totalSkuQty.add(skuQty);
            }

            JSONObject json = new JSONObject();
            json.put(Constants.OBD_H_WAYBILL_CODE, ObjectUtils.toString(dto.getWaybillCode()));
            json.put(Constants.OBD_H_TURNOVER_BOX_NUM, ObjectUtils.toString(dto.getTurnoverBoxNum()));
            json.put(Constants.OBD_H_BOX_NUM, ObjectUtils.toString(dto.getBoxNum()));
            json.put(Constants.OBD_H_DRIVER_INFO, ObjectUtils.toString(dto.getDriverInfo()));
            json.put(Constants.OBD_H_VEHICLE_TYPE, ObjectUtils.toString(dto.getVehicleType()));
            json.put(Constants.OBD_H_VEHICLE_TYPE_DESC, ObjectUtils.toString(dto.getVehicleTypeDesc()));
            json.put(Constants.OBD_H_CARRIER_CODE, ObjectUtils.toString(dto.getCarrierCode()));
            json.put(Constants.OBD_H_CARRIER_NAME, ObjectUtils.toString(dto.getCarrierName()));
            json.put(Constants.OBD_H_CREATE_TIME, ObjectUtils.toString(dto.getCreateTime()));
            json.put(Constants.OBD_H_PICK_TIME, ObjectUtils.toString(dto.getPickTime()));
            json.put(Constants.OBD_H_DELIVERY_TIME, ObjectUtils.toString(dto.getDeliveryTime()));
            json.put(Constants.OBD_H_SCATTERED_BOX_NUM, ObjectUtils.toString(dto.getScatteredBoxNum()));

            OfcObdHead head = new OfcObdHead();
            head.setFulfillWms(dto.getWms());
            head.setSoCode(dto.getSoCode());
            head.setObdCode(dto.getObdCode());
            head.setWarehouseCode(dto.getWarehouseCode());
            head.setTotalSkuDeliverQty(totalSkuQty);
            head.setExt(json.toJSONString());
            head.setDetails(details);

            this.ofcObdService.create(head);
            MethodCallLogCollector.result(Boolean.TRUE.toString(), (int) (System.currentTimeMillis() - start));
            return CommonResult.success(true);
        } catch (Throwable t) {
            MethodCallLogCollector.except(t, (int) (System.currentTimeMillis() - start));
            throw t;
        } finally {
            MethodCallLogCollector.upload();
            MethodCallLogCollector.clear();
        }
    }
}
