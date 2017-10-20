package com.lsh.ofc.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * OFC OBD单据明细
 * Created by huangdong on 16/9/12.
 */
public class OfcObdDetail implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * SO单据号
     */
    private String soBillCode;

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
     * SKU发货总数
     */
    private BigDecimal skuDeliverQty;

    /**
     * SKU退货总数
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

    public BigDecimal getSkuDeliverQty() {
        return skuDeliverQty;
    }

    public void setSkuDeliverQty(BigDecimal skuDeliverQty) {
        this.skuDeliverQty = skuDeliverQty;
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
