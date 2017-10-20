package com.lsh.ofc.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * OFC返仓单头
 * Created by huangdong on 16/9/12.
 */
public class OfcReturnHead implements Serializable {

    /**
     * ID
     */
    private Long id;

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
     * 返仓单金额
     */
    private BigDecimal orderAmount;

    /**
     * 返仓单创建时间
     */
    private Integer orderTime;

    /**
     * 仓库编号
     */
    private String warehouseCode;

    /**
     * 仓库名称
     */
    private String warehouseName;

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
     * 返仓单明细列表
     */
    private List<OfcReturnDetail> details;

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

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Integer orderTime) {
        this.orderTime = orderTime;
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

    public List<OfcReturnDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OfcReturnDetail> details) {
        this.details = details;
    }
}
