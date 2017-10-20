package com.lsh.ofc.proxy.model;

/**
 * Created by huangdong on 16/8/24.
 */
public class MeipiCustomer {

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
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 详细地址
     */
    private String address;


    /**
     * 地域编号
     */
    private Integer regionCode;

    /**
     * SO用户编号
     */
    private String soUserCode;

    /**
     * SO用户地域
     */
    private String soUserRegion;

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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(Integer regionCode) {
        this.regionCode = regionCode;
    }

    public String getSoUserCode() {
        return soUserCode;
    }

    public void setSoUserCode(String soUserCode) {
        this.soUserCode = soUserCode;
    }

    public String getSoUserRegion() {
        return soUserRegion;
    }

    public void setSoUserRegion(String soUserRegion) {
        this.soUserRegion = soUserRegion;
    }
}
