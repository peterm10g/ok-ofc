package com.lsh.ofc.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.api.dto.OrderDetailDTO;
import com.lsh.ofc.api.dto.OrderHeadDTO;
import com.lsh.ofc.api.service.OrderService;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.entity.OfcCustomer;
import com.lsh.ofc.core.entity.OfcOrderDetail;
import com.lsh.ofc.core.entity.OfcOrderHead;
import com.lsh.ofc.core.enums.FulfillStatus;
import com.lsh.ofc.core.handler.MeipiCustomerHandler;
import com.lsh.ofc.core.service.OfcCustomerService;
import com.lsh.ofc.core.service.OfcOrderService;
import com.lsh.ofc.provider.common.util.ValidationUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service(protocol = "dubbo", validation = "true", timeout = 10000)
public class OrderServiceImpl implements OrderService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private OfcOrderService ofcOrderService;

    @Autowired
    private OfcCustomerService ofcCustomerService;

    @Autowired
    private MeipiCustomerHandler meipiCustomerHandler;

    @Override
    public CommonResult<Boolean> createOrder(OrderHeadDTO dto) throws BusinessException {
        logger.info("create so:" + JSON.toJSONString(dto));
        ValidationUtils.valid(dto);
        Long orderCode = dto.getOrderCode();
        OfcOrderHead filter = new OfcOrderHead();
        filter.setOrderCode(orderCode);
        if (this.ofcOrderService.count(filter) > 0) {
            logger.info("重复创建SO，订单号=" + orderCode);
            return CommonResult.success(true);
        }

        BigDecimal totalSkuQty = BigDecimal.ZERO;
        List<OfcOrderDetail> details = new ArrayList<>();
        for (OrderDetailDTO item : dto.getDetails()) {
            JSONObject ext = new JSONObject();
            ext.put(Constants.ORDER_D_DETAIL_ID, item.getDetailId().toString());
            ext.put(Constants.ORDER_D_TYPE, item.getGoodsType());
            BigDecimal skuQty = item.getSkuQty();
            OfcOrderDetail detail = new OfcOrderDetail();
            detail.setOrderCode(orderCode);
            detail.setGoodsCode(item.getGoodsCode());
            detail.setGoodsName(item.getGoodsName());
            detail.setGoodsSaleUnit(item.getGoodsSaleUnit());
            detail.setGoodsPrice(item.getGoodsPrice());
            detail.setGoodsQty(item.getGoodsQty());
            detail.setGoodsAmount(item.getGoodsAmount());
            detail.setSkuCode(item.getSkuCode());
            detail.setSkuQty(skuQty);
            detail.setExt(ext.toJSONString()); //TODO:数据组需要
            details.add(detail);
            totalSkuQty = totalSkuQty.add(skuQty);
        }

        OfcOrderHead head = new OfcOrderHead();
        head.setOrderCode(orderCode);
        head.setRegionCode(dto.getRegionCode());
        head.setAddressCode(dto.getAddressCode());
        head.setAddressInfo(dto.getAddressInfo());
        head.setOrderAmount(dto.getOrderAmount());
        head.setOrderTime(dto.getCreateTime());
        head.setTotalSkuOrderQty(totalSkuQty);
        head.setFulfillStatus(FulfillStatus.NEW.getValue());
        head.setDetails(details);

        OfcCustomer customer = this.getCustomer(head);
        head.setExt(JSON.toJSONString(Collections.singletonMap(Constants.ORDER_H_MP_CUST_CODE, customer.getMpCustCode())));
        this.ofcOrderService.create(head);
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> cancelOrder(Long orderCode) throws BusinessException {
        this.ofcOrderService.cancel(orderCode);
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> cancelOrderNotify(Long orderCode) throws BusinessException {
        this.ofcOrderService.cancelNotify(orderCode);
        return CommonResult.success(true);
    }

    @Override
    public boolean isWumartOfc() throws BusinessException {
        return meipiCustomerHandler.isWumartOfc();
    }

    @Override
    public boolean isForceCancel(Long orderCode) throws BusinessException {
        return this.ofcOrderService.isForceCancel(orderCode);
    }

    /**
     * 校验并更新获取OFC客户信息
     *
     * @param order
     * @return
     * @throws BusinessException
     */
    private OfcCustomer getCustomer(OfcOrderHead order) throws BusinessException {
        //校验并更新用户信息
        JSONObject addressInfo = JSON.parseObject(order.getAddressInfo());
        OfcCustomer param = new OfcCustomer();
        param.setRegionCode(order.getRegionCode());
        param.setCustCode(order.getAddressCode());
        param.setCustName(addressInfo.getString("market_name")); //超市名称
        param.setProvince(addressInfo.getString("province_name"));//省
        param.setCity(addressInfo.getString("city_name"));//市
        param.setDistrict(addressInfo.getString("county_name"));//区
        param.setAddress(addressInfo.getString("address")); //地址
        param.setContactName(addressInfo.getString("contact_name"));//联系人
        param.setContactPhone(addressInfo.getString("contact_phone")); //联系人电话
        param.setLocation(addressInfo.getString("real_position"));//坐标
        Integer transLimit = addressInfo.getInteger("trans_limit");//大车限行
        param.setExt(JSON.toJSONString(Collections.singletonMap(Constants.USER_ADDRESS_TRANS_LIMIT, transLimit)));
        return this.ofcCustomerService.updateCustomer(param);
    }
}
