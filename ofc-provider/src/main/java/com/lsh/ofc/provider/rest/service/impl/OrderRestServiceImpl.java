package com.lsh.ofc.provider.rest.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.api.dto.OrderHeadDTO;
import com.lsh.ofc.api.service.OrderService;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.entity.OfcBill;
import com.lsh.ofc.core.enums.BillType;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.model.Costs;
import com.lsh.ofc.core.service.OfcBillService;
import com.lsh.ofc.provider.rest.service.OrderRestService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangdong on 16/8/28.
 */
@Service(protocol = "rest", validation = "true")
@Path("order")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class OrderRestServiceImpl implements OrderRestService {

    @Autowired
    private OfcBillService ofcBillService;

    @Autowired
    private OrderService orderService;

    @POST
    @Path("/so/create")
    @Override
    public CommonResult<Boolean> createSo(OrderHeadDTO dto) throws BusinessException {
        return this.orderService.createOrder(dto);
    }

    @POST
    @Path("cost/calc")
    @Override
    public CommonResult<Object> calcCosts(String json) throws BusinessException {
        Map<Long, BigDecimal> items = new HashMap<>();
        JSONObject obj = JSON.parseObject(json);
        Long orderCode = obj.getLong("code");
        if (orderCode == null) {
            throw EC.ERROR.exception("\"code\" must not be null!");
        }
        JSONArray skus = obj.getJSONArray("details");
        for (int i = 0; i < skus.size(); i++) {
            JSONObject sku = skus.getJSONObject(i);
            Long skuCode = sku.getLong("sku");
            if (skuCode == null) {
                throw EC.ERROR.exception("\"details[" + i + "].sku\" must not be null!");
            }
            BigDecimal skuQty = sku.getBigDecimal("qty");
            if (skuQty == null) {
                throw EC.ERROR.exception("\"details[" + i + "].qty\" must not be null!");
            }
            BigDecimal qty = items.get(skuCode);
            if (qty == null) {
                qty = skuQty;
            } else {
                qty = qty.add(skuQty);
            }
            items.put(skuCode, qty);
        }
        Costs costs = ofcBillService.calcCosts(orderCode, items);
        return CommonResult.success((Object) costs);
    }

    @POST
    @Path("/obd/consign/query")
    @Override
    public CommonResult<Object> queryConsignOfOBD(Long[] orderCodes) throws BusinessException {
        List<OfcBill> list = ofcBillService.findList(BillType.OBD.name(), Constants.OFC_BILL_SYSTEM_WUMART_SAP_JISHOU, orderCodes);
        Map<String, Object> map = new LinkedHashMap<>();
        for (OfcBill bill : list) {
            JSONArray array = JSON.parseObject(bill.getBillDetails()).getJSONArray(Constants.ATTR_ITEMS);
            List<Object> details = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Map<String, Object> item = new HashMap<>(4);
                item.put(Constants.ATTR_CODE, obj.getString(Constants.ATTR_CODE));
                item.put(Constants.ATTR_QTY, obj.getBigDecimal(Constants.ATTR_QTY));
                details.add(item);
            }
            map.put(bill.getOrderId().toString(), details);
        }
        return CommonResult.success((Object) map);
    }
}
