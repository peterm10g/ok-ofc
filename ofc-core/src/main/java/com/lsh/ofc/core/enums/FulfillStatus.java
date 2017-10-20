package com.lsh.ofc.core.enums;

/**
 * 订单履约状态枚举
 * Created by panxudong on 16/10/19.
 */
public enum FulfillStatus {

    /**
     * 10-初始化
     */
    NEW(10, "初始化"),

    /**
     * 20-已创建
     */
    CREATED(20, "已创建"),

    /**
     * 30-已发货
     */
    DELIVERY(30, "已发货"),

    /**
     * 91-取消中
     */
    CANCELING(91, "取消中"),

    /**
     * 90-已取消
     */
    CANCELED(90, "已取消");

    private final Integer value;

    private final String desc;

    private FulfillStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getValue());
    }

    public static FulfillStatus valueOf(Integer value) {
        if (value == null) {
            return null;
        }
        for (FulfillStatus item : FulfillStatus.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
