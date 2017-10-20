package com.lsh.ofc.core.entity;

import java.io.Serializable;

/**
 * OFC操作日志
 * Created by huangdong on 16/9/12.
 */
public class OfcOperateLog implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 单号
     */
    private String billCode;

    /**
     * 单据类型
     */
    private String billType;

    /**
     * 操作编码
     */
    private Integer operateType;

    /**
     * 操作描述
     */
    private String operateDesc;

    /**
     * 
     */
    private String remark;

    /**
     * 创建时间
     */
    private Integer createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public String getOperateDesc() {
        return operateDesc;
    }

    public void setOperateDesc(String operateDesc) {
        this.operateDesc = operateDesc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }
}
