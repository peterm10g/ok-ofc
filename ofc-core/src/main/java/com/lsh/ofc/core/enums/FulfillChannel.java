package com.lsh.ofc.core.enums;

import com.lsh.ofc.core.handler.CreateRoByLshWMSHandler;
import com.lsh.ofc.core.handler.CreateRoByWumartOFCHandler;
import com.lsh.ofc.core.handler.CreateRoByWumartSAPHandler;
import com.lsh.ofc.core.handler.CreateRoHandler;
import com.lsh.ofc.core.handler.CreateSoByLshWMSHandler;
import com.lsh.ofc.core.handler.CreateSoByWumartOFCHandler;
import com.lsh.ofc.core.handler.CreateSoByWumartSAPHandler;
import com.lsh.ofc.core.handler.CreateSoHandler;

/**
 * 订单履约渠道
 * Created by huangdong on 16/9/10.
 */
public enum FulfillChannel {

    LSH_WMS(1, "链商WMS", CreateSoByLshWMSHandler.class, CreateRoByLshWMSHandler.class),

    WUMART_OFC(2, "物美OFC", CreateSoByWumartOFCHandler.class, CreateRoByWumartOFCHandler.class),

    WUMART_SAP(3, "物美SAP", CreateSoByWumartSAPHandler.class, CreateRoByWumartSAPHandler.class);

    private final Integer value;

    private final String desc;

    private Class<? extends CreateSoHandler> createSoHandlerType;

    private Class<? extends CreateRoHandler> createRoHandlerType;

    private FulfillChannel(Integer value, String desc, Class<? extends CreateSoHandler> createSoHandlerType, Class<? extends CreateRoHandler> createRoHandlerType) {
        this.value = value;
        this.desc = desc;
        this.createSoHandlerType = createSoHandlerType;
        this.createRoHandlerType = createRoHandlerType;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public Class<? extends CreateSoHandler> getCreateSoHandlerType() {
        return createSoHandlerType;
    }

    public Class<? extends CreateRoHandler> getCreateRoHandlerType() {
        return createRoHandlerType;
    }

    public static FulfillChannel valueOf(Integer value) {
        for (FulfillChannel item : FulfillChannel.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }

    public static final boolean isWumartOfc(FulfillChannel fulfillChannel) {
        return fulfillChannel == FulfillChannel.WUMART_OFC;
    }
}
