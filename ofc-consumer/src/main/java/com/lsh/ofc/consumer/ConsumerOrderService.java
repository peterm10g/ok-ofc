package com.lsh.ofc.consumer;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.api.dto.OrderDetailDTO;
import com.lsh.ofc.api.dto.OrderHeadDTO;
import com.lsh.ofc.api.service.OrderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by huangdong on 16/9/1.
 */
public class ConsumerOrderService {

    public static void main(String[] args) throws BusinessException {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/*.xml");
//        context.start();
//        OrderService service = context.getBean(OrderService.class);
//
//        OrderHeadDTO order = new OrderHeadDTO();
//        order.setOrderCode(6184297411733622784L);
//        order.setRegionCode(1000);
//        order.setAddressCode(7723299307949736161L);
//        order.setAddressInfo("{\"id\":\"19674\",\"uid\":\"7720766033159350791\",\"address_id\":\"7723299307949736161\",\"contact_name\":\"\\u90b5\\u4fdd\\u4e1c\",\"contact_phone\":\"15330056076\",\"emerg_cellphone\":\"\",\"telephone\":\"\",\"market_name\":\"test123\",\"province\":\"11\",\"city\":\"1101\",\"county\":\"110101\",\"address\":\"\\u52a8\\u8f66\\u4eb2\\u543b\\u9e45\",\"status\":\"3\",\"is_default\":\"1\",\"is_deleted\":\"0\",\"ext\":\"{\\\"approve_info\\\":{\\\"uid\\\":\\\"8245716190935018754\\\",\\\"username\\\":\\\"\\\",\\\"approve_at\\\":1474447546}}\",\"created_at\":\"1469710522\",\"updated_at\":\"1474447546\",\"so_user_id\":\"0081000196\",\"so_user_region\":\"B0B1\",\"area\":\"11010102\",\"position\":\"{\\\"position\\\":{\\\"lng\\\":\\\"116.415426\\\",\\\"lat\\\":\\\"39.928435\\\"}}\",\"is_open\":\"1\",\"img_list\":\"[\\\"\\\"]\",\"geohash\":\"wx4g1hepupc0\",\"real_position\":\"{\\\"position\\\":{\\\"lng\\\":\\\"116.415426\\\",\\\"lat\\\":\\\"39.928435\\\"}}\",\"sales_position\":\"\",\"closed_remark_flag\":\"0\",\"closed_remark_info\":\"\",\"closed_time\":\"0\",\"market_size\":\"0\",\"market_location\":\"0\",\"boss_age\":\"0\",\"position_status\":\"0\",\"zone_id\":\"1000\",\"trans_limit\":\"2\",\"status_operate\":\"\",\"position_unusual_wholesale_status\":\"1\",\"position_unusual_dot_status\":\"1\",\"position_unusual_wholesale_order_ids\":\"\",\"position_unusual_dot_order_ids\":\"\",\"picture_position\":\"\",\"self_position\":\"\",\"position_unusual_sale_num\":\"0\",\"position_unusual_sale_status\":\"0\",\"province_name\":\"\\u5317\\u4eac\\u5e02\",\"city_name\":\"\\u5e02\\u8f96\\u533a\",\"county_name\":\"\\u4e1c\\u57ce\\u533a\",\"area_name\":\"\\u5317\\u65b0\\u6865\"}");
//        order.setOrderAmount(new BigDecimal("3589.10"));
//        order.setCreateTime(1474451401);
//
//        List<OrderDetailDTO> details = new ArrayList<>();
//
//        OrderDetailDTO detail = new OrderDetailDTO();
//        detail.setDetailId(2774377L);
//        detail.setGoodsCode(120019L);
//        detail.setGoodsName("雀巢苹果米粉225g/盒");
//        detail.setGoodsType(1);
//        detail.setGoodsSaleUnit(2);
//        detail.setGoodsPrice(new BigDecimal("1000.00"));
//        detail.setGoodsQty(new BigDecimal("2.000"));
//        detail.setGoodsAmount(detail.getGoodsPrice().multiply(detail.getGoodsQty()));
//        detail.setSkuCode(120017L);
//        detail.setSkuQty(detail.getGoodsQty().multiply(new BigDecimal(String.valueOf(detail.getGoodsSaleUnit()))));
//        details.add(detail);
//
//        OrderDetailDTO detail1 = new OrderDetailDTO();
//        detail1.setDetailId(2774378L);
//        detail1.setGoodsCode(100388L);
//        detail1.setGoodsName("燕京啤酒330ml/听");
//        detail1.setGoodsType(1);
//        detail1.setGoodsSaleUnit(24);
//        detail1.setGoodsPrice(new BigDecimal("1000.00"));
//        detail1.setGoodsQty(new BigDecimal("2.000"));
//        detail1.setGoodsAmount(detail1.getGoodsPrice().multiply(detail1.getGoodsQty()));
//        detail1.setSkuCode(100388L);
//        detail1.setSkuQty(detail1.getGoodsQty().multiply(new BigDecimal(String.valueOf(detail1.getGoodsSaleUnit()))));
//        details.add(detail1);
//
//        order.setDetails(details);
//
//        while(true) {
//            System.out.println("create order start ...");
//            try {
//                System.out.println(JSON.toJSONString(service.createOrder(order)));
//            } catch (Exception e) {
//                System.out.println(e.getClass());
//                System.out.println(e.getMessage());
//            }
//            System.out.println("create order end ...");
//            System.out.println("continue y/n");
//            Scanner in=new Scanner(System.in);
//            String temp = in.next();
//            if(!StringUtils.isEquals("y", temp)) {
//                break;
//            }
//        }
    }
}
