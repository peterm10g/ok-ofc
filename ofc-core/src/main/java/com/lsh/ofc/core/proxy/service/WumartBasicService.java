package com.lsh.ofc.core.proxy.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.core.enums.Switch;
import com.lsh.ofc.core.handler.WumartBasicHandler;
import com.lsh.ofc.proxy.context.WumartBasicContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 物美基础服务
 * Created by huangdong on 16/8/28.
 */
@Service
public class WumartBasicService { //implements InitializingBean {

//    @Value("${wumart.usr.config}")
//    private String usrConfig;
//
//    @Value("${wumart.cmp.config}")
//    private String cmpConfig;
//
//    @Value("${wumart.vsb.config}")
//    private String vsbConfig;
//
//    @Value("${wumart.mkt.config}")
//    private String mktConfig;
//
//    @Value("${wumart.zone.config}")
//    private String zoneConfig;
//
//    @Value("${wumart.orderSource.config}")
//    private String orderSourceConfig;

//    private Map<Integer, String> regionWumartUsrMap;
//
//    private Map<Integer, String> regionWumartCmpMap;
//
//    private Map<Integer, String> regionWumartVsbMap;
//
//    private Map<Integer, Integer> regionWumartMktMap;
//
//    private Map<Integer, String> regionWumartZoneMap;
//
//    private Map<Integer, Integer> regionWumartOrderSourceMap;

    @Autowired
    private WumartBasicHandler wumartBasicHandler;

    public String tansRegionWumartUsr(WumartBasicContext context) throws BusinessException {
        context.setProperty(WumartBasicContext.PARAM, WumartBasicContext.USR);
        String ret = this.wumartBasicHandler.getConfigValue(context);
        if (!StringUtils.hasLength(ret)) {
            throw new BusinessException(CommonResult.ERROR, "wumart usr not found... context=" + context.logInfo());
        }
        return ret;
    }

    public String tansRegion2WumartCmp(WumartBasicContext context) throws BusinessException {
        context.setProperty(WumartBasicContext.PARAM, WumartBasicContext.CMP);
        String ret = this.wumartBasicHandler.getConfigValue(context);
        if (!StringUtils.hasLength(ret)) {
            throw new BusinessException(CommonResult.ERROR, "wumart cmp not found... context=" + context.logInfo());
        }
        return ret;
    }

    public String tansRegion2WumartVsb(WumartBasicContext context) throws BusinessException {
        context.setProperty(WumartBasicContext.PARAM, WumartBasicContext.VSB);
        String ret = this.wumartBasicHandler.getConfigValue(context);
        if (!StringUtils.hasLength(ret)) {
            throw new BusinessException(CommonResult.ERROR, "wumart vsb not found... context=" + context.logInfo());
        }
        return ret;
    }

    public Integer tansRegion2WumartMkt(WumartBasicContext context) throws BusinessException {
        context.setProperty(WumartBasicContext.PARAM, WumartBasicContext.MKT);
        String ret = this.wumartBasicHandler.getConfigValue(context);
        if (!StringUtils.hasLength(ret)) {
            throw new BusinessException(CommonResult.ERROR, "wumart mkt not found... context=" + context.logInfo());
        }
        return Integer.valueOf(ret);
    }

    public String tansRegion2WumartZone(WumartBasicContext context) throws BusinessException {
        context.setProperty(WumartBasicContext.PARAM, WumartBasicContext.ZONE);
        String ret = this.wumartBasicHandler.getConfigValue(context);
        if (!StringUtils.hasLength(ret)) {
            throw new BusinessException(CommonResult.ERROR, "wumart zone not found... context=" + context.logInfo());
        }
        return ret;
    }

    public Integer tansRegion2WumartOrderSource(WumartBasicContext context) throws BusinessException {
        context.setProperty(WumartBasicContext.PARAM, WumartBasicContext.ORDERSOURCE);
        String ret = this.wumartBasicHandler.getConfigValue(context);
        if (!StringUtils.hasLength(ret)) {
            throw new BusinessException(CommonResult.ERROR, "wumart orderSource not found... context=" + context.logInfo());
        }
        return Integer.valueOf(ret);
    }

