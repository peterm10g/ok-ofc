package com.lsh.ofc.provider.rest.dto;

import com.lsh.ofc.api.validation.ValidationMessage;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * OBD头DTO
 * Created by huangdong on 16/9/5.
 */
public class ObdHeadDTO implements Serializable {

    private static final long serialVersionUID = -367589046322334659L;

    /**
     * WMS
     */
    @Min(value = 1, message = ValidationMessage.ERROR)
    @NotNull(message = ValidationMessage.NOT_NULL)
    private Integer wms;

    /**
     * 仓库编号
     */
    @NotBlank(message = ValidationMessage.NOT_BLANK)
    private String warehouseCode;

    /**
     * SO单号
     */
    @NotBlank(message = ValidationMessage.NOT_BLANK)
    private String soCode;

    /**
     * OBD单号
     */
    @NotBlank(message = ValidationMessage.NOT_BLANK)
    private String obdCode;

    /**
     * 运单号
     */
    @NotBlank(message = ValidationMessage.NOT_BLANK)
    private String waybillCode;

    /**
     * 箱数
     */
    @Min(value = 0, message = ValidationMessage.ERROR)
    @NotNull(message = ValidationMessage.NOT_NULL)
    private Integer boxNum;

    /**
     * 周转箱数
     */
    @Min(value = 0, message = ValidationMessage.ERROR)
    @NotNull(message = ValidationMessage.NOT_NULL)
    private Integer turnoverBoxNum;

    /**
     * 散箱数
     */
    @Min(value = 0, message = ValidationMessage.ERROR)
    @NotNull(message = ValidationMessage.NOT_NULL)
    private Integer scatteredBoxNum;

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
    @Valid
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private List<ObdDetailDTO> details;

    public Integer getWms() {
        return wms;
    }

    public void setWms(Integer wms) {
        this.wms = wms;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
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

    public Integer getScatteredBoxNum() {
        return scatteredBoxNum;
    }

    public void setScatteredBoxNum(Integer scatteredBoxNum) {
        this.scatteredBoxNum = scatteredBoxNum;
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

    public List<ObdDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<ObdDetailDTO> details) {
        this.details = details;
    }
}
