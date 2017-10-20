package com.lsh.ofc.proxy.model;

import java.math.BigDecimal;

/**
 * Created by huangdong on 16/8/28.
 */
public class CreateSoRetDetail {

    /**
     * 请求行号
     */
    private String no;

    /**
     * 响应行号
     */
    private String ln;

    /**
     * 商品码
     */
    private String code;

    /**
     * 请求数量
     */
    private BigDecimal reqQty;

    /**
     * 响应数量
     */
    private BigDecimal respQty;

    /**
     * OBD
     */
    private String obd;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getReqQty() {
        return reqQty;
    }

    public void setReqQty(BigDecimal reqQty) {
        this.reqQty = reqQty;
    }

    public BigDecimal getRespQty() {
        return respQty;
    }

    public void setRespQty(BigDecimal respQty) {
        this.respQty = respQty;
    }

    public String getObd() {
        return obd;
    }

    public void setObd(String obd) {
        this.obd = obd;
    }
}
