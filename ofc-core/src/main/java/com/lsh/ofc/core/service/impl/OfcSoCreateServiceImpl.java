package com.lsh.ofc.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.entity.OfcCustomer;
import com.lsh.ofc.core.entity.OfcOrderDetail;
import com.lsh.ofc.core.entity.OfcOrderHead;
import com.lsh.ofc.core.entity.OfcSoDetail;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.enums.FulfillChannel;
import com.lsh.ofc.core.enums.FulfillWms;
import com.lsh.ofc.core.enums.OfcTaskType;
import com.lsh.ofc.core.enums.SoStatus;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.handler.AsyncTaskHandler;
import com.lsh.ofc.core.handler.CreateSoHandler;
import com.lsh.ofc.core.service.OfcCustomerService;
import com.lsh.ofc.core.service.OfcOrderService;
import com.lsh.ofc.core.service.OfcSoCreateService;
import com.lsh.ofc.core.service.OfcSoService;
import com.lsh.ofc.core.service.OfcSoSplitService;
import com.lsh.ofc.core.util.OFCUtils;
import com.lsh.ofc.proxy.service.OmsServiceProxy;
import com.lsh.ofc.proxy.service.TmsServiceProxy;
import com.lsh.ofc.proxy.util.MethodCallLogCollector;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by huangdong on 16/9/10.
 */
@Service
public class OfcSoCreateServiceImpl implements OfcSoCreateService {

    private final Logger logger = Logger.getLogger(this.getClass());

    private ExecutorService executor = Executors.newFixedThreadPool(20);

    @Autowired
    private OfcOrderService ofcOrderService;

    @Autowired
    private OfcSoService ofcSoService;

    @Autowired
    private OfcSoSplitService ofcSoSplitService;

    @Autowired
    private OfcCustomerService ofcCustomerService;

    @Autowired
    private OmsServiceProxy omsServiceProxy;

    @Autowired
    private TmsServiceProxy tmsServiceProxy;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AsyncTaskHandler asyncTaskHandler;

    @Transactional
    @Override
    public List<OfcSoHead> prepare(OfcOrderHead order) throws BusinessException {
        if (order == null) {
            throw EC.ERROR.exception("入参订单信息为空！");
        }
        List<OfcOrderDetail> details = order.getDetails();
        if (CollectionUtils.isEmpty(details)) {
            throw EC.ERROR.exception("入参订单明细为空！");
        }

        Long orderCode = order.getOrderCode();
        OfcSoHead filter = new OfcSoHead();
        filter.setOrderCode(orderCode);
        List<OfcSoHead> sos = this.ofcSoService.findList(filter, true);
        if (!CollectionUtils.isEmpty(sos)) {
            return sos;
        }
        //订单拆分
        sos = this.ofcSoSplitService.split(order);

        //TODO:目前不涉及多物理仓库履约拆单
        //更新订单仓库信息和明细扩展信息
        int ret = this.updateOrder4Prepare(order, sos.get(0).getWarehouseCode(), sos.get(0).getWarehouseName());
        if (ret != 1) { //TODO:防重入
            throw EC.ERROR.exception("订单不存在，或者订单已拆单！订单号=" + orderCode);
        }

        //插入SO信息
        this.ofcSoService.insert(sos);
        return sos;
    }

    /**
     * 更新OFC订单信息
     *
     * @param order
     * @param warehouseCode
     * @param warehouseName
     * @return
     * @throws BusinessException
     */
    private int updateOrder4Prepare(OfcOrderHead order, String warehouseCode, String warehouseName) throws BusinessException {
        OfcOrderHead filter = new OfcOrderHead();
        filter.setId(order.getId());
        filter.setOrderCode(order.getOrderCode());
        filter.setWarehouseCode("");
        filter.setWarehouseName("");

        OfcOrderHead updateHead = new OfcOrderHead();
        updateHead.setWarehouseCode(warehouseCode);
        updateHead.setWarehouseName(warehouseName);

        List<OfcOrderDetail> details = order.getDetails();
        if (!CollectionUtils.isEmpty(details)) {
            List<OfcOrderDetail> updateDetails = new ArrayList<>(details.size());
            for (OfcOrderDetail detail : order.getDetails()) {
                OfcOrderDetail updateDetail = new OfcOrderDetail();
                updateDetail.setId(detail.getId());
                updateDetail.setExt(detail.getExt());
                updateDetails.add(updateDetail);
            }
            updateHead.setDetails(updateDetails);
        }
        return this.ofcOrderService.update(updateHead, filter);
    }

