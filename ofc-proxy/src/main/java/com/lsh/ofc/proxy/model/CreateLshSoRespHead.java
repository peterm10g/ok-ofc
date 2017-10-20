package com.lsh.ofc.proxy.model;

import java.util.List;

/**
 * lsh-ofc
 * Created by peter on 16/9/10.
 */
public class CreateLshSoRespHead {

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 仓库id (黑狗:A001)
     */
    private String warehouseCode;

//    /**
//     * 订单id
//     */
//    private String orderOtherId;
//
//    /**
//     * 参考订单id
//     */
//    private String orderOtherRefId;
//
//    /**
//     * 售达方编号(ofcOrder->agPartnNumber)
//     */
//    private String orderUserCode;
//
//    /**
//     * 售达方名称
//     */
//    private String orderUser;
//
//    /**
//     * 送达方名称(ofcConsumer->name1)
//     */
//    private String deliveryName;
//
//    /**
//     * 送达方编码(电话号码)(ofcConsumer->customerNumber)
//     */
//    private String deliveryCode;
//
//    /**
//     * 货主(1 物美,2链商)
//     */
//    private Long ownerUid;
//
//    /**
//     * 订单类型(1SO单，2供商退货单，3调拨出库单)
//     */
//    private Integer orderType;
//
//    /**
//     * 交货时间
//     */
//    private Date transTime;
//
//    /**
//     * 省
//     */
//    private String shipperProvince;
//
//    /**
//     * 市
//     */
//    private String shipperCity;
//
//    /**
//     * 区
//     */
//    private String shipperDistrict;
//
//    /**
//     * 收货地址
//     */
//    private String deliveryAddrs;

    private List<CreateLshSoRespDetail> detailList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

//    public String getOrderOtherId() {
//        return orderOtherId;
//    }
//
//    public void setOrderOtherId(String orderOtherId) {
//        this.orderOtherId = orderOtherId;
//    }
//
//    public String getOrderOtherRefId() {
//        return orderOtherRefId;
//    }
//
//    public void setOrderOtherRefId(String orderOtherRefId) {
//        this.orderOtherRefId = orderOtherRefId;
//    }
//
//    public String getOrderUserCode() {
//        return orderUserCode;
//    }
//
//    public void setOrderUserCode(String orderUserCode) {
//        this.orderUserCode = orderUserCode;
//    }
//
//    public String getOrderUser() {
//        return orderUser;
//    }
//
//    public void setOrderUser(String orderUser) {
//        this.orderUser = orderUser;
//    }
//
//    public String getDeliveryName() {
//        return deliveryName;
//    }
//
//    public void setDeliveryName(String deliveryName) {
//        this.deliveryName = deliveryName;
//    }
//
//    public String getDeliveryCode() {
//        return deliveryCode;
//    }
//
//    public void setDeliveryCode(String deliveryCode) {
//        this.deliveryCode = deliveryCode;
//    }
//
//    public Long getOwnerUid() {
//        return ownerUid;
//    }
//
//    public void setOwnerUid(Long ownerUid) {
//        this.ownerUid = ownerUid;
//    }
//
//    public Integer getOrderType() {
//        return orderType;
//    }
//
//    public void setOrderType(Integer orderType) {
//        this.orderType = orderType;
//    }
//
//    public Date getTransTime() {
//        return transTime;
//    }
//
//    public void setTransTime(Date transTime) {
//        this.transTime = transTime;
//    }
//
//    public String getShipperProvince() {
//        return shipperProvince;
//    }
//
//    public void setShipperProvince(String shipperProvince) {
//        this.shipperProvince = shipperProvince;
//    }
//
//    public String getShipperCity() {
//        return shipperCity;
//    }
//
//    public void setShipperCity(String shipperCity) {
//        this.shipperCity = shipperCity;
//    }
//
//    public String getShipperDistrict() {
//        return shipperDistrict;
//    }
//
//    public void setShipperDistrict(String shipperDistrict) {
//        this.shipperDistrict = shipperDistrict;
//    }
//
//    public String getDeliveryAddrs() {
//        return deliveryAddrs;
//    }
//
//    public void setDeliveryAddrs(String deliveryAddrs) {
//        this.deliveryAddrs = deliveryAddrs;
//    }

    public List<CreateLshSoRespDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<CreateLshSoRespDetail> detailList) {
        this.detailList = detailList;
    }
}
