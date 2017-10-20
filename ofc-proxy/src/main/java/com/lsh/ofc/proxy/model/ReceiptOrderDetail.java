package com.lsh.ofc.proxy.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 收货单明细
 * Created by huangdong on 16/8/28.
 */
public class ReceiptOrderDetail implements Serializable {

    private Long itemId;

    private BigDecimal money;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
