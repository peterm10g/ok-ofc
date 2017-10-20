package com.lsh.ofc.core.proxy.handler;

import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.proxy.context.WumartBasicContext;
import com.lsh.ofc.proxy.model.CreateSoReqDetail;
import com.lsh.ofc.proxy.model.CreateSoReqHead;
import com.lsh.ofc.proxy.model.CreateSoRetDetail;
import com.lsh.ofc.proxy.model.CreateSoRetHead;
import com.lsh.ofc.proxy.model.MeipiCustomer;
import com.lsh.ofc.proxy.model.ObdDetail;
import com.lsh.ofc.proxy.model.ObdHead;
import com.lsh.ofc.proxy.util.MethodCallLogCollector;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 物美SOP服务代理2
 * Created by huangdong on 16/8/28.
 */
public class WumartSap2Handler extends WumartSapHandler {

    public String commitMeipiCustomer(MeipiCustomer model) throws BusinessException {
        String soUserId = model.getSoUserCode();
        Integer regionCode = model.getRegionCode();
        String sLSHOuterUserId = this.wumartBasicService.tansRegionWumartUsr(WumartBasicContext.buildContext(regionCode));
        com.wumart.sap2.meipi.cus.maintain.ObjectFactory factory = new com.wumart.sap2.meipi.cus.maintain.ObjectFactory();

        String ort = StringUtils.collectionToDelimitedString(Arrays.asList(model.getProvince(), model.getCity(), model.getDistrict()), " ");
        com.wumart.sap2.meipi.cus.maintain.Kna1 kna1 = factory.createKna1();
        kna1.setKtokd("LSMP"); //写死：LSMP
        kna1.setAnred("公司"); //StxtCaption 标题 ,客户类型
        kna1.setName1(model.getMarketName()); // StxtName1 名称1 ,名称
        kna1.setName2("链商客户号"); // 写死：链商客户号
        kna1.setName3(model.getContactName());
        kna1.setSortl(sLSHOuterUserId);
        kna1.setStras(StringUtils.collectionToDelimitedString(Arrays.asList(ort, model.getAddress()), " ")); // StxtStreet 街道 
        kna1.setPsoo4(""); // StxtHomeNum 门牌号 
        kna1.setPstlz("100001"); // StxtPostalcode 邮编 
        kna1.setOrt01(ort); // StxtCity 城市 
        kna1.setLand1("CN"); // StxtCountry1 国家 
        kna1.setRegio("010"); // StxtAddress 地区 ---物美地区编码
        kna1.setTelf1(model.getContactPhone()); // StxtPhone 电话 
        kna1.setBran1("0001"); // StxtCallingCode 行业代码1 , 0001-商业
        kna1.setLzone(model.getSoUserRegion());

        //MODE 模式,I为新增U为修改
        String mode;
        if (soUserId == null) {
            mode = "I";
        } else {
            mode = "U";
            kna1.setKunnr(soUserId);
        }

        //I_KNB1
        com.wumart.sap2.meipi.cus.maintain.Knb1 knb1 = factory.createKnb1();
        knb1.setBukrs("C092"); // StxtComputerCode 公司代码 , 按门店区域公司代码
        knb1.setAkont("1122050100"); // StxtSubject 统驭科目, 默认1122050100
        knb1.setBegru(""); // StxtPurviewGroup 权限组, 按销售地区 "北京,美廉美,天津,华东";

        //I_KNVV
        com.wumart.sap2.meipi.cus.maintain.Knvv knvv = factory.createKnvv();
        knvv.setVsbed(this.wumartBasicService.tansRegion2WumartVsb(WumartBasicContext.buildContext(regionCode)));
        knvv.setKztlf(""); // 默认：B

        //I_BANK 银行信息，非必输
        com.wumart.sap2.meipi.cus.maintain.Knvv knvv1 = factory.createKnvv();
        knvv1.setVkorg("S530");
        knvv1.setVtweg("20");
        knvv1.setSpart("10");

        com.wumart.sap2.meipi.cus.maintain.TableOfKnvv tKnvv = factory.createTableOfKnvv();
        tKnvv.getItem().add(knvv1);

        //T_XKNBK 未使用
        com.wumart.sap2.meipi.cus.maintain.TableOfFknbk tFknbk = factory.createTableOfFknbk();
        com.wumart.sap2.meipi.cus.maintain.TableOfFknvk tFknvk = factory.createTableOfFknvk();


        com.wumart.sap2.meipi.cus.maintain.TableOfFknvp tFknvp = factory.createTableOfFknvp();
        com.wumart.sap2.meipi.cus.maintain.Fknvp fknvp = factory.createFknvp();
        fknvp.setParvw("SP");
        fknvp.setKunnr(sLSHOuterUserId);
        tFknvp.getItem().add(fknvp);


        Holder<String> errorMsg = new Holder<>();
        Holder<String> flag = new Holder<>();
        Holder<String> kunnr = new Holder<>();
        Holder<com.wumart.sap2.meipi.cus.maintain.Kna1> hKna1 = new Holder<>(kna1);
        Holder<com.wumart.sap2.meipi.cus.maintain.TableOfKnvv> hKnvv = new Holder<>(tKnvv);
        Holder<com.wumart.sap2.meipi.cus.maintain.TableOfFknbk> hFknbk = new Holder<>(tFknbk);
        Holder<com.wumart.sap2.meipi.cus.maintain.TableOfFknvk> hFknvk = new Holder<>(tFknvk);
        Holder<com.wumart.sap2.meipi.cus.maintain.TableOfFknvp> hFknvp = new Holder<>(tFknvp);

        com.wumart.sap2.meipi.cus.maintain.Zbankinfo zbankinfo = factory.createZbankinfo();
        com.wumart.sap2.meipi.cus.maintain.ZWSMEIPICUSMAINTAINN zbinding = new com.wumart.sap2.meipi.cus.maintain.ZWEBSERVICEMEIPICUSN().getBindingSOAP12();
        this.auth((BindingProvider) zbinding, model.getRegionCode());
        JSONObject params = new JSONObject(32);
        params.put("IBank", zbankinfo);
        params.put("IKna1", kna1);
        params.put("IKnb1", knb1);
        params.put("IKnvv", knvv);
        params.put("Mode", mode);
        params.put("TKnvv", hKnvv);
        params.put("TXknbk", hFknbk);
        params.put("TXknvk", hFknvk);
        params.put("TXknvp", hFknvp);
        params.put("ErrorMsg", errorMsg);
        params.put("Flag", flag);
        params.put("OKna1", hKna1);
        params.put("Tkunnr", kunnr);
        logger.info("zmdMeipiCustomerMaintain params:" + params.toJSONString());
        long start = System.currentTimeMillis();
        zbinding.zmdMeipiCustomerMaintain(zbankinfo, kna1, knb1, knvv, mode, hKnvv, hFknbk, hFknvk, hFknvp, errorMsg, flag, hKna1, kunnr);
        long end = System.currentTimeMillis();
        JSONObject result = new JSONObject(2);
        result.put("params", params);
        String msg = "zmdMeipiCustomerMaintain cost[" + (end - start) + "] result:" + result.toJSONString();
        logger.info(msg);
        if (!flag.value.toUpperCase().equals("X")) {
            throw new BusinessException(CommonResult.ERROR, msg);
        }
        return kunnr.value;
    }

