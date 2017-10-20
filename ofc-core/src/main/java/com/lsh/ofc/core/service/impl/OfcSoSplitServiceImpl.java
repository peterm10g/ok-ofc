package com.lsh.ofc.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.entity.OfcOrderDetail;
import com.lsh.ofc.core.entity.OfcOrderHead;
import com.lsh.ofc.core.entity.OfcSoDetail;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.entity.OfcSupplier;
import com.lsh.ofc.core.enums.SoStatus;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.proxy.service.WumartBasicService;
import com.lsh.ofc.core.service.OfcSoSplitService;
import com.lsh.ofc.core.service.OfcSupplierService;
import com.lsh.ofc.proxy.context.WumartBasicContext;
import com.lsh.ofc.proxy.model.Goods;
import com.lsh.ofc.proxy.service.AtpServiceProxy;
import com.lsh.ofc.proxy.service.GoodsServiceProxy;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by huangdong on 16/9/9.
 */
@Service
public class OfcSoSplitServiceImpl implements OfcSoSplitService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private AtpServiceProxy atpServiceProxy;

    @Autowired
    private GoodsServiceProxy goodsServiceProxy;

    @Autowired
    private OfcSupplierService ofcSupplierService;

    @Autowired
    private com.lsh.ofc.proxy.dao.base.BaseItemSkuDAO baseItemSkuDAO;

    @Autowired
    private WumartBasicService wumartBasicService;

    @Override
    public List<OfcSoHead> split(OfcOrderHead order) throws BusinessException {
        Long orderCode = order.getOrderCode();
        List<OfcOrderDetail> details = order.getDetails();

        logger.info("订单【" + orderCode + "】查询ATP扣减信息开始...");
        Map<Long, Map<String, BigDecimal>> skuDcQtyMap = this.atpServiceProxy.querySkuDcQtyMap(orderCode);
        logger.info("订单【" + orderCode + "】查询ATP扣减信息结果，skuDcQtyMap=" + skuDcQtyMap);

        Map<String, OfcSoHead> soMap = new HashMap<>();
        for (OfcOrderDetail item : details) {
            Long skuCode = item.getSkuCode();
            BigDecimal skuQty = item.getSkuQty();
            if (skuQty == null || skuQty.compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }
            Map<String, BigDecimal> dcQtyMap = skuDcQtyMap.get(skuCode);
            if (CollectionUtils.isEmpty(dcQtyMap)) {
                throw EC.ERROR.exception("订单商品库存扣减信息不存在! sku_code=" + skuCode);
            } else if (dcQtyMap.size() != 1) { //单DC扣减
                throw EC.ERROR.exception("订单商品库存扣减信息不存在! sku_code=" + skuCode);
            }

            String dc = dcQtyMap.entrySet().iterator().next().getKey();
            Integer regionCode = order.getRegionCode(); //TODO:地区Region
            OfcSoHead so = soMap.get(dc);
            if (so == null) {
                OfcSupplier supplier = this.ofcSupplierService.getSupplierByCode(dc, regionCode);
                if (supplier == null) {
                    throw EC.ERROR.exception("OFC供货商信息不存在! code=" + dc);
                }
                JSONObject ext = JSON.parseObject(order.getExt());
                ext.put(Constants.SO_H_SUPPLIER_CODE, supplier.getCode());
                so = new OfcSoHead();
                so.setOrderCode(orderCode);
                so.setOrderTime(order.getOrderTime());
                so.setRegionCode(order.getRegionCode());
                so.setAddressCode(order.getAddressCode());
                so.setWarehouseCode(supplier.getWarehouseCode());
                so.setWarehouseName(supplier.getWarehouseName());
                so.setSupplierId(supplier.getSupplierId());
                so.setSupplierDc(supplier.getSupplierDc());
                so.setSupplierOrg(supplier.getSupplierOrg());
                so.setFulfillWms(supplier.getFulfillWms());
                so.setFulfillChannel(supplier.getFulfillChannel());
                so.setSoStatus(SoStatus.UNCREATED.getValue());
                so.setTotalSkuOrderQty(BigDecimal.ZERO);
                so.setExt(ext.toJSONString());
                so.setDetails(new ArrayList<OfcSoDetail>());
                soMap.put(dc, so);
            }

            OfcSoDetail detail = new OfcSoDetail();
            detail.setGoodsCode(item.getGoodsCode());
            detail.setGoodsName(item.getGoodsName());
            detail.setGoodsSaleUnit(item.getGoodsSaleUnit());
            detail.setGoodsPrice(item.getGoodsPrice());
            detail.setGoodsAmount(item.getGoodsAmount());
            detail.setSkuCode(skuCode);
            detail.setSkuOrderQty(skuQty);
            detail.setExt(item.getExt());
            so.getDetails().add(detail);

            so.setTotalSkuOrderQty(so.getTotalSkuOrderQty().add(item.getSkuQty()));
        }

        Map<Long, Integer> skuDefineMap = new HashMap<>();
        for (OfcSoHead so : soMap.values()) {
            int i = 0;
            Set<Long> goodsCodes = new HashSet<>();
            for (OfcSoDetail detail : so.getDetails()) {
                detail.setItemNo((++i) * 10);
                goodsCodes.add(detail.getGoodsCode());
            }

            Integer supplierId = so.getSupplierId();
            logger.info("订单【" + orderCode + "】【" + supplierId + "】查询商品信息开始，goodsCodes=" + goodsCodes);
            Map<Long, Goods> goodsMap = this.goodsServiceProxy.getGoodsInfoMapByGoodsCodes(goodsCodes, supplierId);
            logger.info("订单【" + orderCode + "】【" + supplierId + "】查询商品信息结果，goodsMap=" + JSON.toJSONString(goodsMap));
            Set<String> outSkuCodes = new HashSet<>();
            for (Goods goods : goodsMap.values()) {
                outSkuCodes.add(goods.getSupplySkuCode());
            }

            if (outSkuCodes.size() <= 0) {
                throw EC.ERROR.exception("供货码集合为空!");
            }
            Integer market = this.wumartBasicService.tansRegion2WumartMkt(WumartBasicContext.buildContext(so.getRegionCode(), so.getSupplierDc()));
            logger.info("订单【" + orderCode + "】【" + supplierId + "】查询SKU税率开始，market=" + market + ", outSkuCodes=" + outSkuCodes);
            Map<String, BigDecimal> taxRateMap = this.baseItemSkuDAO.selectSkuTaxMap(market, outSkuCodes);
            logger.info("订单【" + orderCode + "】【" + supplierId + "】查询SKU税率结果，market=" + market + "，taxRateMap=" + taxRateMap);

            for (OfcSoDetail detail : so.getDetails()) {
                Long goodsCode = detail.getGoodsCode();
                Goods goods = goodsMap.get(goodsCode);
                if (goods == null) {
                    throw EC.ERROR.exception("商品不存在! goods_code=" + goodsCode);
                }
                detail.setSkuSupplyCode(goods.getSupplySkuCode());
                this.fillTaxRate(detail, taxRateMap);
                skuDefineMap.put(goodsCode, goods.getSkuDefine());

                //填充供货价
                BigDecimal supplyPrice = goods.getSupplyPrice();
                if (supplyPrice == null) {
                    throw EC.ERROR.exception("商品供货价为空！商品销售码=" + goodsCode);
                }
                detail.setSkuSupplyPrice(supplyPrice);
            }
        }

        for (OfcOrderDetail item : details) {
            this.fillSkuDefine(item, skuDefineMap);
        }
        return new ArrayList<>(soMap.values());
    }

    /**
     * 填充税率
     *
     * @param detail
     * @param taxRateMap
     * @throws BusinessException
     */
    private void fillTaxRate(OfcSoDetail detail, Map<String, BigDecimal> taxRateMap) throws BusinessException {
        String skuSupplyCode = detail.getSkuSupplyCode();
        BigDecimal taxRate = taxRateMap.get(skuSupplyCode);
        if (taxRate == null) {
            logger.warn("tax_rate is null... skuSupplyCode=" + skuSupplyCode);
            taxRate = Constants.TAX_017;
        }
        detail.setTaxRate(taxRate.setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 填充sku_define
     *
     * @param detail
     * @param skuDefineMap
     * @throws BusinessException
     */
    private void fillSkuDefine(OfcOrderDetail detail, Map<Long, Integer> skuDefineMap) throws BusinessException {
        Long goodsCode = detail.getGoodsCode();
        Integer skuDefine = skuDefineMap.get(goodsCode);
        if (skuDefine == null) {
            throw EC.ERROR.exception("商品不存在! goods_code=" + goodsCode);
        }
        if (!Integer.valueOf(1).equals(skuDefine) && !Integer.valueOf(2).equals(skuDefine)) {
            throw EC.ERROR.exception("sku_define is error! define=" + skuDefine + ", 销售商品码=" + goodsCode);
        }
        JSONObject json;
        String ext = detail.getExt();
        if (StringUtils.hasLength(ext)) {
            json = JSON.parseObject(ext);
        } else {
            json = new JSONObject();
        }
        json.put(Constants.ORDER_D_SKU_DEFINE, skuDefine);
        detail.setExt(json.toJSONString());
    }
}
