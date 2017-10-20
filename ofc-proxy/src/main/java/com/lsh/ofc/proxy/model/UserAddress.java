package com.lsh.ofc.proxy.model;

import java.io.Serializable;

/**
 * 用户地址信息
 * Created by huangdong on 16/8/28.
 */
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 4840507373552709236L;

    /**
     * 地址ID
     */
    private Long addressId;

    /**
     * 超市名称
     */
    private String marketName;

    /**
     * 收货人
     */
    private String contactName;

    /**
     * 收货电话
     */
    private String contactPhone;

    /**
     * 省份
     */
    private String provinceName;

    /**
     * 城市
     */
    private String cityName;

    /**
     * 县
     */
    private String countyName;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 物美so账户id
     */
    private String soUserId;

    /**
     * 物美so客户所属区域
     */
    private String soUserRegion;

    /**
     * 所属地域id
     */
    private Integer zoneId;

    /**
     * 准确点位
     */
    private String realPosition;

    /**
     * 大车限行：1-限行  2－不限行
     */
    private Integer transLimit;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSoUserId() {
        return soUserId;
    }

    public void setSoUserId(String soUserId) {
        this.soUserId = soUserId;
    }

    public String getSoUserRegion() {
        return soUserRegion;
    }

    public void setSoUserRegion(String soUserRegion) {
        this.soUserRegion = soUserRegion;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public String getRealPosition() {
        return realPosition;
    }

    public void setRealPosition(String realPosition) {
        this.realPosition = realPosition;
    }

    public Integer getTransLimit() {
        return transLimit;
    }

    public void setTransLimit(Integer transLimit) {
        this.transLimit = transLimit;
    }
}
