package com.lsh.ofc.core.enums;

/**
 * RO状态枚举
 * Created by huangdong on 16/9/10.
 */
public enum RoStatus {

    UNCREATED(10, "待创建"),

    CREATING(15, "创建中"),

    CREATED(20, "已创建"),

    CREATE_FAIL(25, "创建失败"),

    COMPLETED(30, "已完成"),

    IGNORED(95, "已忽略");

    private final Integer value;

    private final String desc;

    private RoStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static RoStatus valueOf(Integer value) {
        for (RoStatus item : RoStatus.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
