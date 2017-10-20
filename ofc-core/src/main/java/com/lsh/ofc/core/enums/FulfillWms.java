package com.lsh.ofc.core.enums;


/**
 * 履约WMS枚举
 * Created by huangdong on 16/11/11.
 */
public enum FulfillWms {

    /**
     * 0-无
     */
    NONE(0, "无"),

    /**
     * 1-物美WMS
     */
    Wumart(1, "物美WMS"),

    /**
     * 2-链商WMS
     */
    LSH(2, "链商WMS");

    private final Integer value;

    private final String desc;

    private FulfillWms(Integer value, String desc) {
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

    public static FulfillWms valueOf(Integer value) {
        if (value == null) {
            return null;
        }
        for (FulfillWms item : FulfillWms.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
