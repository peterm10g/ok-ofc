package com.lsh.ofc.proxy.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

/**
 * lsh-ofc
 * Created by peter on 16/9/10.
 */
public class CreateLshRoReqHead {

    /**
     * 仓库id (黑狗:A001)
     */
    private String warehouseCode;

    /**
     * 参考订单id
     */
    private String orderOtherRefId;

    /**
     * 订单id
     */
    private String orderOtherId;

    /**
     * 货主(1 物美,2链商)
     */
    private Long ownerUid;

    /**
     * 订单类型(1SO单，2供商退货单，3调拨出库单)
     */
    private Integer orderType;

    /**
     * 售达方名称
     */
    private String orderUser;

    /**
     * 供商编码
     */
    private String supplierCode;

    /**
     * 订单日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;

    /**
     * 截止收货时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDeliveryDate;

    private List<CreateLshRoReqDetail> detailList;

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getOrderOtherRefId() {
        return orderOtherRefId;
    }

    public void setOrderOtherRefId(String orderOtherRefId) {
        this.orderOtherRefId = orderOtherRefId;
    }

    public String getOrderOtherId() {
        return orderOtherId;
    }

    public void setOrderOtherId(String orderOtherId) {
        this.orderOtherId = orderOtherId;
    }

    public Long getOwnerUid() {
        return ownerUid;
    }

    public void setOwnerUid(Long ownerUid) {
        this.ownerUid = ownerUid;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getEndDeliveryDate() {
        return endDeliveryDate;
    }

    public void setEndDeliveryDate(Date endDeliveryDate) {
        this.endDeliveryDate = endDeliveryDate;
    }

    public List<CreateLshRoReqDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<CreateLshRoReqDetail> detailList) {
        this.detailList = detailList;
    }
}
