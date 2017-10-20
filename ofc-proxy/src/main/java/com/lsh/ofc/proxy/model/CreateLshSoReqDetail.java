package com.lsh.ofc.proxy.model;

import java.math.BigDecimal;

/**
 * lsh-ofc
 * Created by peter on 16/9/10.
 */
public class CreateLshSoReqDetail {

    /**
     * 上游细单Id
     */
    private Integer detailOtherId;

    /**
     * 物美码
     */
    private String skuCode;

    /**
     * 国条
     */
    private String barCode;

    /**
     * 箱规
     */
    private Integer packUnit;

    /**
     * 进货数
     */
    private BigDecimal orderQty;

    /**
     * 箱规名称
     */
    private String packName;

    /**
     * 批次号
     */
    private String lotNum;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 基本单位名称
     */
    private String unitName;

    /**
     * 基本单位转换后数量
     */
    private BigDecimal unitQty;

    public Integer getDetailOtherId() {
        return detailOtherId;
    }

    public void setDetailOtherId(Integer detailOtherId) {
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

    public Integer getPackUnit() {
        return packUnit;
    }

    public void setPackUnit(Integer packUnit) {
        this.packUnit = packUnit;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public BigDecimal getUnitQty() {
        return unitQty;
    }

    public void setUnitQty(BigDecimal unitQty) {
        this.unitQty = unitQty;
    }
}
