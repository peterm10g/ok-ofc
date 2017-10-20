package com.lsh.ofc.proxy.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 收货单头
 * Created by huangdong on 16/8/28.
 */
public class ReceiptOrderHead implements Serializable {

    private static final long serialVersionUID = 5069141333256108792L;

    private Long orderId;

    private Long addressId;

    private BigDecimal couponMoney;

    private BigDecimal ticketCouponMoney;

    private BigDecimal discountCouponMoney;

    private BigDecimal payCouponMoney;

    private BigDecimal money;

    private Integer receiptAt;

    private List<ReceiptOrderDetail> details;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public BigDecimal getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(BigDecimal couponMoney) {
        this.couponMoney = couponMoney;
    }

    public BigDecimal getTicketCouponMoney() {
        return ticketCouponMoney;
    }

    public void setTicketCouponMoney(BigDecimal ticketCouponMoney) {
        this.ticketCouponMoney = ticketCouponMoney;
    }

    public BigDecimal getDiscountCouponMoney() {
        return discountCouponMoney;
    }

    public void setDiscountCouponMoney(BigDecimal discountCouponMoney) {
        this.discountCouponMoney = discountCouponMoney;
    }

    public BigDecimal getPayCouponMoney() {
        return payCouponMoney;
    }

    public void setPayCouponMoney(BigDecimal payCouponMoney) {
        this.payCouponMoney = payCouponMoney;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getReceiptAt() {
        return receiptAt;
    }

    public void setReceiptAt(Integer receiptAt) {
        this.receiptAt = receiptAt;
    }

    public List<ReceiptOrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ReceiptOrderDetail> details) {
        this.details = details;
    }
}
