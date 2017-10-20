package com.lsh.ofc.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.dao.OfcBillDAO;
import com.lsh.ofc.core.entity.OfcBill;
import com.lsh.ofc.core.entity.OfcObdDetail;
import com.lsh.ofc.core.entity.OfcObdHead;
import com.lsh.ofc.core.entity.OfcReturnDetail;
import com.lsh.ofc.core.entity.OfcReturnHead;
import com.lsh.ofc.core.entity.OfcRoHead;
import com.lsh.ofc.core.entity.OfcSoDetail;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.enums.BillType;
import com.lsh.ofc.core.enums.FulfillWms;
import com.lsh.ofc.core.enums.Region;
import com.lsh.ofc.core.enums.RoStatus;
import com.lsh.ofc.core.enums.SupplierOrg;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.model.Costs;
import com.lsh.ofc.core.service.OfcBillService;
import com.lsh.ofc.core.util.OFCUtils;
import com.lsh.ofc.proxy.model.CreateSoReqDetail;
import com.lsh.ofc.proxy.model.CreateSoReqHead;
import com.lsh.ofc.proxy.model.Goods;
import com.lsh.ofc.proxy.model.ObdHead;
import com.lsh.ofc.proxy.service.GoodsServiceProxy;
import com.lsh.ofc.proxy.service.RiskServiceProxy;
import com.lsh.ofc.core.proxy.service.WumartOfcServiceProxy;
import com.lsh.ofc.core.proxy.service.WumartSapServiceProxy;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class OfcBillServiceImpl implements OfcBillService {

    private final Logger logger = Logger.getLogger(this.getClass());

    private static final String BILL_STATUS_NORMAL = "NORMAL";

    private static final String BILL_STATUS_SUBPART_CREATE = "SUBPART_CREATE";

    private static final String BILL_STATUS_NONE_CREATE = "NONE_CREATE";

    private static final String BILL_SYSTEM_WUMART_SAP = "WUMART_SAP";

    private static final String BILL_SYSTEM_WUMART_SAP_JISHOU = "WUMART_SAP_JISHOU";


    private static final String ATTR_ITEMS = "items";

    private static final String ATTR_ZONE_ID = "zone_id";

    private static final String ATTR_LINE_NO = "line_no";

    private static final String ATTR_SKU_ID = "sku_id";

    private static final String ATTR_LSH_SKU_ID = "lsh_sku_id";

    private static final String ATTR_CODE = "code";

    private static final String ATTR_QTY = "qty";

    private static final String ATTR_TAX_RATE = "tax_rate";

    private static final String ATTR_SUPPLY_PRICE = "supply_price";

    @Autowired
    private OfcBillDAO ofcBillDAO;

    @Autowired
    private GoodsServiceProxy goodsServiceProxy;

    @Autowired
    private RiskServiceProxy riskServiceProxy;

    @Autowired
    private WumartSapServiceProxy wumartSapServiceProxy;

    @Autowired
    private WumartOfcServiceProxy wumartOfcServiceProxy;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public List<OfcBill> findList(String billType, String system, Long... orderCodes) throws BusinessException {
        if (ArrayUtils.isEmpty(orderCodes)) {
            return Collections.emptyList();
        }
        List<OfcBill> list = new ArrayList<>(orderCodes.length);
        OfcBill filter = new OfcBill();
        filter.setBillType(billType);
        filter.setSystem(system);
        for (Long orderCode : orderCodes) {
            filter.setOrderId(orderCode);
            list.addAll(this.ofcBillDAO.findList(filter));
        }
        return list;
    }

    @Override
    public List<OfcBill> findList(OfcBill filter) throws BusinessException {
        return this.ofcBillDAO.findList(filter);
    }

    @Override
    public void insert(OfcSoHead so) throws BusinessException {
        if (so == null) {
            return;
        }
        List<OfcSoDetail> details = so.getDetails();
        if (CollectionUtils.isEmpty(details)) {
            return;
        }
        JSONArray items = new JSONArray();
        DecimalFormat format = new DecimalFormat("000000");
        for (OfcSoDetail detail : details) {
            if (detail == null) {
                continue;
            }
            JSONObject ext = JSON.parseObject(detail.getExt());
            Map<String, String> item = new HashMap<>();
            item.put("sku_id", detail.getSkuSupplyCode());
            item.put("qty", detail.getSkuOrderQty().toString());
            item.put("type", ext.getString(Constants.SO_D_TYPE));
            item.put("lsh_sku_id", detail.getGoodsCode().toString());
            item.put("tax_rate", detail.getTaxRate().toString());
            item.put("supply_price", detail.getSkuSupplyPrice().toString());
            item.put("obd", ext.getString(Constants.SO_D_OBD));
            item.put("line_no", format.format(detail.getItemNo()));
            item.put("o_qty", detail.getSkuSupplyQty().toString());
            items.add(item);
        }
        JSONObject json = new JSONObject();
        json.put("items", items);
        json.put("so_user_id", JSON.parseObject(so.getExt()).get(Constants.SO_H_MP_CUST_CODE));
        json.put("zone_id", so.getRegionCode().toString());

        //插入OFCBill记录
        String billStatus;
        BigDecimal totalSkuSupplyQty = so.getTotalSkuSupplyQty();
        if (totalSkuSupplyQty == null || BigDecimal.ZERO.compareTo(totalSkuSupplyQty) == 0) {
            billStatus = BILL_STATUS_NONE_CREATE;
        } else if (totalSkuSupplyQty.compareTo(so.getTotalSkuOrderQty()) < 0) {
            billStatus = BILL_STATUS_SUBPART_CREATE;
        } else {
            billStatus = BILL_STATUS_NORMAL;
        }
        String soCode = so.getSoCode();
        String jsonstr = json.toJSONString();
        OfcBill bill = new OfcBill();
        bill.setOrderId(so.getOrderCode());
        bill.setSystem((SupplierOrg.isConsign(so.getSupplierOrg()) ? BILL_SYSTEM_WUMART_SAP_JISHOU : BILL_SYSTEM_WUMART_SAP));
        bill.setOutOrderId(soCode);
        bill.setBillType(BillType.ORDER.name());
        bill.setBillId(soCode);
        bill.setBillDetails(jsonstr);
        bill.setBillStatus(billStatus);
        this.ofcBillDAO.insert(bill);

        //通知风控系统
        {
            try {
                boolean ret = this.riskServiceProxy.report(jsonstr);
                if (!ret) {
                    throw EC.ERROR.exception("通知风控系统失败！");
                }
            } catch (Exception e) { //TODO:风控超级不靠谱！
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void insert(OfcObdHead obd) throws BusinessException {
        if (obd == null) {
            return;
        }
        JSONObject hext = JSON.parseObject(obd.getExt());
        JSONArray items = new JSONArray();
        List<OfcObdDetail> details = obd.getDetails();
        for (OfcObdDetail detail : details) {
            JSONObject dext = JSON.parseObject(detail.getExt());
            JSONObject obj = new JSONObject();
            obj.put("code", detail.getSkuSupplyCode());
            obj.put("qty", detail.getSkuDeliverQty().toString());
            obj.put("waybill_id", hext.getString(Constants.OBD_H_WAYBILL_CODE));
            obj.put("driver_info", hext.getString(Constants.OBD_H_DRIVER_INFO));
            obj.put("pack_num", dext.getString(Constants.OBD_D_PACK_NUM));
            obj.put("box_num", dext.getString(Constants.OBD_D_BOX_NUM));
            obj.put("left_ea_num", dext.getString(Constants.OBD_D_LEFT_EA_NUM));
            items.add(obj);
        }

        JSONObject json = new JSONObject();
        json.put("system", BILL_SYSTEM_WUMART_SAP);
        json.put("type", BillType.OBD.name());
        json.put("order_id", obd.getOrderCode().toString());
        json.put("out_order_id", obd.getSoCode());
        json.put("bill_id", obd.getObdCode());
        json.put("waybill_id", hext.getString(Constants.OBD_H_WAYBILL_CODE));
        json.put("driver_info", hext.getString(Constants.OBD_H_DRIVER_INFO));
        json.put("amount", obd.getCostAmount());
        json.put("nt_amount", obd.getCostNtAmount());
        json.put("create_time", hext.getString(Constants.OBD_H_CREATE_TIME));
        json.put("delivery_time", hext.getString(Constants.OBD_H_DELIVERY_TIME));
        json.put("pick_time", hext.getString(Constants.OBD_H_PICK_TIME));
        json.put("box_num", hext.getString(Constants.OBD_H_BOX_NUM));
        json.put("turnoverbox_num", hext.getString(Constants.OBD_H_TURNOVER_BOX_NUM));
        json.put("so_user_id", hext.getString(Constants.OBD_H_MP_CUST_CODE));
        json.put("car_type", hext.getString(Constants.OBD_H_VEHICLE_TYPE));
        json.put("car_type_desc", hext.getString(Constants.OBD_H_VEHICLE_TYPE_DESC));
        json.put("carrier_code", hext.getString(Constants.OBD_H_CARRIER_CODE));
        json.put("carrier_name", hext.getString(Constants.OBD_H_CARRIER_NAME));
        json.put("lack_qty", obd.getTotalSkuDeliverQty().subtract(obd.getTotalSkuSupplyQty()).toString()); //发货数 - 创建数
        json.put("items", items);

        OfcBill bill = new OfcBill();
        bill.setOrderId(obd.getOrderCode());
        bill.setSystem((SupplierOrg.isConsign(obd.getSupplierOrg()) ? BILL_SYSTEM_WUMART_SAP_JISHOU : BILL_SYSTEM_WUMART_SAP));
        bill.setOutOrderId(obd.getSoCode());
        bill.setBillType(BillType.OBD.name());
        bill.setBillId(obd.getObdCode());
        bill.setBillDetails(json.toString());
        bill.setBillStatus(BILL_STATUS_NORMAL);
        this.ofcBillDAO.insert(bill);
    }

    @Override
    public void insert(OfcRoHead ro) throws BusinessException {
        if (ro == null) {
            return;
        }
        if (SupplierOrg.isConsign(ro.getSupplierOrg())) { //寄售不记录
            return;
        }
        String roCode = ro.getRoCode();
        OfcBill bill = new OfcBill();
        bill.setOrderId(ro.getReturnCode());
        bill.setSystem(BILL_SYSTEM_WUMART_SAP);
        bill.setOutOrderId(roCode);
        bill.setBillType(BillType.ORDER.name());
        bill.setBillId(roCode);
        bill.setBillDetails(JSON.toJSONString(Collections.singletonMap(ATTR_ZONE_ID, ro.getRegionCode())));
        bill.setBillStatus(BILL_STATUS_NORMAL);
        this.ofcBillDAO.insert(bill);
    }


    @Override
    public Costs calcCosts(Long orderCode, Map<Long, BigDecimal> items) throws BusinessException {
        if (orderCode == null) {
            logger.error("订单号为空！");
            throw EC.ORDER_CODE_IS_NULL.exception();
        }
        if (CollectionUtils.isEmpty(items)) {
            logger.error("商品明细为空！");
            throw EC.ORDER_DETAILS_IS_EMPTY.exception();
        }

        OfcBill filter = new OfcBill();
        filter.setOrderId(orderCode);
        filter.setBillType(BillType.ORDER.name());
        filter.setSystem(BILL_SYSTEM_WUMART_SAP);
        List<OfcBill> sos = this.findList(filter);
        if (CollectionUtils.isEmpty(sos)) {
            logger.warn("SO单信息不存在！订单号=" + orderCode);
            return Costs.ZERO;
        } else if (sos.size() != 1) {
            throw EC.SO_ORDER_IS_ERROR.exception("订单号=" + orderCode);
        }

        filter.setBillType(BillType.OBD.name());
        int count = this.ofcBillDAO.count(filter);
        if (count == 0) {
            logger.warn("OBD单信息不存在！订单号=" + orderCode);
            return Costs.ZERO;
        } else if (count != 1) {
            throw EC.SO_OBD_IS_ERROR.exception("订单号=" + orderCode);
        }

        OfcBill so = sos.get(0);
        JSONObject json = JSON.parseObject(so.getBillDetails());
        JSONArray array = json.getJSONArray("items");
        List<Long> goodsCodes = new ArrayList<>(items.size());
        for (int i = 0; i < array.size(); ++i) {
            goodsCodes.add(array.getJSONObject(i).getLong("lsh_sku_id"));
        }
        String soCode = so.getOutOrderId();
        Integer regionCode = json.getInteger("zone_id");
        Integer supplierId = OFCUtils.getSupplierId(regionCode, false);
        logger.info("SO【" + soCode + "】【" + regionCode + "】查询商品信息开始，supplierId=" + supplierId + ", goodsCodes=" + goodsCodes);
        Map<Long, Goods> goodsMap = this.goodsServiceProxy.getGoodsInfoMapByGoodsCodes(goodsCodes, supplierId);
        logger.info("SO【" + soCode + "】【" + regionCode + "】查询商品信息结果，supplierId=" + supplierId + ", goodsMap=" + JSON.toJSONString(goodsMap));
        Map<String, BigDecimal> map = new HashMap<>();
        Map<Long, BigDecimal> itemMap = new HashMap<>(items);
        for (Goods goods : goodsMap.values()) {
            Long skuCode = goods.getSkuCode();
            BigDecimal skuQty = itemMap.remove(skuCode);
            if (skuQty == null) {
                continue;
            }
            if (skuQty.compareTo(BigDecimal.ZERO) <= 0) {
                throw EC.ERROR_PARAMS.exception("SKU数量不能为空或者小于0！skuCode=" + skuCode);
            }
            String supplySkuCode = goods.getSupplySkuCode();
            BigDecimal qty = map.get(supplySkuCode);
            if (qty == null) {
                qty = skuQty;
            } else {
                qty = qty.add(skuQty);
            }
            map.put(supplySkuCode, qty);
            if (itemMap.isEmpty()) {
                break;
            }
        }
        if (!itemMap.isEmpty()) {
            throw EC.GOODS_IS_ERROR.exception("对应货主商品不存在！skuCodes=" + items.keySet());
        }
        return calc(map, so.getBillDetails());
    }

    private Costs calc(Map<String, BigDecimal> items, String json) {
        BigDecimal amountSum = BigDecimal.ZERO;
        BigDecimal ntAmountSum = BigDecimal.ZERO;
        if (!StringUtils.hasText(json)) {
            return Costs.ZERO;
        }
        Map<String, BigDecimal> map = new HashMap<>(items);
        JSONArray array = JSON.parseObject(json).getJSONArray(ATTR_ITEMS);
        for (int i = 0; i < array.size(); i++) {
            if (map.size() == 0) {
                break;
            }
            JSONObject item = array.getJSONObject(i);
            if (item == null) {
                continue;
            }
            String code = item.getString(ATTR_SKU_ID);
            BigDecimal qty = map.remove(code);
            if (qty == null) {
                continue;
            }
            BigDecimal taxRate = new BigDecimal(item.getString(ATTR_TAX_RATE));
            BigDecimal supplyPrice = new BigDecimal(item.getString(ATTR_SUPPLY_PRICE));
            BigDecimal itAmount = supplyPrice.multiply(qty);
            BigDecimal ntAmount = itAmount.divide(BigDecimal.ONE.add(taxRate), 2, BigDecimal.ROUND_HALF_UP);
            amountSum = amountSum.add(itAmount);
            ntAmountSum = ntAmountSum.add(ntAmount);
        }
        amountSum = amountSum.setScale(2, BigDecimal.ROUND_HALF_UP);
        ntAmountSum = ntAmountSum.setScale(2, BigDecimal.ROUND_HALF_UP);
        return new Costs(amountSum, ntAmountSum);
    }

    @Override
    public String createBill4Return(OfcReturnHead model) throws BusinessException {
        if (model == null) {
            throw EC.RETURN_ORDER_IS_NULL.exception();
        }
        List<OfcReturnDetail> details = model.getDetails();
        if (CollectionUtils.isEmpty(details)) {
            throw EC.RETURN_ORDER_DETAILS_IS_EMPTY.exception();
        }
        Long returnCode = model.getReturnCode();
        OfcBill filter = new OfcBill();
        filter.setOrderId(returnCode);
        filter.setBillType(BillType.ORDER.name());
        List<OfcBill> orders = this.ofcBillDAO.findList(filter);
        if (!CollectionUtils.isEmpty(orders)) {
            logger.warn("退货SO已创建完成！退货单号=" + returnCode);
            return orders.get(0).getOutOrderId();
        }

        Long orderCode = model.getOrderCode();
        filter = new OfcBill();
        filter.setOrderId(orderCode);
        filter.setBillType(BillType.ORDER.name());
        filter.setSystem(BILL_SYSTEM_WUMART_SAP);
        List<OfcBill> list = this.ofcBillDAO.findList(filter);
        if (CollectionUtils.isEmpty(list)) {
            BusinessException e = EC.SO_ORDER_NOT_EXIST.exception("订单号=" + orderCode);
            logger.error(e.getMessage());
            throw e;
        }

        if (list.size() != 1) {
            BusinessException e = EC.SO_ORDER_IS_ERROR.exception("订单号=" + orderCode);
            logger.error(e.getMessage());
            throw e;
        }
        OfcBill bill = list.get(0);
        if (!StringUtils.hasText(bill.getOutOrderId())) {
            BusinessException e = EC.SO_ORDER_IS_ERROR.exception("订单号=" + orderCode);
            logger.error(e.getMessage() + "... 原SO单数据错误！");
            throw e;
        }

        Map<String, String> mapRefIdx = new HashMap<>();
        JSONObject json = JSON.parseObject(bill.getBillDetails());
        JSONArray items = json.getJSONArray(Constants.ATTR_ITEMS);
        List<Long> goodsCodes = new ArrayList<>(items.size());
        for (int i = 0; i < items.size(); ++i) {
            JSONObject item = items.getJSONObject(i);
            mapRefIdx.put(item.getString(ATTR_SKU_ID), item.getString(ATTR_LINE_NO));
            goodsCodes.add(item.getLong(ATTR_LSH_SKU_ID));
        }

        Integer regionCode = json.getInteger(ATTR_ZONE_ID);
        Integer supplierId = OFCUtils.getSupplierId(regionCode, false);
        logger.info("SO【" + bill.getOutOrderId() + "】【" + regionCode + "】查询商品信息开始，supplierId=" + supplierId + ", goodsCodes=" + goodsCodes);
        Map<Long, Goods> goodsMap = this.goodsServiceProxy.getGoodsInfoMapByGoodsCodes(goodsCodes, supplierId);
        logger.info("SO【" + bill.getOutOrderId() + "】【" + regionCode + "】查询商品信息结果，supplierId=" + supplierId + ", goodsMap=" + JSON.toJSONString(goodsMap));
        Map<Long, Goods> skuMap = new HashMap<>();
        for (Goods goods : goodsMap.values()) {
            skuMap.put(goods.getSkuCode(), goods);
        }

        List<CreateSoReqDetail> sodetails = new ArrayList<>();
        for (OfcReturnDetail detail : details) {
            Goods goods = skuMap.get(detail.getSkuCode());
            if (goods == null) {
                continue;
            }
            String outSkuCode = goods.getSupplySkuCode();
            String lineNo = mapRefIdx.get(outSkuCode);
            if (lineNo == null) {
                continue;
            }
            CreateSoReqDetail de = new CreateSoReqDetail();
            de.setItemNum(lineNo);
            de.setSkuCode(outSkuCode);
            de.setQuality(detail.getSkuQty());
            de.setAmount(detail.getGoodsAmount());
            sodetails.add(de);
        }
        if (sodetails.isEmpty()) {
            throw EC.ERROR.exception("没有非寄售品，无法提交物美SO");
        }
        CreateSoReqHead order = new CreateSoReqHead();
        order.setSoCode(bill.getOutOrderId());
        order.setOrderCode(model.getOrderCode().toString()); //TODO
        order.setWarehouse(model.getWarehouseCode());
        order.setRegionCode(model.getRegionCode());
        order.setSoUserCode(json.getString("so_user_id"));
        order.setFulfillWms(FulfillWms.Wumart.getValue());
        order.setDetails(sodetails);

        logger.info("SO【" + bill.getOutOrderId() + "】创建返仓SO,订单号=" + orderCode + ", params=" + JSON.toJSONString(order));
        String soCode = this.wumartSapServiceProxy.createMeipiRo(order, bill.getOutOrderId(), JSON.parseObject(model.getExt()).getInteger(Constants.RETURN_H_BATCH) == 1).getCode();

        OfcBill entity = new OfcBill();
        entity.setOrderId(returnCode);
        entity.setSystem(BILL_SYSTEM_WUMART_SAP);
        entity.setOutOrderId(soCode);
        entity.setBillType(BillType.ORDER.name());
        entity.setBillId(soCode);
        entity.setBillDetails(JSON.toJSONString(Collections.singletonMap(ATTR_ZONE_ID, regionCode)));
        entity.setBillStatus(BILL_STATUS_NORMAL);
        this.ofcBillDAO.insert(entity);
        this.executor.submit(new WumartOfcReturnTask(this.wumartOfcServiceProxy, order, returnCode));

        return soCode;
    }

    @Override
    public Map<String, Integer> queryReturnStatus(Long returnCode) throws BusinessException {
        OfcBill filter = new OfcBill();
        filter.setOrderId(returnCode);
        filter.setBillType(BillType.ORDER.name());
        filter.setSystem(BILL_SYSTEM_WUMART_SAP);
        OfcBill bill = this.ofcBillDAO.findOne(filter);
        if (bill == null) {
            throw EC.SO_ORDER_NOT_EXIST.exception("退货单号=" + returnCode);
        }

        Integer regionCode = null;
        String json = bill.getBillDetails();
        if (!StringUtils.isEmpty(json)) {
            regionCode = JSON.parseObject(json).getInteger(ATTR_ZONE_ID);
        }
        if (regionCode == null) {
            regionCode = Region.Beijing.getCode();
        }
        ObdHead ret;
        try {
            logger.info("查询返仓结果开始，RO单号=" + bill.getOutOrderId());
            ret = this.wumartSapServiceProxy.queryObdStatus4Ro(bill.getOutOrderId(), regionCode);
            logger.info("查询返仓结果完成，RO单号=" + bill.getOutOrderId() + ", ret=" + ret);
        } catch (BusinessException e) {
            logger.error("查询返仓结果异常...RO单号=" + bill.getOutOrderId() + " ..." + e.getMessage(), e);
            return Collections.singletonMap(bill.getOutOrderId(), RoStatus.CREATED.getValue());
        } catch (Throwable e) {
            logger.error("查询返仓结果异常，RO单号=" + bill.getOutOrderId() + " ..." + e.getMessage(), e);
            throw new BusinessException(EC.ERROR.getCode(), e.getMessage(), e);
        }

        if (ret == null || CollectionUtils.isEmpty(ret.getDetails())) {
            logger.info("退货OBD记录已存在！退货单号=" + returnCode);
            return Collections.singletonMap(bill.getOutOrderId(), RoStatus.CREATED.getValue());
        }
        return Collections.singletonMap(bill.getOutOrderId(), RoStatus.COMPLETED.getValue());
    }

    private class WumartOfcReturnTask implements Runnable {

        private final WumartOfcServiceProxy proxy;

        private final CreateSoReqHead so;

        private final Long batchNo;

        public WumartOfcReturnTask(WumartOfcServiceProxy proxy, CreateSoReqHead so, Long batchNo) {
            this.proxy = proxy;
            this.so = so;
            this.batchNo = batchNo;
        }

        @Override
        public void run() {
            try {
                logger.info("物美OFC返仓履约开始...SO=" + JSON.toJSONString(this.so));
                boolean ret = proxy.submitMeipiOrder(WumartOfcServiceProxy.OrderType.RETURN,this.so, batchNo);
                logger.info("物美OFC返仓履约结束(" + ret + ")...SO=" + JSON.toJSONString(this.so));
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }
    }
}