    @Override
    public int process(OfcOrderHead ofcOrderHead, List<OfcSoHead> sos) throws BusinessException {
        if (CollectionUtils.isEmpty(sos)) {
            return -1;
        }
        Long orderCode = sos.get(0).getOrderCode();
        logger.info("process start...订单号= " + orderCode);
        OfcCustomer customer = null;
        List<Future<Boolean>> futures = new ArrayList<>(sos.size());
        for (OfcSoHead so : sos) {
            if (so == null || CollectionUtils.isEmpty(so.getDetails())) {
                continue;
            }
            if (!SoStatus.UNCREATED.getValue().equals(so.getSoStatus())) {
                logger.warn("【" + so.getSoBillCode() + "】SO单状态(" + so.getSoStatus() + ")不为\"" + SoStatus.UNCREATED.getValue() + "\"");
                continue;
            }
            if (customer == null) {
                customer = this.ofcCustomerService.getCustomer(so.getAddressCode());
            }
            Future<Boolean> future = this.executor.submit(CreateSoHandler.newInstance(this.context, ofcOrderHead, so, customer));
            futures.add(future);
        }

        for (Future<Boolean> future : futures) {
            try {
                if (!future.get()) {
                    logger.error("创建SO失败，订单号=" + orderCode);
                    return -1;
                }
            } catch (Exception e) {
                String msg = "创建SO异常，订单号=" + orderCode + "\n" + e.getMessage();
                logger.error(msg, e);
                throw new BusinessException(EC.ERROR.getCode(), msg, e);
            }
        }
        return sos.size();
    }

    @Override
    public int merge(Long orderCode) throws BusinessException {
        OfcSoHead filter = new OfcSoHead();
        filter.setOrderCode(orderCode);
        List<OfcSoHead> sos = this.ofcSoService.findList(filter, true);
        if (CollectionUtils.isEmpty(sos)) {
            throw EC.SO_OBD_NOT_EXIST.exception("订单号=" + orderCode);
        }
        boolean needIssuedTms = true;
        List<String> soCodes = new ArrayList<>();
        Set<String> warehouseCodes = new HashSet<>(sos.size());
        BigDecimal totalSkuOrderQty = BigDecimal.ZERO;
        BigDecimal totalSkuSupplyQty = BigDecimal.ZERO;
        JSONArray items = new JSONArray();
        for (OfcSoHead so : sos) {
            if (so == null) {
                continue;
            }
            String soCode = so.getSoCode();
            String warehouseCode = so.getWarehouseCode();
            if (SoStatus.CREATED.getValue() > so.getSoStatus() || !StringUtils.hasText(soCode)) {
                //TODO:如果超时
                logger.warn("订单对应SO未全部创建完成...订单号=" + orderCode + "，SO单号=" + so.getSoCode() + "，SO状态=" + so.getSoStatus());
                return 0;
            }
            totalSkuOrderQty = totalSkuOrderQty.add(so.getTotalSkuOrderQty());
            totalSkuSupplyQty = totalSkuSupplyQty.add(so.getTotalSkuSupplyQty());
            soCodes.add(soCode);
            warehouseCodes.add(warehouseCode);
            if (FulfillWms.NONE.getValue().equals(so.getFulfillWms())) { //TODO:黑狗不下发TMS
                needIssuedTms = false;
            }
            if (needIssuedTms) {
                Integer supplierId = so.getSupplierId();
                for (OfcSoDetail detail : so.getDetails()) {
                    JSONObject item = new JSONObject();
                    item.put("supplier_id", supplierId);
                    item.put("item_id", detail.getSkuCode().toString());
                    item.put("name", detail.getGoodsName());
                    item.put("item_qty", detail.getSkuOrderQty().toString());
                    item.put("item_supply_code", detail.getSkuSupplyCode());
                    item.put("s_qty", detail.getSkuSupplyQty().toString());
                    item.put("sale_unit", detail.getGoodsSaleUnit().toString());
                    items.add(item);
                }
            }
        }

        //下发TMS
        if (needIssuedTms) {
            OfcOrderHead param = new OfcOrderHead();
            param.setOrderCode(orderCode);
            OfcOrderHead bill = this.ofcOrderService.findList(param, true).get(0);
            OfcCustomer customer = this.ofcCustomerService.getCustomer(bill.getAddressCode());
            BigDecimal boxQty = this.sumBoxQty(bill.getDetails());
            JSONObject info = new JSONObject();
            info.put("order_id", orderCode.toString());
            info.put("address_id", customer.getCustCode());
            info.put("province_name", customer.getProvince());
            info.put("city_name", customer.getCity());
            info.put("county_name", customer.getDistrict());
            info.put("address", customer.getAddress());
            info.put("market_name", customer.getCustName());
            info.put("real_position", customer.getLocation());
            info.put("zone_id", bill.getRegionCode());
            info.put("warehouse_id", bill.getWarehouseCode());
            info.put("warehouse_name", bill.getWarehouseName());
            info.put("trans_limit", JSON.parseObject(customer.getExt()).getString(Constants.USER_ADDRESS_TRANS_LIMIT));
            info.put("money", bill.getOrderAmount().toString());
            info.put("ordered_at", bill.getOrderTime().toString());
            info.put("container", boxQty.toString());
            info.put("bill_id", StringUtils.collectionToDelimitedString(soCodes, ","));
            info.put("status", 1);
            info.put("so_user_id", customer.getMpCustCode());
            info.put("items", items);

            //送达时间
            if (StringUtils.hasText(bill.getAddressInfo())) {
                JSONObject addressInfo = JSONObject.parseObject(bill.getAddressInfo());
                if (StringUtils.hasText(addressInfo.getString("delivery_time_memo"))) {
                    info.put("delivery_time_memo", addressInfo.getString("delivery_time_memo"));
                }
                if (StringUtils.hasText(addressInfo.getString("trans_limit"))) {
                    info.put("trans_limit", addressInfo.getString("trans_limit"));
                }
            }

            boolean ret = this.tmsServiceProxy.addOrder(info.toString());
            if (!ret) {
                throw EC.ERROR.exception("下发TMS失败！订单号=" + orderCode);
            }
        }

        //修改OMS订单状态
        int lack;
        if (totalSkuSupplyQty == null || totalSkuSupplyQty.compareTo(BigDecimal.ZERO) <= 0) {
            lack = 1;//全部缺交
        } else if (totalSkuSupplyQty.compareTo(totalSkuOrderQty) < 0) {
            lack = 2; //部分缺交
        } else {
            lack = 0;
        }
        boolean ret = this.omsServiceProxy.updateOrderStatus(orderCode, 22, lack, soCodes, warehouseCodes);
        if (!ret) {
            throw EC.ERROR.exception("更新OMS订单状态失败！订单号=" + orderCode);
        }
        //更新OFC订单接单数量
        this.ofcOrderService.update4Create(orderCode, totalSkuSupplyQty);
        return sos.size();
    }

