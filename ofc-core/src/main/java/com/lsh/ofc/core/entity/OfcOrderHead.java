package com.lsh.ofc.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * OFC订单头
 * Created by huangdong on 16/9/12.
 */
public class OfcOrderHead implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 订单号
     */
    private Long orderCode;

    /**
     * 地域编号
     */
    private Integer regionCode;

    /**
     * 地址编号
     */
    private Long addressCode;

    /**
     * 地址信息
     */
    private String addressInfo;

    /**
     * 下单金额
     */
    private BigDecimal orderAmount;

    /**
     * 下单时间
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
     * 履约状态
     */
    private Integer fulfillStatus;

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
     * 订单明细列表
     */
    private List<OfcOrderDetail> details;

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

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
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

    public Integer getFulfillStatus() {
        return fulfillStatus;
    }

    public void setFulfillStatus(Integer fulfillStatus) {
        this.fulfillStatus = fulfillStatus;
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

    public List<OfcOrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OfcOrderDetail> details) {
        this.details = details;
    }
}
