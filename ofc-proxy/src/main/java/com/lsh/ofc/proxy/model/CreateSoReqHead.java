package com.lsh.ofc.proxy.model;

import java.util.List;

/**
 * Created by huangdong on 16/8/28.
 */
public class CreateSoReqHead {

    /**
     * 原SO单号(仅返仓时设置,下单时为空)
     */
    private String soCode;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 仓库
     */
    private String warehouse;

    /**
     * 地域编号
     */
    private Integer regionCode;

    /**
     * SO用户编号
     */
    private String soUserCode;

    /**
     * 履约WMS
     */
    private Integer fulfillWms;

    private List<CreateSoReqDetail> details;

    public String getSoCode() {
        return soCode;
    }

    public void setSoCode(String soCode) {
        this.soCode = soCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
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

    public Integer getFulfillWms() {
        return fulfillWms;
    }

    public void setFulfillWms(Integer fulfillWms) {
        this.fulfillWms = fulfillWms;
    }

    public List<CreateSoReqDetail> getDetails() {
        return details;
    }

    public void setDetails(List<CreateSoReqDetail> details) {
        this.details = details;
    }
}
