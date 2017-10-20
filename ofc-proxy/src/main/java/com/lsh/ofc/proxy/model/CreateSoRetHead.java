package com.lsh.ofc.proxy.model;

import java.util.List;

/**
 * Created by huangdong on 16/8/28.
 */
public class CreateSoRetHead {

    /**
     * SO单号
     */
    private String code;

    /**
     * 类型(1-成功;2-失败;3-重复)
     */
    private Integer status;

    /***
     * 缺交状态(0-全部交货;1-部分缺交;2-全部缺交)
     */
    private Integer lack;

    private List<CreateSoRetDetail> items;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLack() {
        return lack;
    }

    public void setLack(Integer lack) {
        this.lack = lack;
    }

    public List<CreateSoRetDetail> getItems() {
        return items;
    }

    public void setItems(List<CreateSoRetDetail> items) {
        this.items = items;
    }
}
