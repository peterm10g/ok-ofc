package com.lsh.ofc.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * OFC RO单据头
 * Created by huangdong on 16/9/12.
 */
public class OfcRoHead implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * RO单据号
     */
    private String roBillCode;

    /**
     * SO单据号
     */
    private String soBillCode;

    /**
     * 订单号
     */
    private Long orderCode;

    /**
     * 返仓单号
     */
    private Long returnCode;

    /**
     * 地域编号
     */
    private Integer regionCode;

    /**
     * 地址编号
     */
    private Long addressCode;

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
     * RO单号
     */
    private String roCode;

    /**
     * RO状态
     */
    private Integer roStatus;

    /**
     * SKU退货总数
     */
    private BigDecimal totalSkuReturnQty;

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
     * RO单据明细列表
     */
    private List<OfcRoDetail> details;

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

    public Long getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Long returnCode) {
        this.returnCode = returnCode;
    }

    public Integer getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(Integer regionCode) {
        this.regionCode = regionCode;
    }

    public Long getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(Long addressCode) {
        this.addressCode = addressCode;
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

    public String getRoCode() {
        return roCode;
    }

    public void setRoCode(String roCode) {
        this.roCode = roCode;
    }

    public Integer getRoStatus() {
        return roStatus;
    }

    public void setRoStatus(Integer roStatus) {
        this.roStatus = roStatus;
    }

    public BigDecimal getTotalSkuReturnQty() {
        return totalSkuReturnQty;
    }

    public void setTotalSkuReturnQty(BigDecimal totalSkuReturnQty) {
        this.totalSkuReturnQty = totalSkuReturnQty;
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

    public List<OfcRoDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OfcRoDetail> details) {
        this.details = details;
    }
}