    public CreateSoRetHead createMeipiSo(CreateSoReqHead order, boolean first) throws BusinessException {
        String wumartUsr = this.wumartBasicService.tansRegionWumartUsr(WumartBasicContext.buildContext(order.getRegionCode(), order.getWarehouse())); //售达方
        String warehouse = order.getWarehouse();
        String orderCode = order.getOrderCode();
        String soUserCode = order.getSoUserCode();
        Integer regionCode = order.getRegionCode();
        List<CreateSoReqDetail> details = order.getDetails();
        if (StringUtils.isEmpty(warehouse) || StringUtils.isEmpty(orderCode) || StringUtils.isEmpty(soUserCode) || regionCode == null || CollectionUtils.isEmpty(details)) {
            String msg = "CreateSOOrder Error Params! warehouse=" + warehouse + ", orderCode=" + orderCode + ", soUserCode=" + soUserCode + ", regionCode=" + regionCode + ", details.isEmpty=" + CollectionUtils.isEmpty(details);
            logger.error(msg);
            throw new BusinessException(CommonResult.ERROR, msg);
        }

        com.wumart.sap2.meipi.createso.ObjectFactory factory = new com.wumart.sap2.meipi.createso.ObjectFactory();
        //SOHEADER
        com.wumart.sap2.meipi.createso.ZbapiR2Createsohead head = factory.createZbapiR2Createsohead();
        head.setIncoterms2(orderCode); //美批订单号
        String soCode = order.getSoCode();
        if (StringUtils.hasLength(soCode)) {
            head.setDocType("ZMPR");
            head.setRefDoc(soCode);
        } else {
            head.setDocType("ZMP");
        }
        if (!first) {
            head.setTemp05("X");
        }

        head.setPlant(warehouse); // 工厂 订货门店号
        com.wumart.sap2.meipi.createso.TableOfZbapiR2Createsohead thead = factory.createTableOfZbapiR2Createsohead();
        thead.getItem().add(head);

        //SOPARTNERS
        com.wumart.sap2.meipi.createso.TableOfZbapiR2Createsopart tpart = factory.createTableOfZbapiR2Createsopart();
        com.wumart.sap2.meipi.createso.ZbapiR2Createsopart part1 = factory.createZbapiR2Createsopart();
        part1.setPartnRole("AG"); //AG:售达方、WE：送达方
        part1.setPartnNumb(wumartUsr); //客户编号1  客户编号（用于获取地址信息)
        com.wumart.sap2.meipi.createso.ZbapiR2Createsopart part2 = factory.createZbapiR2Createsopart();
        part2.setPartnRole("WE"); //AG:售达方、WE：送达方
        part2.setPartnNumb(soUserCode); //客户编号1  客户编号（用于获取地址信息）
        tpart.getItem().add(part1);
        tpart.getItem().add(part2);

        //SOITEM
        com.wumart.sap2.meipi.createso.TableOfZbapiR2Createsoitem titem = factory.createTableOfZbapiR2Createsoitem();
        for (CreateSoReqDetail detail : details) {
            String skuCode = detail.getSkuCode();
            if (StringUtils.isEmpty(skuCode)) {
                throw new BusinessException(CommonResult.ERROR, "CreateSOOrder Error Params! detail.outSkuCode is empty");
            }
            com.wumart.sap2.meipi.createso.ZbapiR2Createsoitem item = factory.createZbapiR2Createsoitem();
            item.setItmNumber(detail.getItemNum());
            item.setMaterial(skuCode); // 物料号 商品编码（物美）
            item.setSalesUnit("EA");
            if (detail.getAmount() == null) {
                String msg = "CreateSOOrder Error Params! detail.amount=null";
                logger.error(msg);
                throw new BusinessException(CommonResult.ERROR, msg);
            }
            item.setTemp01(detail.getAmount().toString());
            item.setReqQty(detail.getQuality()); // 以销售单位计的订单数量 订单数量
            titem.getItem().add(item);
        }

        com.wumart.sap2.meipi.createso.TableOfBapiret2 tbapiret = factory.createTableOfBapiret2();
        tbapiret.getItem().add(factory.createBapiret2());
        Holder<com.wumart.sap2.meipi.createso.TableOfZbapiR2Createsohead> hHead = new Holder<>(thead);
        Holder<com.wumart.sap2.meipi.createso.TableOfZbapiR2Createsoitem> hItem = new Holder<>(titem);
        Holder<com.wumart.sap2.meipi.createso.TableOfZbapiR2Createsopart> hPart = new Holder<>(tpart);
        com.wumart.sap2.meipi.createso.ZWSMEIPICREATESO zbinding = new com.wumart.sap2.meipi.createso.ZWEBSERVICEMEIPICREATESO().getBindingSOAP12();
        this.auth((BindingProvider) zbinding, order.getRegionCode());

        JSONObject params = new JSONObject(8);
        params.put("Return", tbapiret);
        params.put("Soheader", hHead);
        params.put("Soitem", hItem);
        params.put("Sopartners", hPart);
        String paramsStr = params.toJSONString();
        logger.info("zBapiMeipiCreateso params:" + paramsStr);
        MethodCallLogCollector.params(paramsStr);
        com.wumart.sap2.meipi.createso.TableOfBapiret2 tBapiret2;
        long start = System.currentTimeMillis();
        try {
            tBapiret2 = zbinding.zBapiMeipiCreateso(tbapiret, hHead, hItem, hPart);
        } catch (Throwable t) {
            MethodCallLogCollector.except(t, (int) (System.currentTimeMillis() - start));
            throw t;
        }
        long cost = System.currentTimeMillis() - start;
        JSONObject result = new JSONObject(4);
        result.put("return", tBapiret2);
        result.put("params", params);
        String resultStr = result.toJSONString();
        MethodCallLogCollector.result(resultStr, (int) (System.currentTimeMillis() - start));
        String msg = "zBapiMeipiCreateso cost[" + cost + "] result:" + resultStr;
        logger.info(msg);
        List<com.wumart.sap2.meipi.createso.Bapiret2> bapirets = tBapiret2.getItem();
        if (bapirets == null || bapirets.size() != 1) {
            throw new BusinessException(CommonResult.ERROR, msg);
        }

        com.wumart.sap2.meipi.createso.Bapiret2 bapiret = bapirets.get(0);
//        String id = bapiret.getId();
        String message = bapiret.getMessage();
        int lack = 0;
        if (message.contains("已创建部分交货")) {
            lack = 2;
        } else if (message.contains("未创建交货")) {
            lack = 1;
        }

        CreateSoRetHead so = new CreateSoRetHead();
        String soId = hHead.value.getItem().get(0).getSoDoc().trim();
        String type = bapiret.getType();
        String temp1 = bapiret.getMessageV2();
        String temp2 = bapiret.getMessageV3();
        if (type.toUpperCase().equals("S") && !soId.isEmpty()) {
            so.setCode(soId);
            so.setStatus(1);
        } else if (type.toUpperCase().equals("E") && temp1.toUpperCase().equals("DUPLICATE") && !temp2.isEmpty()) {
            so.setCode(temp2);
            so.setStatus(3);
        } else {
            so.setStatus(2);
            logger.error(msg);
            throw new BusinessException(CommonResult.ERROR, msg);
        }
        so.setLack(lack);
        List<CreateSoRetDetail> items = new ArrayList<>();
        for (com.wumart.sap2.meipi.createso.ZbapiR2Createsoitem element : hItem.value.getItem()) {
            String respQty = element.getTemp05().trim();
            CreateSoRetDetail item = new CreateSoRetDetail();
            item.setNo(element.getItmNumber());
            item.setLn(element.getTemp04().trim());
            item.setCode(element.getMaterial());
            item.setReqQty(element.getReqQty());
            item.setRespQty(respQty.isEmpty() ? BigDecimal.ZERO : new BigDecimal(respQty));
            item.setObd(element.getTemp02().trim());
            items.add(item);
        }
        so.setItems(items);
        return so;
    }

