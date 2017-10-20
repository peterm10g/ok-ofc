package com.lsh.ofc.proxy.model;

import java.math.BigDecimal;

/**
 * Created by huangdong on 16/10/8.
 */
public class Goods {

    /**
     * 商品编号
     */
    private Long goodsCode;

    /**
     * 商品售卖单位
     */
    private Integer goodsSaleUnit;

    /**
     * SKU编号
     */
    private Long skuCode;

    /**
     * SKU定义
     */
    private Integer skuDefine;

    /**
     * 供货SKU编号
     */
    private String supplySkuCode;

    /**
     * 供货价
     */
    private BigDecimal supplyPrice;

    public Long getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(Long goodsCode) {
        this.goodsCode = goodsCode;
    }

    public Integer getGoodsSaleUnit() {
        return goodsSaleUnit;
    }

    public void setGoodsSaleUnit(Integer goodsSaleUnit) {
        this.goodsSaleUnit = goodsSaleUnit;
    }

    public Long getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(Long skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getSkuDefine() {
        return skuDefine;
    }

    public void setSkuDefine(Integer skuDefine) {
        this.skuDefine = skuDefine;
    }

    public String getSupplySkuCode() {
        return supplySkuCode;
    }

    public void setSupplySkuCode(String supplySkuCode) {
        this.supplySkuCode = supplySkuCode;
    }

    public BigDecimal getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(BigDecimal supplyPrice) {
        this.supplyPrice = supplyPrice;
    }
}
