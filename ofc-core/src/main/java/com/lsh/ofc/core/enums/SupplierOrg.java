package com.lsh.ofc.core.enums;

/**
 * 供货机构枚举
 * Created by huangdong on 16/9/10.
 */
public enum SupplierOrg {

    WUMART(1, "物美"), LSH(2, "链商");

    private final Integer value;

    private final String desc;

    private SupplierOrg(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 判断供货机构是否为寄售
     *
     * @param org
     * @return
     */
    public static boolean isConsign(Integer org) {
        return SupplierOrg.LSH.getValue().equals(org);
    }
}
