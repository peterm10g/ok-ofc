package com.lsh.ofc.proxy.service;

import com.alibaba.fastjson.JSON;
import com.lsh.atp.api.model.Hold.HoldDetailQueryRequest;
import com.lsh.atp.api.model.Hold.HoldDetailQueryResponse;
import com.lsh.atp.api.model.baseVo.ItemDc;
import com.lsh.atp.api.service.hold.IHoldDatailQueryRPCService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ATP服务代理
 * Created by huangdong on 16/8/28.
 */
@Service
public class AtpServiceProxy {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private IHoldDatailQueryRPCService service;

    /**
     * 获取订单商品库存扣减信息{SKU编号,{DC,数量}}
     *
     * @param orderCode
     * @return
     */
    public Map<Long, Map<String, BigDecimal>> querySkuDcQtyMap(Long orderCode) {
        HoldDetailQueryRequest req = new HoldDetailQueryRequest();
        req.setSequence(orderCode.toString());
        req.setChannel("1");//TODO:OMS请求扣减库存设置值
        long start = System.currentTimeMillis();
        logger.info("IHoldDatailQueryRPCService-queryHoldDetail start... params:" + JSON.toJSONString(req));
        HoldDetailQueryResponse resp = service.queryHoldDetail(req);
        long end = System.currentTimeMillis();
        logger.info("IHoldDatailQueryRPCService-queryHoldDetail end, cost[" + (end - start) + "]... result:" + JSON.toJSONString(resp));
        List<ItemDc> items = resp.getItems();
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyMap();
        }
        Map<Long, Map<String, BigDecimal>> map = new HashMap<>(4);
        for (ItemDc item : items) {
            Long skuCode = item.getItemId();
            Map<String, BigDecimal> dcQty = map.get(skuCode);
            if (dcQty == null) {
                dcQty = new HashMap<>();
                map.put(skuCode, dcQty);
            }
            String dc = item.getDc();
            BigDecimal qty = dcQty.get(dc);
            if (qty == null) {
                qty = BigDecimal.valueOf(item.getQty());
            } else {
                qty = qty.add(BigDecimal.valueOf(item.getQty()));
            }
            dcQty.put(dc, qty);
        }
        return map;
    }
}
