package com.lsh.ofc.core.constant;

import java.math.BigDecimal;

/**
 * Created by huangdong on 16/8/28.
 */
public final class Constants {

    public static final String ATTR_ITEMS = "items";

    public static final String ATTR_CODE = "code";

    public static final String ATTR_QTY = "qty";

    public static final BigDecimal TAX_013 = BigDecimal.valueOf(0.13);

    public static final BigDecimal TAX_017 = BigDecimal.valueOf(0.17);

    public static final String OFC_BILL_SYSTEM_WUMART_SAP = "WUMART_SAP";

    public static final String OFC_BILL_SYSTEM_WUMART_SAP_JISHOU = "WUMART_SAP_JISHOU";

    public static final String KEY_MEIPI_CUSTOMER_CREATE_LOCK = "OFC_MEIPI_CUSTOMER_CREATE_LOCK";

    public static final String kEY_MEIPI_CUSTOMER_CODES = "OFC_MEIPI_CUSTOMER_CODES";

    public static final String kEY_ERROR_TASK_ALERT_TIME = "OFC_ERROR_TASK_ALERT_TIME";

    public static final String TOPIC_OBD = "OFC_BILL";

    public static final String USER_ADDRESS_TRANS_LIMIT = "trans_limit";

    public static final String ORDER_H_MP_CUST_CODE = "mp_cust_code";

    public static final String ORDER_H_TRANS_TIME = "trans_time";

    public static final String ORDER_D_DETAIL_ID = "detail_id";

    public static final String ORDER_D_TYPE = "type";

    public static final String ORDER_D_SKU_DEFINE = "sku_define";


    public static final String SO_H_REF_SO_CODE = "ref_so_code"; //第三方code值,譬如:wms

    public static final String SO_H_FULFILL_CREATE_TIME = "fulfill_create_time";

    public static final String SO_H_MP_CUST_CODE = ORDER_H_MP_CUST_CODE;

    public static final String SO_H_SUPPLIER_CODE = "supplier_code";

    public static final String SO_H_TRANS_TIME = ORDER_H_TRANS_TIME;


    public static final String SO_D_TYPE = ORDER_D_TYPE;

    public static final String SO_D_OBD = "obd";


    public static final String OBD_H_BOX_NUM = "box_num";

    public static final String OBD_H_TURNOVER_BOX_NUM = "turnover_box_num";

    public static final String OBD_H_SCATTERED_BOX_NUM = "scattered_box_num";

    public static final String OBD_H_DRIVER_INFO = "driver_info";

    public static final String OBD_H_VEHICLE_TYPE = "vehicle_type";

    public static final String OBD_H_VEHICLE_TYPE_DESC = "vehicle_type_desc";

    public static final String OBD_H_CARRIER_CODE = "carrier_code";

    public static final String OBD_H_CARRIER_NAME = "carrier_name";

    public static final String OBD_H_CREATE_TIME = "create_time";

    public static final String OBD_H_PICK_TIME = "pick_time";

    public static final String OBD_H_DELIVERY_TIME = "delivery_time";

    public static final String OBD_H_WAYBILL_CODE = "waybill_code";

    public static final String OBD_H_MP_CUST_CODE = SO_H_MP_CUST_CODE;

    public static final String OBD_D_PACK_NUM = "pack_num";

    public static final String OBD_D_BOX_NUM = "box_num";

    public static final String OBD_D_LEFT_EA_NUM = "left_ea_num";

    public static final String RETURN_H_BATCH = "batch";

    public static final String RO_H_REF_RO_CODE = "ref_ro_code";

    public static final String RO_H_FULFILL_CREATE_TIME = "fulfill_create_time";

    public static final String KEY_SO_ES_ID_OFFSET = "OFC_SO_ES_ID_OFFSET";

    public static final String REDIS_HASH = "OFC_REDIS_HASH";

    public static final String WUMART_OFC_SWITCH = "OFC_WUMART_OFC_SWITCH";

    public static final String OFC_SUPPLIER_PREFIX = "OFC_SUPPLIER_{0}_{1}";

    public static final String OFC_SUPPLIER_CONFIG_LOCK = "OFC_SUPPLIER_CONFIG_LOCK";

    public static final String OFC_SUPPLIER_CONFIG_UPDATE_LOCK = "OFC_SUPPLIER_CONFIG_UPDATE_LOCK_{0}";

    public static final String OFC_SUPPLIER_CONFIG = "OFC_SUPPLIER_CONFIG_{0}";

    public static final String OFC_SO_WUMART_CALLBACK_SUPPLY_PRICE = "price";

}
