package com.lsh.ofc.provider.rest.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.entity.OfcReturnDetail;
import com.lsh.ofc.core.entity.OfcReturnHead;
import com.lsh.ofc.core.entity.OfcRoHead;
import com.lsh.ofc.core.enums.RoStatus;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.service.OfcBillService;
import com.lsh.ofc.core.service.OfcRoCreateService;
import com.lsh.ofc.core.service.OfcRoService;
import com.lsh.ofc.provider.rest.dto.ReturnDetailDTO;
import com.lsh.ofc.provider.rest.dto.ReturnHeadDTO;
import com.lsh.ofc.provider.rest.service.ReturnRestService;
import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service(protocol = "rest", validation = "true")
@Path("return")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class ReturnRestServiceImpl implements ReturnRestService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private OfcRoService ofcRoService;

    @Autowired
    private OfcRoCreateService ofcRoCreateService;

    @Autowired
    private OfcBillService ofcBillService;

    @POST
    @Path("/ro/create")
    @Override
    public CommonResult<List<String>> createRo(ReturnHeadDTO dto) throws BusinessException {
        Long returnCode = dto.getReturnCode();
        OfcRoHead filter = new OfcRoHead();
        filter.setReturnCode(returnCode);
        List<OfcRoHead> ros = this.ofcRoService.findList(filter, false);
        if (CollectionUtils.isEmpty(ros)) {
            BigDecimal totalSkuQty = BigDecimal.ZERO;
            List<OfcReturnDetail> details = new ArrayList<>();
            for (ReturnDetailDTO item : dto.getDetails()) {
                BigDecimal skuQty = item.getSkuQty();
                OfcReturnDetail detail = new OfcReturnDetail();
                detail.setReturnCode(returnCode);
                detail.setGoodsCode(item.getGoodsCode());
                detail.setGoodsName(item.getGoodsName());
                detail.setGoodsSaleUnit(item.getGoodsSaleUnit());
                detail.setGoodsPrice(item.getGoodsPrice());
                detail.setGoodsQty(item.getGoodsQty());
                detail.setGoodsAmount(item.getGoodsAmount());
                detail.setSkuCode(item.getSkuCode());
                detail.setSkuQty(skuQty);
                details.add(detail);
                totalSkuQty = totalSkuQty.add(skuQty);
            }
            JSONObject ext = new JSONObject();
            ext.put(Constants.RETURN_H_BATCH, dto.getCount());
            OfcReturnHead head = new OfcReturnHead();
            head.setOrderCode(dto.getOrderCode());
            head.setReturnCode(dto.getReturnCode());
            head.setRegionCode(dto.getRegionCode());
            head.setAddressCode(dto.getAddressCode());
            head.setOrderAmount(dto.getOrderAmount());
            head.setOrderTime(dto.getCreateTime());
            head.setWarehouseCode(dto.getWarehouseCode());
            head.setWarehouseName(dto.getWarehouseName());
            head.setTotalSkuReturnQty(totalSkuQty);
            head.setExt(ext.toJSONString());
            head.setDetails(details);
            try {
                ros = this.ofcRoCreateService.prepare(head);
            } catch (BusinessException e) {
                if (EC.SO_OBD_NOT_EXIST.getCode().equals(e.getCode())) {
                    String ret = this.ofcBillService.createBill4Return(head);
                    if (ret == null) {
                        CommonResult.error("已经创建两笔退货单");
                    }
                    return CommonResult.success(Collections.singletonList(ret));
                } else {
                    throw e;
                }
            }
        }
        List<String> billCodes = new ArrayList<>(ros.size());
        for (OfcRoHead ro : ros) {
            billCodes.add(ro.getRoBillCode());
        }
        return CommonResult.success(billCodes);
    }

    @GET
    @Path("/ro/status/query")
    @Override
    public CommonResult<Object> queryRoStatus(@QueryParam("returnCode") Long returnCode) throws BusinessException {
        OfcRoHead filter = new OfcRoHead();
        filter.setReturnCode(returnCode);
        List<OfcRoHead> roList = this.ofcRoService.findList(filter, false);
        if (CollectionUtils.isEmpty(roList)) {
            Map<String, Integer> ret = this.ofcBillService.queryReturnStatus(returnCode);
            JSONObject data = new JSONObject();
            data.put("returnCode", returnCode);
            data.put("roCodes", ret.keySet());
            JSONArray ros = new JSONArray();
            for (Map.Entry<String, Integer> entry : ret.entrySet()) {
                String roCode = entry.getKey();
                Integer roStatus = entry.getValue();
                JSONObject obj = new JSONObject();
                obj.put("billCode", roCode);
                obj.put("roCode", roCode);
                obj.put("roStatus", roStatus);
                data.put("status", roStatus);
                ros.add(obj);
            }
            data.put("ros", ros);
            return CommonResult.success((Object) data);
        }

        Set<Integer> roStatusSet = new HashSet<>();
        JSONArray roCodes = new JSONArray();
        JSONArray ros = new JSONArray();
        for (OfcRoHead ro : roList) {
            String roCode = ObjectUtils.toString(ro.getRoCode());
            if (!roCode.isEmpty()) {
                roCodes.add(roCode);
            }
            Integer roStatus = ro.getRoStatus();
            if (RoStatus.CREATED.getValue().equals(roStatus)) {
                try {
                    roStatus = this.ofcRoService.refreshRoStatus(ro);
                } catch (BusinessException e) {
                }
            }
            JSONObject obj = new JSONObject();
            obj.put("billCode", ro.getRoBillCode());
            obj.put("roStatus", roStatus);
            obj.put("roCode", roCode);
            ros.add(obj);
            roStatusSet.add(roStatus);
        }
        //15-提交中；20-提交成功；25-提交失败；30-返仓完成
        int status = RoStatus.COMPLETED.getValue(); //30
        roStatusSet.remove(RoStatus.IGNORED.getValue());
        if (!roStatusSet.isEmpty()) {
            List<Integer> list = new ArrayList<>(roStatusSet);
            Collections.sort(list);
            status = list.get(0);
            if (status < RoStatus.CREATED.getValue().intValue()) {
                status = RoStatus.CREATING.getValue(); //15-提交中
            } else if (status == RoStatus.CREATE_FAIL.getValue().intValue()) {
                status = RoStatus.CREATE_FAIL.getValue(); //25-提交失败
            } else if (status == RoStatus.COMPLETED.getValue().intValue()) {
                status = RoStatus.COMPLETED.getValue(); //30-返仓完成
            } else {
                status = RoStatus.CREATED.getValue();
            }
        }
        JSONObject data = new JSONObject();
        data.put("returnCode", returnCode);
        data.put("roCodes", roCodes);
        data.put("status", status);
        data.put("ros", ros);
        return CommonResult.success((Object) data);
    }
}
