package com.lsh.ofc.proxy.service;

import com.alibaba.fastjson.JSON;
import com.lsh.base.common.model.CommonResult;
//import com.lsh.oms.api.model.order.OrderHeadDTO;
import com.lsh.oms.api.model.status.UpdateStatusDto;
//import com.lsh.oms.api.service.query.IFindOrderService;
import com.lsh.oms.api.service.status.IUpdateStatusRpcService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * OMS服务代理
 * Created by huangdong on 16/8/28.
 */
@Service
public class OmsServiceProxy {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private IUpdateStatusRpcService service;

//    @Autowired
//    private IFindOrderService findOrderRpcService;

    /**
     * 更新订单状态
     *
     * @param orderCode
     * @param status
     * @param lack
     * @param soCodes
     * @return
     */
    public boolean updateOrderStatus(Long orderCode, int status, int lack, List<String> soCodes, Set<String> warehouseCodes) {
        UpdateStatusDto dto = new UpdateStatusDto();
        dto.setErp("OFC");
        dto.setOrderNo(orderCode);
        dto.setStatus(status);
        dto.setLackDeliveryType(lack);
        dto.setSoCode(StringUtils.collectionToDelimitedString(soCodes, ","));
        dto.setWarehouseCode(StringUtils.collectionToDelimitedString(warehouseCodes, ","));
        long start = System.currentTimeMillis();
        logger.info("IUpdateStatusRpcService-IUpdateStatusRpcService start... params:" + JSON.toJSONString(dto));
        CommonResult<?> result = service.updateStatusOFC(dto);
        long end = System.currentTimeMillis();
        logger.info("IUpdateStatusRpcService-IUpdateStatusRpcService end, cost[" + (end - start) + "]... result:" + JSON.toJSONString(result));
        Object obj = result.getData();
        return (obj != null && "100000".equals(result.getCode()));
    }

//    /**
//     * 根据订单号获取订单信息
//     *
//     * @param orderCode
//     * @return
//     */
//    public OrderHeadDTO getOrderByCode(Long orderCode) {
//        OrderHeadDTO dto = new OrderHeadDTO();
//        dto.setOrderCode(orderCode);
//        logger.info("IFindOrderService-getOrderInfo start... params:" + JSON.toJSONString(dto));
//        long start = System.currentTimeMillis();
//        String resultString = this.findOrderRpcService.getOrderInfo(dto);
//        long end = System.currentTimeMillis();
//        logger.info("IFindOrderService-getOrderInfo end, cost[" + (end - start) + "]... result:" + resultString);
//        CommonResult<List<OrderHeadDTO>> result = JSON.parseObject(resultString, CommonResult.class);
//        List<OrderHeadDTO> list = result.getData();
//        if (CollectionUtils.isEmpty(list)) {
//            return null;
//        }
//        OrderHeadDTO ret = list.get(0);
//        if (ret == null) {
//            return null;
//        }
//        return JSON.parseObject(JSON.toJSONString(ret), OrderHeadDTO.class);
//    }
}
