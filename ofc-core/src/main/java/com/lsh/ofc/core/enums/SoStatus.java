package com.lsh.ofc.core.enums;

/**
 * SO状态枚举
 * Created by huangdong on 16/9/10.
 */
public enum SoStatus {

    UNCREATED(10, "待创建"),

    CREATING(15, "创建中"),

    CREATED(20, "已创建"),

    CREATE_FAIL(25, "创建失败"),

    DELIVERED(30, "已发货"),

    CANCELING(98, "取消中"),

    CANCELED(99, "已取消");

    private final Integer value;

    private final String desc;

    private SoStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static SoStatus valueOf(Integer value) {
        for (SoStatus item : SoStatus.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
