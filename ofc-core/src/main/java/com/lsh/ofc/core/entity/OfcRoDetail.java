package com.lsh.ofc.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * OFC RO单据明细
 * Created by huangdong on 16/9/12.
 */
public class OfcRoDetail implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * RO单据号
     */
    private String roBillCode;

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
     * SKU退货数
     */
    private BigDecimal skuReturnQty;

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

    public String getRoBillCode() {
        return roBillCode;
    }

    public void setRoBillCode(String roBillCode) {
        this.roBillCode = roBillCode;
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

    public BigDecimal getSkuReturnQty() {
        return skuReturnQty;
    }

    public void setSkuReturnQty(BigDecimal skuReturnQty) {
        this.skuReturnQty = skuReturnQty;
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