    /**
     * 统计箱数
     *
     * @param details
     * @return
     * @throws BusinessException
     */
    private BigDecimal sumBoxQty(List<OfcOrderDetail> details) throws BusinessException {
        if (CollectionUtils.isEmpty(details)) {
            return BigDecimal.ZERO;
        }
        BigDecimal eaQty = BigDecimal.ZERO;
        BigDecimal boxQty = BigDecimal.ZERO;
        for (OfcOrderDetail detail : details) {
            if (detail == null) {
                continue;
            }
            Long skuCode = detail.getSkuCode();
            Integer define = JSON.parseObject(detail.getExt()).getInteger(Constants.ORDER_D_SKU_DEFINE);
            if (Integer.valueOf(1).equals(define)) {
                if (detail.getGoodsSaleUnit().equals(Integer.valueOf(1))) {
                    eaQty = eaQty.add(detail.getGoodsQty());
                } else {
                    boxQty = boxQty.add(detail.getGoodsQty());
                }
            } else if (Integer.valueOf(2).equals(define)) {
                logger.warn("sku_define=2, sku_code=" + skuCode);
                boxQty = boxQty.add(detail.getGoodsQty());
            } else {
                throw EC.ERROR.exception("sku_define is error! define=" + define + ", sku_code=" + skuCode);
            }
        }

        logger.info("Order[" + details.get(0).getOrderCode() + "] details unit: ea=" + eaQty + ", box=" + boxQty);
        return boxQty.add(eaQty.divide(BigDecimal.valueOf(20), 2, BigDecimal.ROUND_HALF_UP));
    }

