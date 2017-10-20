package com.lsh.ofc.core.proxy.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.proxy.context.WumartBasicContext;
import com.lsh.ofc.proxy.model.CreateSoReqDetail;
import com.lsh.ofc.proxy.model.CreateSoReqHead;
import com.lsh.ofc.proxy.model.MeipiCustomer;
import com.lsh.ofc.proxy.util.HttpClientUtils;
import com.lsh.ofc.proxy.util.MethodCallLogCollector;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 物美OFC服务代理
 * Created by huangdong on 16/8/28.
 */
@Service
public class WumartOfcServiceProxy implements InitializingBean {

    private static final String SUBMIT_MEIPI_ORDER_URI = "/order/lianshangorder";
    private static final String QUERY_MEIPI_ORDER_URI = "/lians/orderStatus";
    private static final String SUBMIT_MEIPI_CUSTOMER_URI = "/lians/createCustomer";
    private static final String CANCEL_ORDER_URI = "/lians/cancelOrder";
    private final Logger logger = Logger.getLogger(this.getClass());
    private final CustomerBuilder customerBuilder = new CustomerBuilder();
    private volatile String contentType;
    @Value("${wumart.ofc.server.path}")
    private String serverPath;
    @Value("${wumart.ofc.username}")
    private String username;
    @Value("${wumart.ofc.password}")
    private String password;
    @Autowired
    private WumartBasicService wumartBasicService;

    /**
     * 添加美批用户
     *
     * @param customer
     * @return
     * @throws BusinessException
     */
    public String addMeipiCustomer(MeipiCustomer customer) throws BusinessException {
        customer.setSoUserCode("");
        return this.submitMeipiCustomer(customer);
    }

    /**
     * 修改美批用户
     *
     * @param customer
     * @return
     * @throws BusinessException
     */
    public String modMeipiCustomer(MeipiCustomer customer) throws BusinessException {
        return this.submitMeipiCustomer(customer);
    }

    /**
     * 提交美批用户
     *
     * @param customer
     * @return
     * @throws BusinessException
     */
    public String submitMeipiCustomer(MeipiCustomer customer) throws BusinessException {
        JSONObject customerObj = customerBuilder.createCustomerDto(customer);

        String uri = this.serverPath + SUBMIT_MEIPI_CUSTOMER_URI;
        String content = customerObj.toJSONString();

        String data = this.post(uri, content);
        if (!StringUtils.hasText(data)) {
            throw new BusinessException(CommonResult.ERROR, data);
        }
        JSONObject json = JSONObject.parseObject(data);
        String code = json.getString("code");
        if ("0000".equals(code)) { //成功
            if (!json.containsKey("data") || json.get("data") == null) {
                throw new BusinessException(CommonResult.ERROR, data);
            }

            JSONObject dataObj = json.getJSONObject("data");
            if (!dataObj.containsKey("customerNumber") || !StringUtils.hasText(dataObj.getString("customerNumber"))) {
                throw new BusinessException(CommonResult.ERROR, data);
            }

            String customerNumber = dataObj.getString("customerNumber");
            return customerNumber;
        } else {
            throw new BusinessException(CommonResult.ERROR, data);
        }
    }

