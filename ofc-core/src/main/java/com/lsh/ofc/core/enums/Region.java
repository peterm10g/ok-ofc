package com.lsh.ofc.core.enums;

/**
 * 地域枚举
 * Created by huangdong on 16/10/1.
 */
public enum Region {

    Beijing(1000, "北京"), Tianjin(1001, "天津"), Hangzhou(1002, "杭州"), Beijingcg(2000, "北京餐馆"), BeijingKA(3000, "北京KA用户");

    private final Integer code;

    private final String desc;

    private Region(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
