package com.lsh.ofc.core.entity;

import java.io.Serializable;

/**
 * OFC供货商
 * Created by huangdong on 16/9/9.
 */
public class OfcSupplier implements Serializable {

    /**
     * ID
     */
    private Integer id;

    /**
     * SupplierId
     */
    private Integer supplierId;

    /**
     * 编号
     */
    private String code;

    /**
     * 供货商DC
     */
    private String supplierDc;

    /**
     * 供货商机构
     */
    private Integer supplierOrg;

    /**
     * 仓库编号
     */
    private String warehouseCode;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 地域编号
     */
    private Integer regionCode;

    /**
     * 履约WMS
     */
    private Integer fulfillWms;

    /**
     * 履约渠道
     */
    private Integer fulfillChannel;

    /**
     * 配置信息
     */
    private String config;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Integer getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(Integer regionCode) {
        this.regionCode = regionCode;
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

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
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

}
