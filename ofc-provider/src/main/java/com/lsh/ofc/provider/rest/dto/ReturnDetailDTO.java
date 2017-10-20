package com.lsh.ofc.provider.rest.dto;

import com.lsh.ofc.api.validation.ValidationMessage;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 返仓单明细
 * Created by huangdong on 16/8/28.
 */
public class ReturnDetailDTO implements Serializable {

    private static final long serialVersionUID = 5589572979214167762L;

    /**
     * 明细ID
     */
    @NotNull(message = ValidationMessage.NOT_NULL)
    @Min(value = 0, message = ValidationMessage.ERROR)
    private Long detailId;

    /**
     * 商品编号
     */
    @NotNull(message = ValidationMessage.NOT_NULL)
    @Min(value = 0, message = ValidationMessage.ERROR)
    private Long goodsCode;

    /**
     * 商品名称
     */
    @NotBlank(message = ValidationMessage.NOT_BLANK)
    private String goodsName;


    /**
     * 商品售卖单位
     */
    @NotNull(message = ValidationMessage.NOT_NULL)
    @Min(value = 0, message = ValidationMessage.ERROR)
    private Integer goodsSaleUnit;

    /**
     * 商品单价
     */
    @NotNull(message = ValidationMessage.NOT_NULL)
    @DecimalMin(value = "0", message = ValidationMessage.ERROR)
    private BigDecimal goodsPrice;

    /**
     * 商品数量
     */
    @NotNull(message = ValidationMessage.NOT_NULL)
    @DecimalMin(value = "0", message = ValidationMessage.ERROR)
    private BigDecimal goodsQty;

    /**
     * 商品金额
     */
    @NotNull(message = ValidationMessage.NOT_NULL)
    @DecimalMin(value = "0", message = ValidationMessage.ERROR)
    private BigDecimal goodsAmount;

    /**
     * SKU编号
     */
    @NotNull(message = ValidationMessage.NOT_NULL)
    @Min(value = 0, message = ValidationMessage.ERROR)
    private Long skuCode;

    /**
     * SKU数量
     */
    @NotNull(message = ValidationMessage.NOT_NULL)
    @DecimalMin(value = "0", message = ValidationMessage.ERROR)
    private BigDecimal skuQty;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(Long goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsSaleUnit() {
        return goodsSaleUnit;
    }

    public void setGoodsSaleUnit(Integer goodsSaleUnit) {
        this.goodsSaleUnit = goodsSaleUnit;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsQty() {
        return goodsQty;
    }

    public void setGoodsQty(BigDecimal goodsQty) {
        this.goodsQty = goodsQty;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public Long getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(Long skuCode) {
        this.skuCode = skuCode;
    }

    public BigDecimal getSkuQty() {
        return skuQty;
    }

    public void setSkuQty(BigDecimal skuQty) {
        this.skuQty = skuQty;
    }
}
