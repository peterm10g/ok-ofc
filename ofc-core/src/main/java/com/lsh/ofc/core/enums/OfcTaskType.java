package com.lsh.ofc.core.enums;

/**
 * OFC任务类型枚举
 * Created by huangdong on 16/9/10.
 */
public enum OfcTaskType {

    SO_CREATE(11, "SO创建"),

    SO_MERGE(12, "SO合单"),

    OBD_MERGE(21, "DO通知"),

    RO_CREATE(31, "RO创建"),

    RO_MERGE(32, "RO合单"),

    CUSTOMER_REFRESH(91, "客户信息刷新"),

    SO_VALIDATE(41, "SO创建验证"),

    CANCEL_VALIDATE(42, "取消订单验证");

    private final Integer value;

    private final String desc;

    private OfcTaskType(Integer value, String desc) {
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

    public static OfcTaskType valueOf(Integer value) {
        if (value == null) {
            return null;
        }
        for (OfcTaskType item : OfcTaskType.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
