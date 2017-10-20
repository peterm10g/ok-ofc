package com.lsh.ofc.core.exception;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;

/**
 * Created by huangdong on 16/8/28.
 */
public enum EC {

    ERROR(CommonResult.ERROR, "error"),

    ERROR_PARAMS("E0000", "请求参数错误！"),

    ORDER_CODE_IS_NULL("E101", "订单号为空！"),

    ORDER_IS_NULL("E1102", "订单信息为空！"),

    ORDER_DETAILS_IS_EMPTY("E1103", "订单明细信息为空！"),

    ORDER_DETAILS_IS_ERROR("E1104", "订单明细数据错误！"),

    DELIVER_ORDER__DETAILS_IS_EMPTY("E1203", "发货单明细为空！"),

    RECEIPT_ORDER_IS_ERROR("E1302", "签收单数据错误！"),

    RECEIPT_ORDER_NOT_EXIST("E1302", "签收单不存在！"),

    RECEIPT_ORDER__DETAILS_IS_EMPTY("E1303", "签收单明细为空！"),

    RETURN_ORDER_IS_NULL("E1402", "返仓单为空！"),

    RETURN_ORDER_DETAILS_IS_EMPTY("E1403", "返仓明细为空！"),


    SO_ORDER_NOT_EXIST("E2101", "SO订单信息不存在！"),

    SO_DUPLICATE("E2102", "SO订单信息不存在！"),

    SO_DETAILS_IS_EMPTY("E2102", "SO订单信息不存在！"),

    SO_ORDER_IS_ERROR("E2103", "SO订单数据错误！"),

    SO_OBD_NOT_EXIST("E2102", "SO订单信息不存在！"),

    RO_ORDER_NOT_EXIST("E2104", "RO订单信息不存在！"),

    SO_OBD_IS_NULL("E2202", "OBD单信息不存在！"),

    SO_OBD_IS_ERROR("E2203", "OBD单数据错误！"),
    
    SO_OBD_IS_EXIST("E2204", "OBD单数信息已存在！"),

    GOODS_IS_ERROR("E2200", "商品信息错误！"),

    RETURN_ORDER_DETAILS_IS_EMPTY1("E1403", "返仓明细为空！");
    private final String code;

    private final String message;

    EC(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public BusinessException exception() {
        return new BusinessException(this.getCode(), this.getMessage());
    }

    public BusinessException exception(String message) {
        return new BusinessException(this.getCode(), new StringBuilder(this.message).append("\n").append(message).toString());
    }

    public BusinessException exception(Throwable e) {
        return new BusinessException(this.getCode(), new StringBuilder(this.message).append("\n").append(e.getMessage()).toString(), e);
    }
}
