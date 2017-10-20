package com.lsh.ofc.core.enums;

/**
 * WMS订单类型枚举
 * Created by huangdong on 16/10/1.
 */
public enum WMSOrderType {

    So(1, "SO单"),
    Return(2, "供商退货单"),
    Delivery(3, "调拨出库单");

    private final Integer code;

    private final String desc;

    private WMSOrderType(Integer code, String desc) {
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
