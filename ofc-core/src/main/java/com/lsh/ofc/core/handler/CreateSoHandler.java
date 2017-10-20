package com.lsh.ofc.core.handler;

import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.base.AbstractTask;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.dao.OfcSoDetailDAO;
import com.lsh.ofc.core.entity.OfcCustomer;
import com.lsh.ofc.core.entity.OfcOrderHead;
import com.lsh.ofc.core.entity.OfcSoDetail;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.enums.FulfillChannel;
import com.lsh.ofc.core.enums.SoStatus;
import com.lsh.ofc.core.enums.WMSOrderType;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.service.OfcSoCreateService;
import com.lsh.ofc.core.service.OfcSoService;
import com.lsh.ofc.proxy.model.CreateLshSoReqDetail;
import com.lsh.ofc.proxy.model.CreateLshSoReqHead;
import com.lsh.ofc.proxy.model.CreateSoReqDetail;
import com.lsh.ofc.proxy.model.CreateSoReqHead;
import com.lsh.ofc.proxy.model.MeipiCustomer;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 创建SO处理Handler
 * Created by huangdong on 16/9/9.
 */
public abstract class CreateSoHandler extends AbstractTask<Boolean> {

    protected final Logger logger = Logger.getLogger(this.getClass());
    protected final OfcSoService ofcSoService;
    protected final OfcSoCreateService ofcSoCreateService;
    private final OfcSoDetailDAO ofcSoDetailDAO;
    private final OfcOrderHead ofcOrderHead;

    private final OfcSoHead so;

    private final OfcCustomer customer;

    protected CreateSoHandler(final ApplicationContext context, final OfcOrderHead ofcOrderHead, final OfcSoHead so, final OfcCustomer customer) {
        Assert.notNull(context, "context can not be null!");
        Assert.notNull(ofcOrderHead, "order can not be bull!");
        Assert.notNull(so, "so can not be null!");
        Assert.notNull(customer, "customer can not be null!");
        this.ofcOrderHead = ofcOrderHead;
        this.so = so;
        this.customer = customer;
        this.ofcSoDetailDAO = context.getBean(OfcSoDetailDAO.class);
        this.ofcSoService = context.getBean(OfcSoService.class);
        this.ofcSoCreateService = context.getBean(OfcSoCreateService.class);
    }

    public static CreateSoHandler newInstance(final ApplicationContext context, final OfcOrderHead ofcOrderHead, final OfcSoHead so, final OfcCustomer customer) throws BusinessException {
        Assert.notNull(context, "context can not be null!");
        Assert.notNull(ofcOrderHead, "order can not be bull!");
        Assert.notNull(so, "so can not be null!");
        Assert.notNull(customer, "customer can not be null!");
        FulfillChannel channel = FulfillChannel.valueOf(so.getFulfillChannel());
        if (channel == null) {
            throw EC.ERROR.exception("fulfill channel is not support!!! channel=" + so.getFulfillChannel());
        }
        try {
            return channel.getCreateSoHandlerType().getDeclaredConstructor(ApplicationContext.class, OfcOrderHead.class, OfcSoHead.class, OfcCustomer.class).newInstance(context, ofcOrderHead, so, customer);
        } catch (Exception e) {
            throw EC.ERROR.exception(e);
        }
    }

    @Override
    public Boolean execute() throws Exception {
        if (!SoStatus.UNCREATED.getValue().equals(so.getSoStatus())) {
            logger.warn("SO状态不是\"待创建\"！SO单号=" + so.getSoCode() + "，状态=" + so.getSoStatus());
            return true;
        }
        if (CollectionUtils.isEmpty(this.so.getDetails())) {
            OfcSoDetail params = new OfcSoDetail();
            params.setSoBillCode(this.so.getSoBillCode());
            this.so.setDetails(this.ofcSoDetailDAO.findList(params));
        }
        try {
            return this.process(this.so, this.customer);
        } catch (Throwable e) {
            logger.error("【" + this.so.getSoBillCode() + "】创建SO异常" + e.getMessage(), e);
            return false;
        }
    }

    protected abstract boolean process(final OfcSoHead so, final OfcCustomer customer) throws BusinessException;

