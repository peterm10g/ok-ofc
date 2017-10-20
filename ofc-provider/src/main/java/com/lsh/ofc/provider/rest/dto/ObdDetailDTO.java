package com.lsh.ofc.provider.rest.dto;

import com.lsh.ofc.api.validation.ValidationMessage;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * OBD明细
 * Created by huangdong on 16/9/5.
 */
public class ObdDetailDTO implements Serializable {

    private static final long serialVersionUID = -3380475657461957950L;

    /**
     * 供货SKU码
     */
    @NotBlank(message = ValidationMessage.NOT_BLANK)
    private String supplySkuCode;

    /**
     * SKU数量
     */
    @NotNull(message = ValidationMessage.NOT_NULL)
    @DecimalMin(value = "0", message = ValidationMessage.ERROR)
    private BigDecimal skuQty;

    @NotNull(message = ValidationMessage.NOT_NULL)
    @Min(value = 0, message = ValidationMessage.ERROR)
    private Integer packNum;

    /**
     * 箱数
     */
    private Integer boxNum;

    /**
     * LeftEA数
     */
    private Integer leftEaNum;

    public String getSupplySkuCode() {
        return supplySkuCode;
    }

    public void setSupplySkuCode(String supplySkuCode) {
        this.supplySkuCode = supplySkuCode;
    }

    public BigDecimal getSkuQty() {
        return skuQty;
    }

    public void setSkuQty(BigDecimal skuQty) {
        this.skuQty = skuQty;
    }

    public Integer getPackNum() {
        return packNum;
    }

    public void setPackNum(Integer packNum) {
        this.packNum = packNum;
    }

    public Integer getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(Integer boxNum) {
        this.boxNum = boxNum;
    }

    public Integer getLeftEaNum() {
        return leftEaNum;
    }

    public void setLeftEaNum(Integer leftEaNum) {
        this.leftEaNum = leftEaNum;
    }
}