    @Override
    public CommonResult<Boolean> callback(JSONObject data) throws BusinessException {
        String soBillCode = data.getString("reqCode");
        if (!StringUtils.hasText(soBillCode)) {
            throw EC.ERROR_PARAMS.exception("reqCode[" + soBillCode + "] is black! received content:" + data.toJSONString());
        }

        OfcSoHead filter = new OfcSoHead();
        filter.setSoBillCode(soBillCode);
        List<OfcSoHead> sos = this.ofcSoService.findList(filter, true);
        if (CollectionUtils.isEmpty(sos)) {
            throw EC.SO_ORDER_NOT_EXIST.exception("reqCode[" + soBillCode + "] is not match! received content:" + data.toJSONString());
        }

        OfcSoHead so = sos.get(0);
        Integer soStatus = so.getSoStatus();
        MethodCallLogCollector.business(so.getSoBillCode(), 10);

        if (SoStatus.CREATED.getValue().equals(soStatus)) {
            String msg = "SO[" + soBillCode + "]create so callback duplicate! received content:" + data.toJSONString();
            return CommonResult.success(true, msg);
        }

        if (!SoStatus.CREATING.getValue().equals(soStatus) && !SoStatus.CREATE_FAIL.getValue().equals(soStatus)) {
            throw EC.ERROR.exception("SO[" + soBillCode + "] status is incorrect! status:" + soStatus + ", received content:" + data.toJSONString());
        }

        String soCode = data.getString("soCode");
        JSONArray items = data.getJSONArray("details");
        if (!StringUtils.hasText(soCode) || "-1".equals(soCode) || CollectionUtils.isEmpty(items)) {
            OfcSoHead updateHead = new OfcSoHead();
            updateHead.setId(so.getId());
            updateHead.setSoBillCode(so.getSoBillCode());
            updateHead.setSoStatus(SoStatus.CREATE_FAIL.getValue());
            int ret = this.ofcSoService.update4Create(updateHead, SoStatus.valueOf(soStatus), SoStatus.CREATE_FAIL);
            String msg = "SO[" + soBillCode + "]create so error! ret=" + ret + "... received content:" + data.toJSONString();
            logger.warn(msg);
            return CommonResult.error(msg, false);
        }

        Map<Integer, OfcSoDetail> detailMap = new HashMap<>();
        for (OfcSoDetail detail : so.getDetails()) {
            detailMap.put(detail.getItemNo(), detail);
        }
        BigDecimal totalSkuQty = BigDecimal.ZERO;
        List<OfcSoDetail> updateDetails = new ArrayList<>(detailMap.size());
        for (int i = 0; i < items.size(); i++) {
            JSONObject item = items.getJSONObject(i);
            Integer itemNbr = item.getInteger("no"); //行项目号
            String wmcode = item.getString("code"); //物美码
            BigDecimal reqQty = item.getBigDecimal("reqQty"); //请求SKU数量
            BigDecimal retQty = item.getBigDecimal("retQty"); //创建SKU数量
            OfcSoDetail soDetail = detailMap.get(itemNbr);
            if (soDetail == null) {
                throw EC.ERROR.exception("SO[" + soBillCode + "]Item is not exist! no=" + item.getString("no") + "... received content:" + data.toJSONString());
            }
            if (retQty == null || retQty.compareTo(BigDecimal.ZERO) < 0) {
                retQty = BigDecimal.ZERO;
            }

            if (!soDetail.getSkuSupplyCode().equals(wmcode) || soDetail.getSkuOrderQty().compareTo(reqQty) != 0) {//物美码不一致，或者请求数量不一致
                throw EC.ERROR.exception("SO[" + soBillCode + "]Item is not match! code(" + wmcode + "," + soDetail.getSkuSupplyCode() + "), reqQty(" + reqQty + "," + soDetail.getSkuOrderQty() + ") no=" + item.getString("no") + "... received content:" + data.toJSONString());
            }
            JSONObject obj = JSON.parseObject(soDetail.getExt());
            obj.put(Constants.SO_D_OBD, item.getString("obd"));

            String callbackSupplyPrice = item.getString("price");
            if (StringUtils.hasText(callbackSupplyPrice)) {
                obj.put(Constants.OFC_SO_WUMART_CALLBACK_SUPPLY_PRICE, callbackSupplyPrice.trim());
            }

            OfcSoDetail updateDetail = new OfcSoDetail();
            updateDetail.setId(soDetail.getId());
            updateDetail.setSkuSupplyQty(retQty);
            updateDetail.setExt(obj.toJSONString());
            updateDetails.add(updateDetail);
            totalSkuQty = totalSkuQty.add(retQty);
        }

        JSONObject ext = JSON.parseObject(so.getExt());
        ext.put(Constants.SO_H_REF_SO_CODE, data.get("temp3"));
        ext.put(Constants.SO_H_FULFILL_CREATE_TIME, OFCUtils.currentTime());
        OfcSoHead updateHead = new OfcSoHead();
        updateHead.setId(so.getId());
        updateHead.setSoBillCode(so.getSoBillCode());
        updateHead.setSoStatus(SoStatus.CREATED.getValue());
        updateHead.setSoCode(soCode);
        updateHead.setTotalSkuSupplyQty(totalSkuQty);
        updateHead.setExt(ext.toJSONString());
        updateHead.setDetails(updateDetails);
        int ret = this.ofcSoService.update4Create(updateHead, SoStatus.valueOf(soStatus), SoStatus.CREATED);
        logger.info("SO[" + soBillCode + "]create so callback success! ret=" + ret + "... received content:" + data.toJSONString());

        if (FulfillChannel.isWumartOfc(FulfillChannel.valueOf(so.getFulfillChannel())) && totalSkuQty.doubleValue() > 0) {
            asyncTaskHandler.buildAsyncTask(so, OfcTaskType.SO_VALIDATE);
        }

        return CommonResult.success(true);
    }

}