    protected CreateLshSoReqHead getCreateLshSoReq() {
        Integer tmpTransTime = JSONObject.parseObject(this.so.getExt()).getInteger(Constants.SO_H_TRANS_TIME);
        Date transTime = new Date(tmpTransTime * 1000L);

        CreateLshSoReqHead order = new CreateLshSoReqHead();
        order.setWarehouseCode(this.so.getWarehouseCode());
        order.setOrderOtherId(this.so.getSoBillCode());
        order.setOrderOtherRefId("");
        order.setOrderUserCode("");
        order.setOrderUser("");
        order.setDeliveryName(this.customer.getCustName());
        order.setDeliveryCode(this.customer.getMpCustCode());
        order.setTelephone(this.customer.getContactPhone());
        order.setOwnerUid(Long.valueOf(this.so.getSupplierOrg()));
        order.setOrderType(WMSOrderType.So.getCode());
        order.setTransTime(transTime);
        order.setShipperProvince(this.customer.getProvince());
        order.setShipperCity(this.customer.getCity());
        order.setShipperDistrict(this.customer.getDistrict());
        order.setDeliveryAddrs(this.customer.getAddress());

        //wms下发超市坐标及具体地址信息
        JSONObject ext = new JSONObject();
        ext.put("position", this.customer.getLocation());
        ext.put("address", JSONObject.parseObject(this.ofcOrderHead.getAddressInfo()).getString("address"));
        order.setExt(ext.toJSONString());

        order.setAddressInfo(this.ofcOrderHead.getAddressInfo());

        List<OfcSoDetail> soDetails = this.so.getDetails();
        List<CreateLshSoReqDetail> details = new ArrayList<>(soDetails.size());
        for (OfcSoDetail soDetail : soDetails) {
            CreateLshSoReqDetail detail = new CreateLshSoReqDetail();
            detail.setDetailOtherId(soDetail.getItemNo());
            detail.setSkuCode(soDetail.getSkuSupplyCode());
            detail.setBarCode("");
            detail.setPackUnit(1);//TODO:箱规,默认写1
            detail.setOrderQty(soDetail.getSkuOrderQty());
            detail.setPackName("");
            detail.setLotNum("");
            detail.setPrice(soDetail.getGoodsPrice().divide(BigDecimal.valueOf(soDetail.getGoodsSaleUnit()), 2, BigDecimal.ROUND_HALF_UP));
            detail.setUnitName("件");
            detail.setUnitQty(soDetail.getSkuOrderQty());
            details.add(detail);
        }

        order.setDetailList(details);
        return order;
    }

    protected CreateSoReqHead getCreateSoReq() {
        CreateSoReqHead order = new CreateSoReqHead();
        order.setSoCode(this.so.getSoCode());
        order.setOrderCode(this.so.getSoBillCode());
        order.setWarehouse(this.so.getSupplierDc());
        order.setRegionCode(this.so.getRegionCode());
        order.setSoUserCode(this.customer.getMpCustCode());
        order.setFulfillWms(this.so.getFulfillWms());
        List<OfcSoDetail> items = this.so.getDetails();
        List<CreateSoReqDetail> details = new ArrayList<>(items.size());
        DecimalFormat format = new DecimalFormat("000000");
        for (OfcSoDetail item : items) {
            CreateSoReqDetail detail = new CreateSoReqDetail();
            detail.setItemNum(format.format(item.getItemNo()));
            detail.setSkuCode(item.getSkuSupplyCode());
            detail.setQuality(item.getSkuOrderQty());
            detail.setAmount(item.getGoodsAmount());
            details.add(detail);
        }
        order.setDetails(details);
        return order;
    }

    protected MeipiCustomer getMeipiCustomer() {
        MeipiCustomer mpCustomer = new MeipiCustomer();
        mpCustomer.setRegionCode(this.customer.getRegionCode());
        mpCustomer.setMarketName(this.customer.getCustName());
        mpCustomer.setSoUserCode(this.customer.getMpCustCode());
        mpCustomer.setSoUserRegion(this.customer.getMpCustZone());
        mpCustomer.setContactName(this.customer.getContactName());
        mpCustomer.setContactPhone(this.customer.getContactPhone());
        mpCustomer.setProvince(this.customer.getProvince());
        mpCustomer.setCity(this.customer.getCity());
        mpCustomer.setDistrict(this.customer.getDistrict());
        mpCustomer.setAddress(this.customer.getAddress());
        return mpCustomer;
    }
}
