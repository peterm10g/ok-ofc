package com.lsh.ofc.provider.rest.dto;

import com.lsh.ofc.api.validation.ValidationMessage;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 返仓单头
 * Created by huangdong on 16/8/28.
 */
public class ReturnHeadDTO implements Serializable {

    private static final long serialVersionUID = -2231129782398247708L;

    /**
     * 返仓单号
     */
    @NotNull(message = ValidationMessage.NOT_NULL)
    @Min(value = 0, message = ValidationMessage.ERROR)
    private Long returnCode;

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
     * 仓库编号
     */
    @NotBlank(message = ValidationMessage.NOT_BLANK)
    private String warehouseCode;

    /**
     * 仓库名称
     */
    @NotBlank(message = ValidationMessage.NOT_BLANK)
    private String warehouseName;

    /**
     * 返仓单金额
     */
    @NotNull(message = ValidationMessage.NOT_NULL)
    @DecimalMin(value = "0", message = ValidationMessage.ERROR)
    private BigDecimal orderAmount;

    /**
     * 返仓单创建时间
     */
    @NotNull(message = ValidationMessage.NOT_NULL)
    @Min(value = 0, message = ValidationMessage.ERROR)
    private Integer createTime;

    /**
     * 返仓次数
     */
    @NotNull(message = ValidationMessage.NOT_NULL)
    @Min(value = 1, message = ValidationMessage.ERROR)
    @Max(value = 2, message = ValidationMessage.ERROR)
    private Integer count;

    /**
     * 明细列表
     */
    @Valid
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private List<ReturnDetailDTO> details;

    public Long getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Long returnCode) {
        this.returnCode = returnCode;
    }

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

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ReturnDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<ReturnDetailDTO> details) {
        this.details = details;
    }
}