    /**
     * 取消订单
     *
     * @param soCodes
     * @return
     * @throws BusinessException
     */
    public boolean cancelOrder(List<String> soCodes, WumartBasicContext wumartBasicContext) throws BusinessException {
        String businessIds = StringUtils.collectionToDelimitedString(soCodes, ",");
        JSONObject obj = new JSONObject(2);
        obj.put("businessIds", businessIds);
//        obj.put("orderSource", this.wumartBasicService.tansRegion2WumartOrderSource(wumartBasicContext));

        String uri = this.serverPath + CANCEL_ORDER_URI;
        String content = obj.toJSONString();

        String data = this.post(uri, content);
        if (!StringUtils.hasText(data)) {
            throw new BusinessException(CommonResult.ERROR, data);
        }

        JSONObject json = JSONObject.parseObject(data);
        String code = json.getString("code");
        if ("0000".equals(code)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 提交美批订单
     *
     * @param orderType
     * @param so
     * @return
     * @throws BusinessException
     */
    public boolean submitMeipiOrder(WumartOfcServiceProxy.OrderType orderType, CreateSoReqHead so) throws BusinessException {
        return this.submitMeipiOrder(orderType, so, null, null);
    }

    /**
     * 提交美批订单
     *
     * @param orderType
     * @param so
     * @param customer
     * @return
     * @throws BusinessException
     */
    public boolean submitMeipiOrder(WumartOfcServiceProxy.OrderType orderType, CreateSoReqHead so, MeipiCustomer customer) throws BusinessException {
        return this.submitMeipiOrder(orderType, so, customer, null);
    }

    /**
     * 提交美批订单
     *
     * @param orderType
     * @param so
     * @param batchNo
     * @return
     * @throws BusinessException
     */
    public boolean submitMeipiOrder(WumartOfcServiceProxy.OrderType orderType, CreateSoReqHead so, Long batchNo) throws BusinessException {
        return this.submitMeipiOrder(orderType, so, null, batchNo);
    }

    /**
     * 提交美批订单
     *
     * @param orderType
     * @param so
     * @param customer
     * @param batchNo
     * @return
     * @throws BusinessException
     */
    public boolean submitMeipiOrder(WumartOfcServiceProxy.OrderType orderType, CreateSoReqHead so, MeipiCustomer customer, Long batchNo) throws BusinessException {
        JSONObject json = new JSONObject(4);
        json.put("ofcOrder", this.buildOrder(so, orderType, batchNo));
        if (OrderType.NORMAL.equals(orderType)) {
            if (customer == null) {
                throw new NullPointerException("customer is null while orderType=" + orderType.getValue());
            } else {
                json.put("ofcConsumer", customerBuilder.createCustomerDto(so.getOrderCode(), customer));
            }
        }
        return this.submitMeipiOrder(json.toJSONString());
    }

    /**
     * 提交美批订单
     *
     * @param content
     * @return
     * @throws BusinessException
     */
    public boolean submitMeipiOrder(String content) throws BusinessException {
        String uri = this.serverPath + SUBMIT_MEIPI_ORDER_URI;
        String data = this.post(uri, content);
        if (!StringUtils.hasText(data)) {
            throw new BusinessException(CommonResult.ERROR, data);
        }
        JSONObject json = JSONObject.parseObject(data);
        Integer code = json.getInteger("code");
        if (Integer.valueOf(200).equals(code)) { //成功
            return true;
        } else {
            throw new BusinessException(CommonResult.ERROR, data);
        }
    }

    /**
     * 查询美批订单
     *
     * @param orderType
     * @param billCode
     * @return
     * @throws BusinessException
     */
    public String queryMeipiOrder(WumartOfcServiceProxy.OrderType orderType, String billCode, WumartBasicContext wumartBasicContext) throws BusinessException {
        return this.queryMeipiOrder(orderType, billCode, null, wumartBasicContext);
    }

    /**
     * 查询美批订单
     *
     * @param orderType
     * @param billCode
     * @param batchNo
     * @return
     * @throws BusinessException
     */
    public String queryMeipiOrder(WumartOfcServiceProxy.OrderType orderType, String billCode, Long batchNo, WumartBasicContext wumartBasicContext) throws BusinessException {
        JSONObject json = new JSONObject(4);
        json.put("orderType", orderType.getValue());
        json.put("businessId", billCode);
        json.put("batchNumber", batchNo);
//        json.put("orderSource", this.wumartBasicService.tansRegion2WumartOrderSource(wumartBasicContext));
        String uri = this.serverPath + QUERY_MEIPI_ORDER_URI; //TODO: 名字暂定
        return this.post(uri, json.toString());
    }

    /**
     * 状态订单信息
     *
     * @param so
     * @param orderType
     * @param batchNo
     * @return
     * @throws BusinessException
     */
    private JSONObject buildOrder(CreateSoReqHead so, WumartOfcServiceProxy.OrderType orderType, Long batchNo) throws BusinessException {
        JSONObject order = new JSONObject();
        if (OrderType.RETURN.equals(orderType)) {//仅退单需要传batchNumber,退单的唯一标示,区分多次退单
            if (batchNo == null) {
                throw new NullPointerException("batchNo is null while orderType=" + orderType.getValue());
            } else {
                order.put("batchNumber", batchNo);
            }
        }
        //TODO:orderSource 配置DC化
//        int src;
//        Integer wms = so.getFulfillWms();
//        if (Integer.valueOf(1).equals(wms)) {//物美WMS
//            src = Integer.valueOf(1002).equals(so.getRegionCode()) ? 5 : 2; //杭州是5，其它是2
//        } else if (Integer.valueOf(2).equals(wms)) {//链商WMS
//            src = 3;
//        } else {
//            throw new BusinessException(CommonResult.ERROR, "wms=" + wms + " is not support!");
//        }
        String bizCode = so.getOrderCode();
        order.put("orderType", orderType.getValue()); //正常订单:1, 补单:2, 退单:3, 取消:4
        order.put("businessId", bizCode); //订单号
        order.put("orderSource", this.wumartBasicService.tansRegion2WumartOrderSource(WumartBasicContext.buildContext(so.getRegionCode(), so.getWarehouse()))); //写死,2代表链商SO,3代表走WMS的SO
        order.put("plant", so.getWarehouse()); //仓库
        order.put("agPartnNumber", this.wumartBasicService.tansRegionWumartUsr(WumartBasicContext.buildContext(so.getRegionCode(), so.getWarehouse()))); //AG售达方客户编号(151001,151002)
        //TODO: transTime 交货时间待定
        order.put("transTime", null); //交货时间

        JSONArray items = new JSONArray();
        for (CreateSoReqDetail ofcSoDetail : so.getDetails()) {
            JSONObject item = new JSONObject();
            item.put("lineNumber", ofcSoDetail.getItemNum()); //行项目号
            item.put("businessId", bizCode); //订单号
            item.put("itemName", ofcSoDetail.getItemName()); //物料名称
            item.put("matnr", ofcSoDetail.getSkuCode()); //物料号
            item.put("quantity", ofcSoDetail.getQuality().toString()); //商品数量
//            item.put("salePrice", ofcSoDetail.getGoodsPrice().toString()); //售价
            item.put("saleUnit", "EA"); //单位
            items.add(item);
        }
        order.put("items", items);
        return order;
    }


    /**
     * 提交数据
     *
     * @param uri
     * @param content
     * @return
     * @throws BusinessException
     */
    private String post(String uri, String content) throws BusinessException {
        JSONObject obj = new JSONObject(4);
        obj.put("URI", uri);
        obj.put("content", content);
        MethodCallLogCollector.params(obj.toJSONString());
        StringEntity entity = new StringEntity(content, Consts.UTF_8);
        entity.setContentType(ContentType.APPLICATION_JSON.toString());
        for (int i = 0; i < 2; i++) {
            logger.info("[POST][URI=" + uri + "]" + content);
            CommonResult<String> result = HttpClientUtils.post(uri, entity, this.buildHeaders());
            logger.info("[POST][URI=" + uri + "]" + JSON.toJSONString(result));
            String data = result.getData();
            JSONObject json = JSONObject.parseObject(data);
            Integer code = json.getInteger("code");
            if (!CommonResult.SUCCESS.equals(result.getCode())) {
                throw new BusinessException(CommonResult.ERROR, result.getMessage());
            }
            if (Integer.valueOf(1).equals(code)) { //token为空或者过期
                String token = json.getString("gatewayToken");
                if (StringUtils.isEmpty(token)) {
                    throw new BusinessException(CommonResult.ERROR, data);
                }
                this.fillToken(token);
                continue;
            } else {
                return data;
            }
        }
        throw new BusinessException(CommonResult.ERROR, "调用失败，uri=" + uri + ", content=" + content);
    }

    private Header[] buildHeaders() {
        logger.info("OFC Content-Type:" + this.contentType);
        Header[] headers = {new BasicHeader(HttpHeaders.CONTENT_TYPE, this.contentType)};
        return headers;
    }

    private synchronized void fillToken(String token) throws BusinessException {
        String name = "gatewayToken=";
        String[] array = this.contentType.split(name);
        if (array.length < 1 || array.length > 2) {
            throw new BusinessException(CommonResult.ERROR, "contentType is error! " + this.contentType);
        }
        StringBuilder builder = new StringBuilder(array[0]);
        if (!array[0].endsWith(";")) {
            builder.append(";");
        }
        builder.append(name).append(token).append(";");
        this.contentType = builder.toString();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(this.serverPath, "\"wumart.ofc.server.path\" must have text; it must not be null, empty, or blank");
        Assert.hasText(this.username, "\"wumart.ofc.username\" must have text; it must not be null, empty, or blank");
        Assert.hasText(this.password, "\"wumart.ofc.password\" must have text; it must not be null, empty, or blank");
        StringBuilder builder = new StringBuilder("application/json;accountName=").append(this.username).append(";password=").append(this.password).append(";");
        this.contentType = builder.toString();
    }

    public enum OrderType {

        NORMAL(1, "正常"), REPLENISH(2, "补单"), RETURN(3, "退单"), CANCEL(4, "取消");

        private final Integer value;

        private final String desc;

        OrderType(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public static OrderType valueOf(Integer value) {
            for (OrderType item : OrderType.values()) {
                if (item.getValue().equals(value)) {
                    return item;
                }
            }
            return null;
        }

        public Integer getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    private class CustomerBuilder {

        public JSONObject createCustomerDto(String billCode, MeipiCustomer customer) throws BusinessException {
            CustomerDto customerDto = new CustomerForCreateSoDto(billCode, customer);
            return customerDto.toJson();
        }

        public JSONObject createCustomerDto(MeipiCustomer customer) throws BusinessException {
            CustomerDto customerDto = new CustomerForMeipiCustomerDto(customer);
            return customerDto.toJson();
        }
    }

    @Setter
    @Getter
    private abstract class CustomerDto {
        private String name1; //客户名称
        private String name2; //链商客户号
        private String sortl; //售达方客户号
        private String stras; //详细地址
        private String pstlz; //邮编
        private String ort01; //城市
        private String telf1; //电话号码
        private String lzone; //运输区域
        private String vsbed; //装运条件
        private String customerNumber;

        public CustomerDto(MeipiCustomer customer) throws BusinessException {
            String pcc = StringUtils.arrayToDelimitedString(new Object[]{customer.getProvince(), customer.getCity(), customer.getDistrict()}, " ");

            this.name1 = customer.getMarketName(); //超市名称
            this.name2 = "链商客户号"; //链商客户号(静态)
            this.sortl = wumartBasicService.tansRegionWumartUsr(WumartBasicContext.buildContext(customer.getRegionCode())); //售达方客户号
            this.stras = StringUtils.arrayToDelimitedString(new Object[]{pcc, customer.getAddress()}, " "); //街道
            this.pstlz = "100001"; //邮编(静态)(100001)
            this.ort01 = pcc; //城市
            this.telf1 = customer.getContactPhone(); //电话
            this.lzone = customer.getSoUserRegion(); //运输区域
            this.vsbed = wumartBasicService.tansRegion2WumartVsb(WumartBasicContext.buildContext(customer.getRegionCode())); //装运条件
            this.customerNumber = customer.getSoUserCode();
        }

        public abstract JSONObject toJson();
    }

    @Getter
    @Setter
    private class CustomerForCreateSoDto extends CustomerDto {
        private String businessId;

        public CustomerForCreateSoDto(String billCode, MeipiCustomer customer) throws BusinessException {
            super(customer);
            this.businessId = billCode;
        }

        @Override
        public JSONObject toJson() {
            JSONObject customer = (JSONObject) JSON.toJSON(this);
            return customer;
        }
    }

    @Getter
    @Setter
    private class CustomerForMeipiCustomerDto extends CustomerDto {
        private int orderSource;

        public CustomerForMeipiCustomerDto(MeipiCustomer customer) throws BusinessException {
            super(customer);
            this.orderSource = wumartBasicService.tansRegion2WumartOrderSource(WumartBasicContext.buildContext(customer.getRegionCode()));
        }

        @Override
        public JSONObject toJson() {
            JSONObject customer = (JSONObject) JSON.toJSON(this);
            return customer;
        }
    }
}
