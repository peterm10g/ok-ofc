package com.lsh.ofc.proxy.model;

import java.math.BigDecimal;

public class CreateSoReqDetail {

    private String itemNum;

    private String itemName;

    /**
     * SKU编号(外部)
     */
    private String skuCode;

    /**
     * 数量
     */
    private BigDecimal quality;

    /**
     * 商品单价
     */
    private BigDecimal goodsPrice;

    /**
     * 金额
     */
    private BigDecimal amount;

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public BigDecimal getQuality() {
        return quality;
    }

    public void setQuality(BigDecimal quality) {
        this.quality = quality;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
