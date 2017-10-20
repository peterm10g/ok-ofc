package com.lsh.ofc.proxy.context;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Project Name: lsh-ofc
 * Author: panxudong
 * 北京链商电子商务有限公司
 * Desc: 类功能描述
 * Package Name: com.lsh.ofc.proxy.context
 * Time: 2017-07-27 下午2:43
 */
public class WumartBasicContext {

    public static final String USR = "usr";
    public static final String CMP = "cmp";
    public static final String VSB = "vsb";
    public static final String MKT = "mkt";
    public static final String ZONE = "zone";
    public static final String ORDERSOURCE = "orderSource";
    public static final String ISFORCECANCEL = "isForceCancel";
    public static final String TUPREFIX = "tuPrefix";
    public static final String WMSPATH = "wmsPath";

    public static final String REGION = "region";
    public static final String DC = "dc";
    public static final String PARAM = "param";
    private static final Set<String> paramSet = new HashSet<>();

    static {
        paramSet.add(USR);
        paramSet.add(CMP);
        paramSet.add(VSB);
        paramSet.add(MKT);
        paramSet.add(ZONE);
        paramSet.add(ORDERSOURCE);
        paramSet.add(ISFORCECANCEL);
        paramSet.add(TUPREFIX);
        paramSet.add(WMSPATH);
    }

    private Map<String, Object> context;

    public WumartBasicContext() {
        context = new HashMap<>();
    }

    public WumartBasicContext(Map<String, Object> context) {
        this.context = context;
    }

    public static WumartBasicContext buildContext(Integer region) {
        WumartBasicContext context = new WumartBasicContext();
        context.setProperty(REGION, region);
        return context;
    }

    public static WumartBasicContext buildContext(Integer region, String dc) {
        WumartBasicContext context = new WumartBasicContext();
        context.setProperty(REGION, region);
        context.setProperty(DC, dc);
        return context;
    }

    public static boolean isContain(WumartBasicContext context) {
        Object paramObj = context.getProperty(PARAM);
        if (paramObj == null) {
            return false;
        }
        String param = String.valueOf(paramObj);
        if (!StringUtils.hasText(param)) {
            return false;
        }
        if (!paramSet.contains(param)) {
            return false;
        }
        return true;
    }

    public int size() {
        return this.context.size();
    }

    public void setProperty(String key, Object value) {
        this.context.put(key, value);
    }

    public Object getProperty(String key) {
        return this.context.get(key);
    }

    public String logInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(REGION).append(":").append(this.getProperty(REGION)).append(";")
                .append(DC).append(":").append(this.getProperty(DC)).append(";")
                .append(PARAM).append(":").append(this.getProperty(PARAM));
        return sb.toString();
    }

}
