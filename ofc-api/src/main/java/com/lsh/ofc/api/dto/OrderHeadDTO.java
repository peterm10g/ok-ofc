package com.lsh.ofc.api.dto;

import com.lsh.ofc.api.validation.ValidationMessage;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单头
 * Created by huangdong on 16/8/28.
 */
public class OrderHeadDTO implements Serializable {

    private static final long serialVersionUID = -9073480600808306620L;

    /**
     * 订单号
     */
    @NotNull(message = ValidationMessage.NOT_NULL)
    @Min(value = 0, message = ValidationMessage.ERROR)
    private Long orderCode;

    /**
     * 地域编号
     */
    @NotNull(message = ValidationMessage.NOT_NULL)
    @Min(value = 0, message = ValidationMessage.ERROR)
    private Integer regionCode;

    /**
     * 地址编号
     */
    @NotNull(message = ValidationMessage.NOT_NULL)
    @Min(value = 0, message = ValidationMessage.ERROR)
    private Long addressCode;

    /**
     * 地址信息
     */
    @NotBlank(message = ValidationMessage.NOT_BLANK)
    private String addressInfo;

    /**
     * 订单金额
     */
    @NotNull(message = ValidationMessage.NOT_NULL)
    @DecimalMin(value = "0", message = ValidationMessage.ERROR)
    private BigDecimal orderAmount;

    /**
     * 订单创建时间
     */
    @NotNull(message = ValidationMessage.NOT_NULL)
    @Min(value = 0, message = ValidationMessage.ERROR)
    private Integer createTime;

    /**
     * 明细列表
     */
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private List<OrderDetailDTO> details;

    public Long getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Long orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(Integer regionCode) {
        this.regionCode = regionCode;
    }

    public Long getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(Long addressCode) {
        this.addressCode = addressCode;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public List<OrderDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetailDTO> details) {
        this.details = details;
    }
}
