package com.lsh.ofc.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.dao.OfcObdDetailDAO;
import com.lsh.ofc.core.dao.OfcObdHeadDAO;
import com.lsh.ofc.core.entity.OfcObdDetail;
import com.lsh.ofc.core.entity.OfcObdHead;
import com.lsh.ofc.core.entity.OfcSoDetail;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.entity.OfcTask;
import com.lsh.ofc.core.enums.BillType;
import com.lsh.ofc.core.enums.OfcOperateEnum;
import com.lsh.ofc.core.enums.OfcTaskStatus;
import com.lsh.ofc.core.enums.OfcTaskType;
import com.lsh.ofc.core.enums.SoStatus;
import com.lsh.ofc.core.enums.Valid;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.kafka.KafkaTemplate;
import com.lsh.ofc.core.redis.RedisTemplate;
import com.lsh.ofc.core.service.OfcBillService;
import com.lsh.ofc.core.service.OfcObdService;
import com.lsh.ofc.core.service.OfcOperateLogService;
import com.lsh.ofc.core.service.OfcOrderService;
import com.lsh.ofc.core.service.OfcSoService;
import com.lsh.ofc.core.service.OfcTaskService;
import com.lsh.ofc.core.util.OFCUtils;
import com.lsh.ofc.proxy.util.MethodCallLogCollector;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OfcObdServiceImpl implements OfcObdService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private OfcObdHeadDAO ofcObdHeadDAO;

    @Autowired
    private OfcObdDetailDAO ofcObdDetailDAO;

    @Autowired
    private OfcOrderService ofcOrderService;

    @Autowired
    private OfcSoService ofcSoService;

    @Autowired
    private OfcBillService ofcBillService;

    @Autowired
    private OfcTaskService ofcTaskService;

    @Autowired
    private OfcOperateLogService ofcOperateLogService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public OfcObdHead findOne(OfcObdHead filter, boolean fillDetails) throws BusinessException {
        OfcObdHead obd = this.ofcObdHeadDAO.findOne(filter);
        if (obd == null) {
            return null;
        }
        if (fillDetails) {
            obd.setDetails(this.findDtails(obd.getSoBillCode()));
        }
        return obd;
    }

    @Override
    public List<OfcObdHead> findList(OfcObdHead filter, boolean fillDetails) throws BusinessException {
        List<OfcObdHead> obds = this.ofcObdHeadDAO.findList(filter);
        if (CollectionUtils.isEmpty(obds)) {
            return Collections.emptyList();
        }
        if (fillDetails) {
            for (OfcObdHead obd : obds) {
                obd.setDetails(this.findDtails(obd.getSoBillCode()));
            }
        }
        return obds;
    }

    @Override
    public List<OfcObdDetail> findDtails(String soBillCode) throws BusinessException {
        if (!StringUtils.hasLength(soBillCode)) {
            return Collections.emptyList();
        }
        OfcObdDetail param = new OfcObdDetail();
        param.setSoBillCode(soBillCode);
        return this.ofcObdDetailDAO.findList(param);
    }

    @Transactional
    @Override
    public void create(OfcObdHead obd) throws BusinessException {
        if (obd == null) {
            throw EC.ERROR.exception("入参订单信息为空！");
        }
        List<OfcObdDetail> details = obd.getDetails();
        if (CollectionUtils.isEmpty(details)) {
            throw EC.ERROR.exception("入参订单明细为空！");
        }

        String soCode = obd.getSoCode();
        Integer wms = obd.getFulfillWms();
        OfcSoHead filter = new OfcSoHead();
        filter.setRegionCode(obd.getRegionCode());
        filter.setWarehouseCode(obd.getWarehouseCode());
        filter.setSoCode(soCode);
        filter.setFulfillWms(wms);
        filter.setSoStatus(SoStatus.CREATED.getValue());
        List<OfcSoHead> sos = ofcSoService.findList(filter, true);
        if (CollectionUtils.isEmpty(sos)) {
            throw EC.SO_ORDER_NOT_EXIST.exception("SO单号=" + soCode + "，WMS=" + wms);
        }
        if (sos.size() != 1) {
            throw EC.SO_DUPLICATE.exception("SO单号=" + soCode + "，WMS=" + wms);
        }
        OfcSoHead so = sos.get(0);
        List<OfcSoDetail> soDetails = so.getDetails();
        if (CollectionUtils.isEmpty(soDetails)) {
            EC.SO_DETAILS_IS_EMPTY.exception("SO单号=" + obd.getSoCode() + "，WMS=" + wms);
        }
        MethodCallLogCollector.business(so.getSoBillCode(), 20);

        OfcObdHead filterObd = new OfcObdHead();
        filterObd.setSoCode(soCode);
        filterObd.setFulfillWms(wms);
        OfcObdHead entity = this.findOne(filterObd, false);
        if (entity != null) {
            if (entity.getTotalSkuDeliverQty().compareTo(obd.getTotalSkuDeliverQty()) != 0) {
                //TODO:预警
                throw EC.SO_OBD_IS_ERROR.exception("[SO单号=" + soCode + "]已存在OBD发货数据, 但当前PUSH数量[" + obd.getTotalSkuDeliverQty() + "]与既有数量[" + entity.getTotalSkuDeliverQty() + "]不一致！");
            }
            logger.info("OBD发货数据已存在，SO单号=" + soCode);
            return;
        }

        Map<String, OfcSoDetail> skuCodeMap = new HashMap<>((int) ((soDetails.size() + 1) / 0.75));
        for (OfcSoDetail item : soDetails) {
            skuCodeMap.put(item.getSkuSupplyCode(), item);
        }

        Map<String, BigDecimal> qtyMap = new HashMap<>();
        Long orderCode = so.getOrderCode();
        String billCode = so.getSoBillCode();
        obd.setSoBillCode(billCode);
        obd.setOrderCode(orderCode);
        obd.setRegionCode(so.getRegionCode());
        obd.setWarehouseCode(so.getWarehouseCode());
        obd.setWarehouseName(so.getWarehouseName());
        obd.setSupplierId(so.getSupplierId());
        obd.setSupplierDc(so.getSupplierDc());
        obd.setSupplierOrg(so.getSupplierOrg());
        obd.setFulfillWms(so.getFulfillWms());
        obd.setFulfillChannel(so.getFulfillChannel());
        obd.setSoCode(so.getSoCode());
        obd.setObdCode(obd.getObdCode());
        obd.setTotalSkuOrderQty(so.getTotalSkuOrderQty());
        obd.setTotalSkuSupplyQty(so.getTotalSkuSupplyQty());
        obd.setValid(Valid.enable.getValue());
        BigDecimal costAmount = BigDecimal.ZERO;
        BigDecimal costNtAmount = BigDecimal.ZERO;

        List<OfcObdDetail> obdDetails = new ArrayList<>();

        for (OfcObdDetail detail : details) {
            String skuSupplyCode = detail.getSkuSupplyCode();
            OfcSoDetail soDetail = skuCodeMap.get(skuSupplyCode);
            if (soDetail == null) {
                throw EC.ERROR.exception("SO中不存在对应商品，供货码=" + skuSupplyCode);
            }
            BigDecimal skuSupplyPrice = soDetail.getSkuSupplyPrice();
            BigDecimal taxRate = soDetail.getTaxRate();
            detail.setSoBillCode(billCode);
            detail.setSkuCode(soDetail.getSkuCode());
            detail.setSkuSupplyPrice(skuSupplyPrice);
            detail.setSkuReturnQty(BigDecimal.ZERO);
            detail.setTaxRate(taxRate);

            //this.ofcObdDetailDAO.insert(detail);
            obdDetails.add(detail);

            BigDecimal skuDeliverQty = detail.getSkuDeliverQty();
            BigDecimal qty = qtyMap.get(skuSupplyCode);
            if (qty == null) {
                qty = skuDeliverQty;
            } else {
                qty = qty.add(skuDeliverQty);
            }
            qtyMap.put(skuSupplyCode, qty);

            BigDecimal amount = skuSupplyPrice.multiply(skuDeliverQty).setScale(6, BigDecimal.ROUND_HALF_UP);
            BigDecimal ntAmount = amount.divide(BigDecimal.ONE.add(taxRate), 6, BigDecimal.ROUND_HALF_UP);
            costAmount = costAmount.add(amount);
            costNtAmount = costNtAmount.add(ntAmount);
        }

        JSONObject ext = JSON.parseObject(obd.getExt());
        ext.put(Constants.OBD_H_MP_CUST_CODE, JSON.parseObject(so.getExt()).getString(Constants.SO_H_MP_CUST_CODE));
        obd.setExt(ext.toJSONString());
        obd.setCostAmount(costAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
        obd.setCostNtAmount(costNtAmount.setScale(2, BigDecimal.ROUND_HALF_UP));

        this.ofcObdDetailDAO.insertBatch(obdDetails);

        this.ofcObdHeadDAO.insert(obd);

        this.ofcBillService.insert(obd);

        //更新SO发货数量
        this.ofcSoService.update4Deliver(billCode, obd.getTotalSkuDeliverQty());

        //创建合单任务
        OfcTask task = new OfcTask();
        task.setRefId(orderCode);
        task.setType(OfcTaskType.OBD_MERGE.getValue());
        task.setStatus(OfcTaskStatus.NEW.getValue());
        if (this.ofcTaskService.countTask(task) <= 0) {
            task.setContent(orderCode.toString());
            this.ofcTaskService.addTask(task);
        }

        //记录操作日志
        this.ofcOperateLogService.insert(billCode, BillType.OBD, OfcOperateEnum.OBD_RECEIVED, obd.getObdCode() + ":" + obd.getTotalSkuDeliverQty().toString());
    }

    @Override
    public int merge(Long orderCode) throws BusinessException {
        if (orderCode == null) {
            throw EC.ORDER_CODE_IS_NULL.exception();
        }

        OfcSoHead filter1 = new OfcSoHead();
        filter1.setOrderCode(orderCode);
        List<OfcSoHead> sos = this.ofcSoService.findList(filter1, true);
        if (CollectionUtils.isEmpty(sos)) {
            throw EC.SO_ORDER_NOT_EXIST.exception("订单号=" + orderCode);
        }
        BigDecimal totalSkuSupplyQty = BigDecimal.ZERO;
        Map<String, OfcSoHead> soMap = new HashMap<>();
        for (OfcSoHead so : sos) {
            soMap.put(so.getSoBillCode(), so);
            totalSkuSupplyQty = totalSkuSupplyQty.add(so.getTotalSkuSupplyQty());
        }
        int soSize = soMap.size();
        logger.info("SO集合：" + soMap.keySet());

        OfcObdHead filter2 = new OfcObdHead();
        filter2.setOrderCode(orderCode);
        List<OfcObdHead> obds = this.findList(filter2, true);
        if (CollectionUtils.isEmpty(obds)) {
            throw EC.SO_OBD_NOT_EXIST.exception("订单号=" + orderCode);
        }
        BigDecimal totalSkuDeliverQty = BigDecimal.ZERO;
        Map<String, OfcObdHead> obdMap = new HashMap<>();
        for (OfcObdHead obd : obds) {
            obdMap.put(obd.getSoBillCode(), obd);
            totalSkuDeliverQty = totalSkuDeliverQty.add(obd.getTotalSkuDeliverQty());
        }
        int obdSize = obdMap.size();
        logger.info("【" + orderCode + "】OBD集合：" + obdMap.keySet());

        boolean flag = (soSize == obdSize);
        if (!flag) {
            logger.warn("【" + orderCode + "】有部分SO尚未发货！");
            for (OfcObdHead obd : obds) {
                int createTime = obd.getCreateTime().intValue();
                if (OFCUtils.currentTime() - createTime > 600) {
                    logger.warn("【" + orderCode + "】有部分SO尚未发货，但已超过10分钟，直接发货！");
                    flag = true;
                }
            }
        }

        if (!flag) {
            logger.warn("【" + orderCode + "】有部分SO尚未发货，不触发发货异步操作！");
            return 0;
        }

        BigDecimal totalOrderRespQty = BigDecimal.ZERO;
        for (OfcSoHead so : sos) {
            totalOrderRespQty = totalOrderRespQty.add(so.getTotalSkuSupplyQty());
        }

        if (!this.redisTemplate.lock(orderCode.toString(), 30)) {
            return 0;
        }

        try {
            BigDecimal amount = BigDecimal.ZERO;
            BigDecimal ntAmount = BigDecimal.ZERO;
            List<String> soCodes = new ArrayList<>();
            List<String> obdCodes = new ArrayList<>();
            JSONObject json = new JSONObject();
            json.put("system", Constants.OFC_BILL_SYSTEM_WUMART_SAP);
            json.put("type", BillType.OBD.name());
            json.put("order_id", orderCode.toString());
            int boxNum = 0; //箱数
            int turnoverBoxNum = 0; //周转箱数
            int scatteredBoxNum = 0; //散箱数
            JSONArray items = new JSONArray();
            for (OfcObdHead obd : obds) {
                JSONObject hext = JSON.parseObject(obd.getExt());
                amount = amount.add(obd.getCostAmount());
                ntAmount = ntAmount.add(obd.getCostNtAmount());
                String waybillId = hext.getString(Constants.OBD_H_WAYBILL_CODE);
                String driverInfo = hext.getString(Constants.OBD_H_DRIVER_INFO);
                boxNum += NumberUtils.toInt(hext.getString(Constants.OBD_H_BOX_NUM), 0);
                int turnoverBoxNumTemp = NumberUtils.toInt(hext.getString(Constants.OBD_H_TURNOVER_BOX_NUM), 0);
                if (turnoverBoxNumTemp > turnoverBoxNum) {
                    turnoverBoxNum = turnoverBoxNumTemp;
                }
                int scatteredBoxNumTemp = NumberUtils.toInt(hext.getString(Constants.OBD_H_SCATTERED_BOX_NUM), 0);
                if (scatteredBoxNumTemp > scatteredBoxNum) {
                    scatteredBoxNum = scatteredBoxNumTemp;
                }

                soCodes.add(obd.getSoCode());
                obdCodes.add(obd.getObdCode());
                json.put("waybill_id", waybillId);
                json.put("driver_info", driverInfo);
                json.put("car_type", hext.getString(Constants.OBD_H_VEHICLE_TYPE));
                json.put("car_type_desc", hext.getString(Constants.OBD_H_VEHICLE_TYPE_DESC));
                json.put("carrier_code", hext.getString(Constants.OBD_H_CARRIER_CODE));
                json.put("carrier_name", hext.getString(Constants.OBD_H_CARRIER_NAME));
                json.put("create_time", hext.getString(Constants.OBD_H_CREATE_TIME));
                json.put("pick_time", hext.getString(Constants.OBD_H_PICK_TIME));
                json.put("delivery_time", hext.getString(Constants.OBD_H_DELIVERY_TIME));
                json.put("so_user_id", hext.getString(Constants.OBD_H_MP_CUST_CODE));
                for (OfcObdDetail detail : obd.getDetails()) {
                    JSONObject item = new JSONObject();
                    JSONObject dext = JSON.parseObject(detail.getExt());
                    item.put("code", detail.getSkuSupplyCode());
                    item.put("qty", detail.getSkuDeliverQty().toString());
                    item.put("waybill_id", waybillId);
                    item.put("driver_info", driverInfo);
                    item.put("pack_num", dext.getString(Constants.OBD_D_PACK_NUM));
                    item.put("box_num", dext.getString(Constants.OBD_D_BOX_NUM));
                    item.put("left_ea_num", dext.getString(Constants.OBD_D_LEFT_EA_NUM));
                    item.put("so_code", obd.getSoCode());
                    item.put("supplier_org", obd.getSupplierOrg());
                    items.add(item);
                }
            }
            json.put("out_order_id", StringUtils.collectionToDelimitedString(soCodes, ","));
            json.put("bill_id", StringUtils.collectionToDelimitedString(obdCodes, ","));
            json.put("box_num", boxNum);
            json.put("turnoverbox_num", turnoverBoxNum);
            json.put("scattered_box_num", scatteredBoxNum);
            json.put("amount", amount.toString());
            json.put("nt_amount", ntAmount.toString());
            json.put("lack_num", totalSkuDeliverQty.subtract(totalSkuSupplyQty));
            json.put("items", items);
            this.kafkaTemplate.send(Constants.TOPIC_OBD, json.toString());
            this.ofcOrderService.update4Deliver(orderCode, totalSkuDeliverQty);
            return obds.size();
        } finally {
            this.redisTemplate.unlock(orderCode.toString());
        }
    }

    @Override
    public BigDecimal update4Return(String billCode, Map<Long, BigDecimal> returnQtyMap) throws BusinessException {
        BigDecimal sum = BigDecimal.ZERO;
        for (Map.Entry<Long, BigDecimal> entry : returnQtyMap.entrySet()) {
            BigDecimal qty = entry.getValue();
            int ret = this.ofcObdDetailDAO.update4Return(billCode, entry.getKey(), qty);
            if (ret != 1) {
                throw EC.ERROR.exception("更新OBD明细返仓数量失败！单号=" + billCode + "，ID=" + entry.getKey() + "，QTY=" + qty);
            }
            sum = sum.add(qty);
        }
        int ret = this.ofcObdHeadDAO.update4Return(billCode, sum);
        if (ret != 1) {
            throw EC.ERROR.exception("更新OBD返仓数量失败！单号=" + billCode + "，QTY=" + sum);
        }
        return sum;
    }
}
