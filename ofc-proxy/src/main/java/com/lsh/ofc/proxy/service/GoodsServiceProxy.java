package com.lsh.ofc.proxy.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.proxy.model.Goods;
import com.lsh.ofc.proxy.util.HttpClientUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品服务代理
 * Created by huangdong on 16/8/28.
 */
@Service
public class GoodsServiceProxy {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Value("${goods.server.path}")
    private String goodsServerPath;

    private static final String GET_BY_GOODS_CODES_URI = "/goods/sku/getinfos";

    private static final String GET_BY_SUPPLY_URI = "/goods/supply/getinfos";

    private Header[] buildHeaders() {
        Header[] headers = {new BasicHeader("api-version", "1.1"), new BasicHeader("platform", "ofc"), new BasicHeader("sign", "md5")};
        return headers;
    }

    /**
     * 根据商品码集合获取商品信息集合
     *
     * @param goodsCodes
     * @param supplierId
     * @return
     * @throws BusinessException
     */
    public Map<Long, Goods> getGoodsInfoMapByGoodsCodes(Collection<Long> goodsCodes, Integer supplierId) throws BusinessException {
        String uri = this.goodsServerPath + GET_BY_GOODS_CODES_URI;
        if (CollectionUtils.isEmpty(goodsCodes)) {
            return Collections.emptyMap();
        }
        JSONArray skuIds = new JSONArray();
        for (Long goodsCode : goodsCodes) {
            skuIds.add(Collections.singletonMap("sku_id", goodsCode));
        }
        List<BasicNameValuePair> pairs = new ArrayList<>(2);
        pairs.add(new BasicNameValuePair("sku_ids", skuIds.toString()));
        pairs.add(new BasicNameValuePair("supplier_id", supplierId.toString()));
        HttpEntity entity = new UrlEncodedFormEntity(pairs, Consts.UTF_8);
        String result = HttpClientUtils.post(uri, entity, this.buildHeaders()).getData();
        JSONObject content = JSON.parseObject(result).getJSONObject("content");
        if (content == null) {
            logger.error("商品信息不存在！goodsCodes=" + goodsCodes);
            return Collections.emptyMap();
        }
        Map<Long, Goods> map = new HashMap<>();
        for (Long goodsCode : goodsCodes) {
            JSONObject item = content.getJSONObject(goodsCode.toString());
            if (item == null) {
                logger.error("商品信息不存在！goodsCode=" + goodsCode);
                continue;
            }
            BigDecimal supplyPrice = item.getBigDecimal("mvp");
            if (supplyPrice == null) {
                supplyPrice = item.getBigDecimal("supply_price");
            }
            Goods goods = new Goods();
            goods.setGoodsCode(goodsCode);
            goods.setGoodsSaleUnit(item.getInteger("sale_unit"));
            goods.setSkuCode(item.getLong("item_id"));
            goods.setSkuDefine(item.getInteger("sku_define"));
            goods.setSupplySkuCode(item.getString("code"));
            goods.setSupplyPrice(supplyPrice);
            map.put(goodsCode, goods);
        }
        return map;
    }
}