    public boolean isForceCancel(WumartBasicContext context) throws BusinessException {
        context.setProperty(WumartBasicContext.PARAM, WumartBasicContext.ISFORCECANCEL);
        String ret = this.wumartBasicHandler.getConfigValue(context);
        if (!StringUtils.hasLength(ret)) {
            throw new BusinessException(CommonResult.ERROR, "config isForceCancel not found... context=" + context.logInfo());
        }
        return Switch.valuesOf(ret) == Switch.ON;
    }

    public String getTuCodePrefix(WumartBasicContext context) throws BusinessException {
        context.setProperty(WumartBasicContext.PARAM, WumartBasicContext.TUPREFIX);
        String ret = this.wumartBasicHandler.getConfigValue(context);
//        if (!StringUtils.hasLength(ret)) {
//            throw new BusinessException(CommonResult.ERROR, "config tuPrefix not found... context=" + context.logInfo());
//        }
        return ret;
    }

    public String getWmsPath(WumartBasicContext context) throws BusinessException {
        context.setProperty(WumartBasicContext.PARAM, WumartBasicContext.WMSPATH);
        String ret = this.wumartBasicHandler.getConfigValue(context);
        if (!StringUtils.hasLength(ret)) {
            throw new BusinessException(CommonResult.ERROR, "config wmsPath not found... context=" + context.logInfo());
        }
        return ret;
    }

    public void validAddress(String province, String city, String district, String address) throws BusinessException {
        if (!StringUtils.hasText(province)) {
            throw new BusinessException(CommonResult.ERROR, "province can not be blank!");
        }
        if (!StringUtils.hasText(city)) {
            throw new BusinessException(CommonResult.ERROR, "city can not be blank!");
        }
        if (!StringUtils.hasText(district)) {
            throw new BusinessException(CommonResult.ERROR, "district can not be blank!");
        }
        if (!StringUtils.hasText(address)) {
            throw new BusinessException(CommonResult.ERROR, "address can not be blank!");
        }
        int length = province.length() + city.length() + district.length() + address.length();
        if (length + 3 > 35) { //物美SAP限制
            throw new BusinessException(CommonResult.ERROR, "address is too long!");
        }
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        Assert.hasText(this.usrConfig);
//        Assert.hasText(this.cmpConfig);
//
//        String[] usrArr = this.usrConfig.split(",");
//        this.regionWumartUsrMap = new HashMap<>();
//        for (String str : usrArr) {
//            String[] arr = str.split(":");
//            this.regionWumartUsrMap.put(Integer.valueOf(arr[0]), arr[1]);
//        }
//
//        String[] cmpArr = this.cmpConfig.split(",");
//        this.regionWumartCmpMap = new HashMap<>();
//        for (String str : cmpArr) {
//            String[] arr = str.split(":");
//            this.regionWumartCmpMap.put(Integer.valueOf(arr[0]), arr[1]);
//        }
//
//        String[] vsbArr = this.vsbConfig.split(",");
//        this.regionWumartVsbMap = new HashMap<>();
//        for (String str : vsbArr) {
//            String[] arr = str.split(":");
//            this.regionWumartVsbMap.put(Integer.valueOf(arr[0]), arr[1]);
//        }
//
//        String[] mktArr = this.mktConfig.split(",");
//        this.regionWumartMktMap = new HashMap<>();
//        for (String str : mktArr) {
//            String[] arr = str.split(":");
//            this.regionWumartMktMap.put(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
//        }
//
//        String[] zoneArr = this.zoneConfig.split(",");
//        this.regionWumartZoneMap = new HashMap<>();
//        for (String str : zoneArr) {
//            String[] arr = str.split(":");
//            this.regionWumartZoneMap.put(Integer.valueOf(arr[0]), arr[1]);
//        }
//
//        String[] orderSourceArr = this.orderSourceConfig.split(",");
//        this.regionWumartOrderSourceMap = new HashMap<>();
//        for (String str : orderSourceArr) {
//            String[] arr = str.split(":");
//            this.regionWumartOrderSourceMap.put(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
//        }
//    }
}
