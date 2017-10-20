package com.lsh.ofc.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * OFC SO单据明细
 * Created by huangdong on 16/9/9.
 */
public class OfcSoDetail implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * SO单据号
     */
    private String soBillCode;

    /**
     * 项目号
     */
    private Integer itemNo;

    /**
     * 商品编号
     */
    private Long goodsCode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品售卖单位
     */
    private Integer goodsSaleUnit;

    /**
     * 商品单价
     */
    private BigDecimal goodsPrice;

    /**
     * 商品金额
     */
    private BigDecimal goodsAmount;

    /**
     * SKU编号
     */
    private Long skuCode;

    /**
     * SKU供货码
     */
    private String skuSupplyCode;

    /**
     * SKU供货价
     */
    private BigDecimal skuSupplyPrice;

    /**
     * SKU下单数
     */
    private BigDecimal skuOrderQty;

    /**
     * SKU供货数
     */
    private BigDecimal skuSupplyQty;

    /**
     * SKU退货数
     */
    private BigDecimal skuReturnQty;

    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 其它信息
     */
    private String ext;

    /**
     * 创建时间
     */
    private Integer createTime;

    /**
     * 更新时间
     */
    private Integer updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSoBillCode() {
        return soBillCode;
    }

    public void setSoBillCode(String soBillCode) {
        this.soBillCode = soBillCode;
    }

    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public Long getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(Long goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsSaleUnit() {
        return goodsSaleUnit;
    }

    public void setGoodsSaleUnit(Integer goodsSaleUnit) {
        this.goodsSaleUnit = goodsSaleUnit;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public Long getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(Long skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuSupplyCode() {
        return skuSupplyCode;
    }

    public void setSkuSupplyCode(String skuSupplyCode) {
        this.skuSupplyCode = skuSupplyCode;
    }

    public BigDecimal getSkuSupplyPrice() {
        return skuSupplyPrice;
    }

    public void setSkuSupplyPrice(BigDecimal skuSupplyPrice) {
        this.skuSupplyPrice = skuSupplyPrice;
    }

    public BigDecimal getSkuOrderQty() {
        return skuOrderQty;
    }

    public void setSkuOrderQty(BigDecimal skuOrderQty) {
        this.skuOrderQty = skuOrderQty;
    }

    public BigDecimal getSkuSupplyQty() {
        return skuSupplyQty;
    }

    public void setSkuSupplyQty(BigDecimal skuSupplyQty) {
        this.skuSupplyQty = skuSupplyQty;
    }

    public BigDecimal getSkuReturnQty() {
        return skuReturnQty;
    }

    public void setSkuReturnQty(BigDecimal skuReturnQty) {
        this.skuReturnQty = skuReturnQty;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }
}