    @Override
    public ObdHead queryObdStatus(String soCode, Integer regionCode, boolean isReturn) throws BusinessException {
        Map<String, Integer> mapOBDCutConf = new HashMap<>();//TODO

        logger.info("so id : " + soCode);
        com.wumart.sap2.obdstatus.select.ObjectFactory factory = new com.wumart.sap2.obdstatus.select.ObjectFactory();
        com.wumart.sap2.obdstatus.select.ZEWMSO so = factory.createZEWMSO();
        so.setVBELN(soCode);
        com.wumart.sap2.obdstatus.select.ZEWMTTSO ttso = factory.createZEWMTTSO();
        ttso.getItem().add(so);
        Holder<com.wumart.sap2.obdstatus.select.ZEWMTTSO> hTtso = new Holder<>(ttso);

        Holder<com.wumart.sap2.obdstatus.select.ZEWMTTOBDOUT> hTtobdout = new Holder<>(factory.createZEWMTTOBDOUT());
        com.wumart.sap2.obdstatus.select.TABLEOFBAPIRET2 bapiret = factory.createTABLEOFBAPIRET2();

        com.wumart.sap2.obdstatus.select.ZMPOBDSTATUSSELECT zbinding = new com.wumart.sap2.obdstatus.select.ZMPOBDSTATUSSELECT_Service().getBinding();
        this.auth((BindingProvider) zbinding, null);
        JSONObject params = new JSONObject(4);
        params.put("IT_HEAD", hTtso);
        params.put("IT_ITEM", hTtobdout);
        params.put("RETURN", bapiret);
        logger.info("zmpOBDSTATUSSELECT params:" + params.toJSONString());
        long start = System.currentTimeMillis();
        com.wumart.sap2.obdstatus.select.TABLEOFBAPIRET2 tableofbapiret2 = zbinding.zmpOBDSTATUSSELECT(hTtso, hTtobdout, bapiret);
        long end = System.currentTimeMillis();
        long cost = end - start;
        JSONObject result = new JSONObject(4);
        result.put("return", tableofbapiret2);
        result.put("params", params);
        String msg = "zmpOBDSTATUSSELECT cost[" + cost + "] result:" + result.toJSONString();
        logger.info(msg);

        List<com.wumart.sap2.obdstatus.select.BAPIRET2> bapirets = tableofbapiret2.getItem();
        List<com.wumart.sap2.obdstatus.select.ZEWMOBDOUT> zewmobdouts = hTtobdout.value.getItem();
        List<com.wumart.sap2.obdstatus.select.ZEWMSO> zewmsos = hTtso.value.getItem();
        if (!CollectionUtils.isEmpty(bapirets) || CollectionUtils.isEmpty(zewmobdouts) || (zewmsos == null || zewmsos.size() != 1)) {
            if (bapirets.size() == 1 && bapirets.get(0).getTYPE().toUpperCase().equals("E")) {
                throw new BusinessException(CommonResult.ERROR, "query obd error!" + msg);
            } else {
                throw new BusinessException(CommonResult.ERROR, "query obd error!" + msg);
            }
        }

        String obdId = null;
        Set<String> mapWayBillls = new HashSet<>();
        List<ObdDetail> vItems = new ArrayList<>();
        for (final com.wumart.sap2.obdstatus.select.ZEWMOBDOUT zewmobdout : zewmobdouts) {
            String vbeln = zewmobdout.getVBELN();
            if (StringUtils.isEmpty(vbeln)) {
                throw new BusinessException(CommonResult.ERROR, "obd id not match " + zewmobdout.getVBELN() + " " + obdId + "\n" + msg);
            }
            if (obdId == null) {
                obdId = vbeln;
            } else if (!obdId.equals(vbeln)) {
                throw new BusinessException(CommonResult.ERROR, "obd id not match " + zewmobdout.getVBELN() + " " + obdId + "\n" + msg);
            }

            BigDecimal qty = zewmobdout.getLFIMG();
            String waybillId = zewmobdout.getTUNUM(); //运单号
            if (StringUtils.isEmpty(zewmobdout.getTUNUM())) {
                if (!isReturn && !BigDecimal.ZERO.equals(qty) && mapOBDCutConf.get(soCode) == null) {
                    throw new BusinessException(CommonResult.ERROR, "waybill is null!" + msg);
                }
            } else {
                mapWayBillls.add(waybillId);
            }

            ObdDetail item = new ObdDetail();
            item.setSupplySkuCode(zewmobdout.getMATNR());
            item.setSkuQty(qty);
//            item.setWaybillId(waybillId);
//            item.setDriverInfo(zewmobdout.getTUIDENT());
            vItems.add(item);
        }

        String sRealOBDId = "";
        int iOrginOBDCnt = 0;
        int iCutOBDCnt = 0;

        if (obdId.length() != 10) {
            throw new BusinessException(CommonResult.ERROR, "get obd meet code error so[" + soCode + "] obd[" + obdId + "]\n" + msg);
        }
        if (obdId.startsWith("01")) {
            iCutOBDCnt++;
        } else if (obdId.startsWith("02")) {
            iOrginOBDCnt++;
        }

        String waybillIds = StringUtils.collectionToCommaDelimitedString(mapWayBillls);
        if (!isReturn) {
            if (iOrginOBDCnt == 0 && iCutOBDCnt >= 2) {
                logger.info("get cut obd len ok so[" + soCode + "] cut[" + iCutOBDCnt + "]");
            } else if (iOrginOBDCnt >= 1 && iCutOBDCnt == 0) {
                //ok
            } else if (mapOBDCutConf.get(soCode) != null && mapOBDCutConf.get(soCode).intValue() == iCutOBDCnt) {
                //use conf
                logger.info("cut use conf " + soCode);
                //ok
            } else {
                throw new BusinessException(CommonResult.ERROR, "get obd not perfect so[" + soCode + "] " + iOrginOBDCnt + " " + iCutOBDCnt);//TODO
            }
            if (waybillIds.equals("")) {
                throw new BusinessException(CommonResult.ERROR, "get way bill id fail soid=" + soCode + "\n" + msg);
            }
        }
        logger.info("obd id :" + sRealOBDId);

        com.wumart.sap2.obdstatus.select.ZEWMSO zewmso = zewmsos.get(0);
        ObdHead head = new ObdHead();
        head.setSoCode(soCode);
//        head.setAmount(BigDecimal.ZERO);
        head.setObdCode(sRealOBDId);
        head.setWaybillCode(waybillIds);
        head.setCreateTime(zewmso.getERDAT());
        head.setDeliveryTime(zewmso.getVDATU());
        head.setPickTime(zewmso.getERDAT());
        head.setDetails(vItems);
        return head;
    }
}
