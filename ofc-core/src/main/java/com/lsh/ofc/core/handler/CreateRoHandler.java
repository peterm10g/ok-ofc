package com.lsh.ofc.core.handler;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.base.AbstractTask;
import com.lsh.ofc.core.dao.OfcRoDetailDAO;
import com.lsh.ofc.core.entity.OfcCustomer;
import com.lsh.ofc.core.entity.OfcRoDetail;
import com.lsh.ofc.core.entity.OfcRoHead;
import com.lsh.ofc.core.enums.FulfillChannel;
import com.lsh.ofc.core.enums.RoStatus;
import com.lsh.ofc.core.enums.WMSOrderType;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.service.OfcRoCreateService;
import com.lsh.ofc.core.service.OfcRoService;
import com.lsh.ofc.proxy.model.CreateLshRoReqDetail;
import com.lsh.ofc.proxy.model.CreateLshRoReqHead;
import com.lsh.ofc.proxy.model.CreateSoReqDetail;
import com.lsh.ofc.proxy.model.CreateSoReqHead;
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
 * 创建RO处理Handler
 * Created by huangdong on 16/9/9.
 */
public abstract class CreateRoHandler extends AbstractTask<Boolean> {

    protected final Logger logger = Logger.getLogger(this.getClass());

    private final OfcRoDetailDAO ofcRoDetailDAO;

    protected final OfcRoService ofcRoService;

    protected final OfcRoCreateService ofcRoCreateService;

    private final OfcRoHead ro;

    private final OfcCustomer customer;

    protected CreateRoHandler(final ApplicationContext context, final OfcRoHead ro, final OfcCustomer customer) {
        Assert.notNull(context, "context can not be null!");
        Assert.notNull(ro, "ro can not be null!");
        Assert.notNull(customer, "customer can not be null!");
        this.ro = ro;
        this.customer = customer;
        this.ofcRoDetailDAO = context.getBean(OfcRoDetailDAO.class);
        this.ofcRoService = context.getBean(OfcRoService.class);
        this.ofcRoCreateService = context.getBean(OfcRoCreateService.class);
    }

    @Override
    public Boolean execute() throws Exception {
        if (!RoStatus.UNCREATED.getValue().equals(ro.getRoStatus())) {
            logger.warn("RO状态不是\"待创建\"！RO单号=" + ro.getSoCode() + "，状态=" + ro.getRoStatus());
            return true;
        }
        if (CollectionUtils.isEmpty(this.ro.getDetails())) {
            OfcRoDetail params = new OfcRoDetail();
            params.setRoBillCode(this.ro.getRoBillCode());
            this.ro.setDetails(this.ofcRoDetailDAO.findList(params));
        }
        try {
            return this.process(this.ro, this.customer);
        } catch (Throwable e) {
            logger.error("【" + this.ro.getSoBillCode() + "】创建RO异常" + e.getMessage(), e);
            return false;
        }
    }

    protected abstract boolean process(final OfcRoHead ro, final OfcCustomer customer) throws BusinessException;

    public static CreateRoHandler newInstance(final ApplicationContext context, final OfcRoHead ro, final OfcCustomer customer) throws BusinessException {
        Assert.notNull(context, "context can not be null!");
        Assert.notNull(ro, "ro can not be null!");
        Assert.notNull(customer, "customer can not be null!");
        FulfillChannel channel = FulfillChannel.valueOf(ro.getFulfillChannel());
        if (channel == null) {
            throw EC.ERROR.exception("fulfill channel is not support!!! channel=" + ro.getFulfillChannel());
        }
        try {
            return channel.getCreateRoHandlerType().getDeclaredConstructor(ApplicationContext.class, OfcRoHead.class, OfcCustomer.class).newInstance(context, ro, customer);
        } catch (Exception e) {
            throw EC.ERROR.exception(e);
        }
    }

    protected CreateLshRoReqHead getCreateLshRoReq() {
        CreateLshRoReqHead order = new CreateLshRoReqHead();
        order.setWarehouseCode(this.ro.getWarehouseCode());
        order.setOrderOtherId(this.ro.getRoBillCode());//返仓业务单号
        order.setOrderOtherRefId(this.ro.getSoCode());//原SO单号
        order.setOwnerUid(Long.valueOf(this.ro.getSupplierOrg()));
        order.setOrderType(WMSOrderType.Return.getCode());
        order.setOrderUser(this.customer.getMpCustCode());
        order.setSupplierCode("0");
        order.setOrderTime(new Date(this.ro.getCreateTime() * 1000L));
        order.setEndDeliveryDate(null);

        List<OfcRoDetail> roDetails = this.ro.getDetails();
        List<CreateLshRoReqDetail> details = new ArrayList<>(roDetails.size());
        for (OfcRoDetail roDetail : roDetails) {
            CreateLshRoReqDetail detail = new CreateLshRoReqDetail();
            detail.setDetailOtherId(String.valueOf(roDetail.getItemNo()));
            detail.setSkuCode(roDetail.getSkuSupplyCode());
            detail.setBarCode("");
            detail.setOrderQty(roDetail.getSkuReturnQty());
            detail.setPackUnit(new BigDecimal("1.00"));//TODO:箱规,默认写1
            detail.setPackName("EA");
            detail.setPrice(new BigDecimal("0.000"));
            detail.setUnitName("EA");
            detail.setUnitQty(roDetail.getSkuReturnQty());
            details.add(detail);
        }

        order.setDetailList(details);
        return order;
    }

    protected CreateSoReqHead getCreateRoReq() {
        CreateSoReqHead order = new CreateSoReqHead();
        order.setSoCode(this.ro.getSoCode());
        order.setOrderCode(this.ro.getSoBillCode());
        order.setWarehouse(this.ro.getSupplierDc());
        order.setRegionCode(this.ro.getRegionCode());
        order.setSoUserCode(this.customer.getMpCustCode());
        order.setFulfillWms(this.ro.getFulfillWms());
        List<OfcRoDetail> items = this.ro.getDetails();
        List<CreateSoReqDetail> details = new ArrayList<>(items.size());
        DecimalFormat format = new DecimalFormat("000000");
        for (OfcRoDetail item : items) {
            CreateSoReqDetail detail = new CreateSoReqDetail();
            detail.setItemNum(format.format(item.getItemNo()));
            detail.setSkuCode(item.getSkuSupplyCode());
            detail.setQuality(item.getSkuReturnQty());
            detail.setAmount(item.getGoodsAmount());
            details.add(detail);
        }
        order.setDetails(details);
        return order;
    }
}
