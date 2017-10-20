package com.lsh.ofc.core.enums;

/**
 * 有效无效枚举
 * Created by huangdong on 16/9/10.
 */
public enum Valid {

    enable(1, "有效"), disable(0, "无效");

    private final Integer value;

    private final String desc;

    private Valid(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static Valid valueOf(Integer value) {
        for (Valid item : Valid.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
