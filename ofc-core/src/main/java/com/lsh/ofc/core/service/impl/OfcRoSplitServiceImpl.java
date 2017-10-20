package com.lsh.ofc.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.*;
import com.lsh.ofc.core.enums.FulfillChannel;
import com.lsh.ofc.core.enums.RoStatus;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.handler.MeipiCustomerHandler;
import com.lsh.ofc.core.service.OfcObdService;
import com.lsh.ofc.core.service.OfcOrderService;
import com.lsh.ofc.core.service.OfcRoSplitService;
import com.lsh.ofc.core.service.OfcSoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by huangdong on 16/9/9.
 */
@Service
public class OfcRoSplitServiceImpl implements OfcRoSplitService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private OfcOrderService ofcOrderService;

    @Autowired
    private OfcSoService ofcSoService;

    @Autowired
    private OfcObdService ofcObdService;

    @Autowired
    private MeipiCustomerHandler meipiCustomerHandler;

    @Transactional
    @Override
    public List<OfcRoHead> split(OfcReturnHead order) throws BusinessException {
        //Map<SKU编号, Map<商品编号, SKU数>>
        Map<Long, Map<Long, BigDecimal>> skuQtyMap = this.mergeSkuInfoAsMap(order.getDetails());

        Long orderCode = order.getOrderCode();
        Long returnCode = order.getReturnCode();

        BigDecimal totalReturnQty = BigDecimal.ZERO;
        List<OfcObdHead> obds = this.fetchObds(orderCode, skuQtyMap.keySet());
        List<OfcRoHead> ros = new ArrayList<>(obds.size());
        for (OfcObdHead obd : obds) {
            if (obd.getTotalSkuReturnQty().compareTo(obd.getTotalSkuDeliverQty()) >= 0) {//已退数量>=发货数量
                continue;
            }

            Map<Long, BigDecimal> soDetailReturnQtyMap = new HashMap<>();
            Map<Long, BigDecimal> obdDetailReturnQtyMap = new HashMap<>();
            Map<Long, BigDecimal> returnQtyMap = new HashMap<>();//Map<商品编号, SKU数>
            for (OfcObdDetail detail : obd.getDetails()) {
                BigDecimal leftQty = detail.getSkuDeliverQty().subtract(detail.getSkuReturnQty());
                if (leftQty.compareTo(BigDecimal.ZERO) <= 0) {
                    continue;
                }
                Long skuCode = detail.getSkuCode();
                Map<Long, BigDecimal> qtyMap = skuQtyMap.get(skuCode);
                if (CollectionUtils.isEmpty(qtyMap)) {
                    continue;
                }

                //汇总OBD明细退货数量，分配销售商品退货数量
                BigDecimal returnQty = this.calcReturnQty(leftQty, qtyMap, returnQtyMap);
                obdDetailReturnQtyMap.put(detail.getId(), returnQty);

                if (qtyMap.isEmpty()) { //如果退货数据全部分配完，从集合中删除该SKU对应记录
                    skuQtyMap.remove(skuCode);
                }
            }

            if (returnQtyMap.isEmpty()) {//分配退货数量集合为空
                continue;
            }

            String billCode = obd.getSoBillCode();
            //组装返仓信息，分配SO明细退货数量
            OfcRoHead ro = this.buildRo(orderCode, billCode, returnQtyMap, soDetailReturnQtyMap);
            if (ro == null) {
                continue;
            }
            ro.setReturnCode(returnCode);

            //更新OBD返仓数量
            BigDecimal ret1 = this.ofcObdService.update4Return(billCode, obdDetailReturnQtyMap);
            //更新SO返仓数量
            BigDecimal ret2 = this.ofcSoService.update4Return(billCode, soDetailReturnQtyMap);
            if (ret1.compareTo(ret2) != 0) {
                throw EC.ERROR.exception("退货数量不一致！" + ret1 + "!=" + ret2);
            }
            ro.setTotalSkuReturnQty(ret1);
            ro.setExt(order.getExt());
            ros.add(ro);
            totalReturnQty = totalReturnQty.add(ret1);
        }

        if (!skuQtyMap.isEmpty()) {//如果未分配完
            throw EC.ERROR.exception("部分商品无法返仓！map=" + JSON.toJSONString(skuQtyMap));
        }

        //更新Order返仓数量
        this.ofcOrderService.update4Return(orderCode, totalReturnQty);
        return ros;
    }


    /**
     * 合并商品信息
     *
     * @param details
     * @return Map&lt;SKU编号, Map&lt;商品编号, SKU数&gt;&gt;
     */
    private Map<Long, Map<Long, BigDecimal>> mergeSkuInfoAsMap(List<OfcReturnDetail> details) {
        Map<Long, Map<Long, BigDecimal>> skuQtyMap = new HashMap<>();//Map<SKU编号, Map<商品编号, SKU数>>
        for (OfcReturnDetail detail : details) {
            Long goodsCode = detail.getGoodsCode();
            Long skuCode = detail.getSkuCode();
            BigDecimal skuQty = detail.getSkuQty();

            Map<Long, BigDecimal> map = skuQtyMap.get(skuCode);
            if (map == null) {
                map = new HashMap<>();
                map.put(goodsCode, skuQty);
                skuQtyMap.put(skuCode, map);
            } else {
                BigDecimal qty = map.get(goodsCode);
                if (qty == null) {
                    qty = skuQty;
                } else {
                    qty = qty.add(skuQty);
                }
                map.put(goodsCode, qty);
            }
        }
        return skuQtyMap;
    }

    /**
     * 根据订单号获取OBD信息
     *
     * @param orderCode
     * @param skuCodes
     * @return
     * @throws BusinessException
     */
    private List<OfcObdHead> fetchObds(Long orderCode, Set<Long> skuCodes) throws BusinessException {
        OfcObdHead filter = new OfcObdHead();
        filter.setOrderCode(orderCode);
        List<OfcObdHead> obds = this.ofcObdService.findList(filter, true);
        if (CollectionUtils.isEmpty(obds)) {
            throw EC.SO_OBD_NOT_EXIST.exception("订单号=" + orderCode);
        }
        List<OfcObdHead> rets = new ArrayList<>(obds.size());
        for (OfcObdHead obd : obds) {
            List<OfcObdDetail> details = obd.getDetails();
            if (CollectionUtils.isEmpty(details)) {
                continue;
            }
            List<OfcObdDetail> list = new ArrayList<>(details.size());
            for (OfcObdDetail detail : details) {
                if (skuCodes.contains(detail.getSkuCode())) {
                    list.add(detail);
                }
            }
            if (!list.isEmpty()) {
                obd.setDetails(list);
                rets.add(obd);
            }
        }

        return rets;
    }


    /**
     * 汇总OBD明细退货数量，分配销售商品退货数量
     *
     * @param leftQty 商品可退数量
     * @param qtyMap  商品SKU请求退货数量，Map&lt;商品编号, SKU数&gt;
     * @param map     商品SKU请求分配退货数量，Map&lt;商品编号, SKU数&gt;
     * @return
     */
    private BigDecimal calcReturnQty(final BigDecimal leftQty, final Map<Long, BigDecimal> qtyMap, final Map<Long, BigDecimal> map) {
        BigDecimal returnQty = BigDecimal.ZERO;
        Iterator<Map.Entry<Long, BigDecimal>> it = qtyMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Long, BigDecimal> entry = it.next();
            BigDecimal skuQty = entry.getValue();
            if (skuQty == null || skuQty.compareTo(BigDecimal.ZERO) <= 0) {
                it.remove();
                continue;
            }
            Long goodsCode = entry.getKey();
            BigDecimal qty = map.get(goodsCode);
            if (qty == null) {
                qty = BigDecimal.ZERO;
            }
            BigDecimal tmpLeftQty = leftQty.subtract(returnQty);
            if (skuQty.compareTo(tmpLeftQty) <= 0) {
                returnQty = returnQty.add(skuQty);
                qty = qty.add(skuQty);
                it.remove(); //如果退货数据全部分配完，从集合中删除该商品对应记录
            } else {
                returnQty = returnQty.add(tmpLeftQty);
                qty = qty.add(tmpLeftQty);
                entry.setValue(skuQty.subtract(tmpLeftQty));
            }
            map.put(goodsCode, qty);
        }
        return returnQty;
    }

    /**
     * 组装返仓信息，分配SO明细退货数量
     *
     * @param orderCode
     * @param billCode
     * @param qtyMap
     * @param map
     * @return
     * @throws BusinessException
     */
    private OfcRoHead buildRo(final Long orderCode, final String billCode, final Map<Long, BigDecimal> qtyMap, final Map<Long, BigDecimal> map) throws BusinessException {
        OfcSoHead filter = new OfcSoHead();
        filter.setOrderCode(orderCode);
        filter.setSoBillCode(billCode);
        List<OfcSoHead> sos = this.ofcSoService.findList(filter, true);
        if (CollectionUtils.isEmpty(sos)) {
            throw EC.SO_ORDER_NOT_EXIST.exception("订单号=" + orderCode + "，单号=" + billCode);
        }
        if (sos.size() != 1) {
            throw EC.SO_ORDER_IS_ERROR.exception("存在多条SO单记录，订单号=" + orderCode + "，单号=" + billCode);
        }
        OfcSoHead so = sos.get(0);
        List<OfcSoDetail> soDetails = so.getDetails();
        List<OfcRoDetail> roDetails = new ArrayList<>(soDetails.size());
        List<OfcSoDetail> unSupplyDetails = new ArrayList<>(soDetails.size()); //创建缺交的明细
        for (OfcSoDetail detail : soDetails) {
            Long goodsCode = detail.getGoodsCode();
            BigDecimal qty = qtyMap.get(goodsCode);
            if (qty == null || qty.compareTo(BigDecimal.ZERO) <= 0) {
                qtyMap.remove(goodsCode);
                continue;
            }
            BigDecimal skuSupplyQty = detail.getSkuSupplyQty();
            if (skuSupplyQty.compareTo(BigDecimal.ZERO) <= 0) {
                unSupplyDetails.add(detail);
                continue;
            }
            BigDecimal left = skuSupplyQty.subtract(detail.getSkuReturnQty());
            if (left.compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }
            if (qty.compareTo(left) > 0) {
                qtyMap.put(goodsCode, qty.subtract(left));
                qty = left;
            } else {
                qtyMap.remove(goodsCode);//如果退货数据全部分配完，从集合中删除该商品对应记录
            }

            OfcRoDetail roDetail = new OfcRoDetail();
            roDetail.setItemNo(detail.getItemNo());
            roDetail.setGoodsCode(detail.getGoodsCode());
            roDetail.setGoodsName(detail.getGoodsName());
            roDetail.setGoodsSaleUnit(detail.getGoodsSaleUnit());
            roDetail.setGoodsPrice(detail.getGoodsPrice());
            roDetail.setGoodsAmount(detail.getGoodsPrice().multiply(qty).divide(BigDecimal.valueOf(detail.getGoodsSaleUnit()), 2, BigDecimal.ROUND_HALF_UP));
            roDetail.setSkuCode(detail.getSkuCode());
            roDetail.setSkuSupplyCode(detail.getSkuSupplyCode());
            roDetail.setSkuReturnQty(qty);
            roDetails.add(roDetail);
            map.put(detail.getId(), qty);
        }
        if (!qtyMap.isEmpty()) {//TODO:填物美创建缺交但发货的坑。如果退货数据未分配完，从创建缺交的记录中分配
            for (OfcSoDetail detail : unSupplyDetails) {
                Long goodsCode = detail.getGoodsCode();
                BigDecimal qty = qtyMap.get(goodsCode);
                if (qty == null || qty.compareTo(BigDecimal.ZERO) <= 0) {
                    qtyMap.remove(goodsCode);
                    continue;
                }
                BigDecimal left = detail.getSkuOrderQty().subtract(detail.getSkuReturnQty());
                if (left.compareTo(BigDecimal.ZERO) <= 0) {
                    continue;
                }
                if (qty.compareTo(left) > 0) {
                    qtyMap.put(goodsCode, qty.subtract(left));
                    qty = left;
                } else {
                    qtyMap.remove(goodsCode);//如果退货数据全部分配完，从集合中删除该商品对应记录
                }

                OfcRoDetail roDetail = new OfcRoDetail();
                roDetail.setItemNo(detail.getItemNo());
                roDetail.setGoodsCode(detail.getGoodsCode());
                roDetail.setGoodsName(detail.getGoodsName());
                roDetail.setGoodsSaleUnit(detail.getGoodsSaleUnit());
                roDetail.setSkuCode(detail.getSkuCode());
                roDetail.setSkuSupplyCode(detail.getSkuSupplyCode());
                roDetail.setSkuReturnQty(qty);
                roDetails.add(roDetail);
                map.put(detail.getId(), qty);
            }
        }

        if (!qtyMap.isEmpty()) {
            throw EC.ERROR.exception("");
        }
        if (roDetails.isEmpty()) {
            return null;
        }
        OfcRoHead head = new OfcRoHead();
        head.setSoBillCode(billCode);
        head.setOrderCode(orderCode);
        head.setAddressCode(so.getAddressCode());
        head.setRegionCode(so.getRegionCode());
        head.setWarehouseCode(so.getWarehouseCode());
        head.setWarehouseName(so.getWarehouseName());
        head.setSupplierId(so.getSupplierId());
        head.setSupplierDc(so.getSupplierDc());
        head.setSupplierOrg(so.getSupplierOrg());
        head.setFulfillWms(so.getFulfillWms());

        Integer fulfillChannel; //TODO:物美sap过渡物美OFC
        if(FulfillChannel.valueOf(so.getFulfillChannel()) == FulfillChannel.WUMART_SAP) {
            fulfillChannel = FulfillChannel.WUMART_OFC.getValue();
        } else {
            fulfillChannel = so.getFulfillChannel();
        }
        head.setFulfillChannel(fulfillChannel);

        head.setSoCode(so.getSoCode());
        head.setRoStatus(RoStatus.UNCREATED.getValue());
        head.setDetails(roDetails);
        return head;
    }
}
