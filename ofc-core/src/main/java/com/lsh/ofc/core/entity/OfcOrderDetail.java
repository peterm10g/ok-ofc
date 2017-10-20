package com.lsh.ofc.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * OFC订单明细
 * Created by huangdong on 16/9/12.
 */
public class OfcOrderDetail implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 单号
     */
    private Long orderCode;

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
     * 商品数量
     */
    private BigDecimal goodsQty;

    /**
     * 商品金额
     */
    private BigDecimal goodsAmount;

    /**
     * SKU编号
     */
    private Long skuCode;

    /**
     * SKU数量
     */
    private BigDecimal skuQty;

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

    public Long getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Long orderCode) {
        this.orderCode = orderCode;
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

    public BigDecimal getGoodsQty() {
        return goodsQty;
    }

    public void setGoodsQty(BigDecimal goodsQty) {
        this.goodsQty = goodsQty;
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

    public BigDecimal getSkuQty() {
        return skuQty;
    }

    public void setSkuQty(BigDecimal skuQty) {
        this.skuQty = skuQty;
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
