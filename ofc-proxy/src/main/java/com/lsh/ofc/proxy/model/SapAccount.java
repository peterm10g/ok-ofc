package com.lsh.ofc.proxy.model;

import java.math.BigDecimal;

/**
 * Created by huangdong on 16/8/28.
 */
public class SapAccount {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户在平台的uid
     */
    private String uid;

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 税点
     */
    private String taxRate;

    /**
     * 凭证类型
     */
    private String docType;

    /**
     * 借/贷（C/D)描述）
     */
    private String cdType;

    /**
     * 科目编码
     */
    private String docNumber;

    /**
     * 利润中心
     */
    private String dd;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 文本描述
     */
    private String text;

    /**
     * 过账日期
     */
    private String pstngDate;

    /**
     * 用户在sap的账号
     */
    private String customer;

    /**
     * 1-新建，2-已导入，3-导入失败
     */
    private Integer status;

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getCdType() {
        return cdType;
    }

    public void setCdType(String cdType) {
        this.cdType = cdType;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPstngDate() {
        return pstngDate;
    }

    public void setPstngDate(String pstngDate) {
        this.pstngDate = pstngDate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
