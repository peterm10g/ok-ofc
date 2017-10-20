package com.lsh.ofc.proxy.model;

import java.math.BigDecimal;

/**
 * lsh-ofc
 * Created by peter on 16/9/10.
 */
public class CreateLshRoReqDetail {

    /**
     * 上游细单Id
     */
    private String detailOtherId;

    /**
     * 物美码
     */
    private String skuCode;

    /**
     * 国条
     */
    private String barCode;

    /**
     * 进货数
     */
    private BigDecimal orderQty;

    /**
     * 包装单位
     */
    private BigDecimal packUnit;

    /**
     * 包装名称
     */
    private String packName;

    /**
     * 基本单位转换后数量
     */
    private BigDecimal unitQty;

    /**
     * 基本单位名称
     */
    private String unitName;

    /**
     * 价格
     */
    private BigDecimal price;

    public String getDetailOtherId() {
        return detailOtherId;
    }

    public void setDetailOtherId(String detailOtherId) {
        this.detailOtherId = detailOtherId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public BigDecimal getPackUnit() {
        return packUnit;
    }

    public void setPackUnit(BigDecimal packUnit) {
        this.packUnit = packUnit;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public BigDecimal getUnitQty() {
        return unitQty;
    }

    public void setUnitQty(BigDecimal unitQty) {
        this.unitQty = unitQty;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
