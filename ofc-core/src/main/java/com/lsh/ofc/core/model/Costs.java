package com.lsh.ofc.core.model;

import java.math.BigDecimal;

/**
 * Created by huangdong on 16/8/28.
 */
public class Costs {

    public static final Costs ZERO = new Costs(BigDecimal.ZERO, BigDecimal.ZERO);

    /**
     * 成本
     */
    private final BigDecimal amount;

    /**
     * 成本(未税)
     */
    private final BigDecimal ntAmount;

    public Costs(BigDecimal amount, BigDecimal ntAmount) {
        this.amount = amount;
        this.ntAmount = ntAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getNtAmount() {
        return ntAmount;
    }
}
