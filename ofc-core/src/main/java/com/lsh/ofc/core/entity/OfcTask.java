package com.lsh.ofc.core.entity;

import java.io.Serializable;

/**
 * OFC任务
 * Created by huangdong on 16/8/28.
 */
public class OfcTask implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 关联ID
     */
    private Long refId;

    /**
     * 任务类型
     */
    private Integer type;

    /**
     * 任务状态
     */
    private Integer status;

    /**
     * 任务内容
     */
    private String content;

    /**
     * 执行次数
     */
    private Integer execCount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Integer createTime;

    /**
     * 更新时间
     */
    private Integer updateTime;

    /**
     * 是否有效（0:无效；1:有效）
     */
    private Integer valid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getExecCount() {
        return execCount;
    }

    public void setExecCount(Integer execCount) {
        this.execCount = execCount;
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

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}
