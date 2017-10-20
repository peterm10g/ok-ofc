package com.lsh.ofc.core.entity;

import java.io.Serializable;

/**
 * OFC单据
 * Created by huangdong on 16/9/20.
 */
public class OfcBill implements Serializable {

    /**
     *
     */
    private Long id;

    /**
     * 主ORDER_ID
     */
    private Long orderId;

    /**
     * 来源系统
     */
    private String system;

    /**
     * 来源系统主ORDER_ID
     */
    private String outOrderId;

    /**
     * 单据类型
     */
    private String billType;

    /**
     * 单据id
     */
    private String billId;

    /**
     * 单据具体内容-JSON
     */
    private String billDetails;

    /**
     * 单据状态
     */
    private String billStatus;

    /**
     * 创建时间
     */
    private Integer createdAt;

    /**
     * 更新时间
     */
    private Integer updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(String billDetails) {
        this.billDetails = billDetails;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }
}
