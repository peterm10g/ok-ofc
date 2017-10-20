package com.lsh.ofc.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * OFC OBD单据头
 * Created by huangdong on 16/9/12.
 */
public class OfcObdHead implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * SO单据号
     */
    private String soBillCode;

    /**
     * 订单号
     */
    private Long orderCode;

    /**
     * 地域编号
     */
    private Integer regionCode;

    /**
     * 仓库编号
     */
    private String warehouseCode;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 供货商ID
     */
    private Integer supplierId;

    /**
     * 供货商DC
     */
    private String supplierDc;

    /**
     * 供货商机构
     */
    private Integer supplierOrg;

    /**
     * 履约WMS
     */
    private Integer fulfillWms;

    /**
     * 履约渠道
     */
    private Integer fulfillChannel;

    /**
     * 履约SO单号
     */
    private String soCode;

    /**
     * OBD单号
     */
    private String obdCode;

    /**
     * SKU下单总数
     */
    private BigDecimal totalSkuOrderQty;

    /**
     * SKU供货总数
     */
    private BigDecimal totalSkuSupplyQty;

    /**
     * SKU发货总数
     */
    private BigDecimal totalSkuDeliverQty;

    /**
     * SKU退货总数
     */
    private BigDecimal totalSkuReturnQty;

    /**
     * 成本金额
     */
    private BigDecimal costAmount;

    /**
     * 未税成本金额
     */
    private BigDecimal costNtAmount;

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

    /**
     * 是否有效（0:无效；1:有效）
     */
    private Integer valid;

    /**
     * OBD单据明细列表
     */
    private List<OfcObdDetail> details;

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

    public Long getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Long orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(Integer regionCode) {
        this.regionCode = regionCode;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierDc() {
        return supplierDc;
    }

    public void setSupplierDc(String supplierDc) {
        this.supplierDc = supplierDc;
    }

    public Integer getSupplierOrg() {
        return supplierOrg;
    }

    public void setSupplierOrg(Integer supplierOrg) {
        this.supplierOrg = supplierOrg;
    }

    public Integer getFulfillWms() {
        return fulfillWms;
    }

    public void setFulfillWms(Integer fulfillWms) {
        this.fulfillWms = fulfillWms;
    }

    public Integer getFulfillChannel() {
        return fulfillChannel;
    }

    public void setFulfillChannel(Integer fulfillChannel) {
        this.fulfillChannel = fulfillChannel;
    }

    public String getSoCode() {
        return soCode;
    }

    public void setSoCode(String soCode) {
        this.soCode = soCode;
    }

    public String getObdCode() {
        return obdCode;
    }

    public void setObdCode(String obdCode) {
        this.obdCode = obdCode;
    }

    public BigDecimal getTotalSkuOrderQty() {
        return totalSkuOrderQty;
    }

    public void setTotalSkuOrderQty(BigDecimal totalSkuOrderQty) {
        this.totalSkuOrderQty = totalSkuOrderQty;
    }

    public BigDecimal getTotalSkuSupplyQty() {
        return totalSkuSupplyQty;
    }

    public void setTotalSkuSupplyQty(BigDecimal totalSkuSupplyQty) {
        this.totalSkuSupplyQty = totalSkuSupplyQty;
    }

    public BigDecimal getTotalSkuDeliverQty() {
        return totalSkuDeliverQty;
    }

    public void setTotalSkuDeliverQty(BigDecimal totalSkuDeliverQty) {
        this.totalSkuDeliverQty = totalSkuDeliverQty;
    }

    public BigDecimal getTotalSkuReturnQty() {
        return totalSkuReturnQty;
    }

    public void setTotalSkuReturnQty(BigDecimal totalSkuReturnQty) {
        this.totalSkuReturnQty = totalSkuReturnQty;
    }

    public BigDecimal getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(BigDecimal costAmount) {
        this.costAmount = costAmount;
    }

    public BigDecimal getCostNtAmount() {
        return costNtAmount;
    }

    public void setCostNtAmount(BigDecimal costNtAmount) {
        this.costNtAmount = costNtAmount;
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

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public List<OfcObdDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OfcObdDetail> details) {
        this.details = details;
    }
}
