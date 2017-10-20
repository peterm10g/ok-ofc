package com.lsh.ofc.core.enums;

/**
 * OFC任务状态枚举
 * Created by huangdong on 16/9/10.
 */
public enum OfcTaskStatus {

    /**
     * 0-初始化
     */
    NEW(0, "初始化"),

    /**
     * 1-处理中
     */
    PROCESSING(1, "处理中"),

    /**
     * 2-处理成功
     */
    SUCEESS(2, "处理成功"),

    /**
     * 3-处理异常
     */
    ERROR(3, "处理异常");

    private final Integer value;

    private final String desc;

    private OfcTaskStatus(Integer value, String desc) {
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

    public static OfcTaskStatus valueOf(Integer value) {
        if (value == null) {
            return null;
        }
        for (OfcTaskStatus item : OfcTaskStatus.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
