package com.lsh.ofc.core.enums;


/**
 * OFC操作枚举
 * Created by huangdong on 16/11/1.
 */
public enum OfcOperateEnum {

    ORDER_NEW(11, "订单初始化"),

    ORDER_FULFILL(12, "订单履约下发"),

    ORDER_DELIVER(13, "订单发货下发"),

    ORDER_RETURN(14, "订单退货"),

    RETURN_NEW(21, "退货发起"),

    //    RETURN_send(22, ""),
//
    RETURN_COMPLETE(23, "退货完成"),

    SO_UNCREATED(30, "SO待创建"),

    SO_CREATING(31, "SO创建中"),

    SO_CREATED(32, "SO已创建"),

    SO_DELIVERED(33, "SO已发货"),

    SO_RETURN(34, "SO退货"),

    SO_CREATE_FAIL(39, "SO创建失败"),

    OBD_RECEIVED(41, "OBD已接收"),

    RO_UNCREATED(50, "RO待创建"),

    RO_CREATING(51, "RO创建中"),

    RO_CREATED(52, "RO已创建"),

    RO_COMPLETED(53, "RO已完成"),

    RO_CREATE_FAIL(59, "RO创建失败");

    private final Integer type;

    private final String desc;

    OfcOperateEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static OfcOperateEnum valueOf(Integer type) {
        for (OfcOperateEnum item : OfcOperateEnum.values()) {
            if (item.getType().equals(type)) {
                return item;
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
