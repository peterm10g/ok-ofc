package com.lsh.ofc.proxy.model;

import java.util.List;

/**
 * Created by huangdong on 16/8/26.
 */
public class ObdHead {

    /**
     * 地域编号
     */
    private Integer regionCode;

    /**
     * SO单号
     */
    private String soCode;

    /**
     * OBD单号
     */
    private String obdCode;

    /**
     * 运单号
     */
    private String waybillCode;

    /**
     * 箱数
     */
    private Integer boxNum;

    /**
     * 周转箱数
     */
    private Integer turnoverBoxNum;

    /**
     * 司机信息
     */
    private String driverInfo;

    /**
     * 车辆类型
     */
    private String vehicleType;

    /**
     * 车辆类型描述
     */
    private String vehicleTypeDesc;

    /**
     * 承运商标号
     */
    private String carrierCode;

    /**
     * 承运商名称
     */
    private String carrierName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 拣货时间
     */
    private String pickTime;

    /**
     * 发货时间
     */
    private String deliveryTime;

    /**
     * OBD明细
     */
    private List<ObdDetail> details;

    public Integer getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(Integer regionCode) {
        this.regionCode = regionCode;
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

    public String getWaybillCode() {
        return waybillCode;
    }

    public void setWaybillCode(String waybillCode) {
        this.waybillCode = waybillCode;
    }

    public Integer getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(Integer boxNum) {
        this.boxNum = boxNum;
    }

    public Integer getTurnoverBoxNum() {
        return turnoverBoxNum;
    }

    public void setTurnoverBoxNum(Integer turnoverBoxNum) {
        this.turnoverBoxNum = turnoverBoxNum;
    }

    public String getDriverInfo() {
        return driverInfo;
    }

    public void setDriverInfo(String driverInfo) {
        this.driverInfo = driverInfo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleTypeDesc() {
        return vehicleTypeDesc;
    }

    public void setVehicleTypeDesc(String vehicleTypeDesc) {
        this.vehicleTypeDesc = vehicleTypeDesc;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPickTime() {
        return pickTime;
    }

    public void setPickTime(String pickTime) {
        this.pickTime = pickTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public List<ObdDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ObdDetail> details) {
        this.details = details;
    }
}
