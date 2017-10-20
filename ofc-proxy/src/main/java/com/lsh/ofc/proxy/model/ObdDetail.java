package com.lsh.ofc.proxy.model;

import java.math.BigDecimal;

/**
 * Created by huangdong on 16/8/28.
 */
public class ObdDetail {
    /**
     * 供货SKU码
     */
    private String supplySkuCode;

    /**
     * SKU数量
     */
    private BigDecimal skuQty;

    private BigDecimal packNum;

    /**
     * 箱数
     */
    private BigDecimal boxNum;

    /**
     * LeftEA数
     */
    private BigDecimal leftEaNum;

    public String getSupplySkuCode() {
        return supplySkuCode;
    }

    public void setSupplySkuCode(String supplySkuCode) {
        this.supplySkuCode = supplySkuCode;
    }

    public BigDecimal getSkuQty() {
        return skuQty;
    }

    public void setSkuQty(BigDecimal skuQty) {
        this.skuQty = skuQty;
    }

    public BigDecimal getPackNum() {
        return packNum;
    }

    public void setPackNum(BigDecimal packNum) {
        this.packNum = packNum;
    }

    public BigDecimal getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(BigDecimal boxNum) {
        this.boxNum = boxNum;
    }

    public BigDecimal getLeftEaNum() {
        return leftEaNum;
    }

    public void setLeftEaNum(BigDecimal leftEaNum) {
        this.leftEaNum = leftEaNum;
    }
}
