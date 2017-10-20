package com.lsh.ofc.proxy.dao.base;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangdong on 16/8/28.
 */
@Repository
public class BaseItemSkuDAO {

    private static final String SELECT_TAX = "select sku_id,tax from item_sku where is_valid=1 and market_id=:market_id and sku_id in (:sku_ids)";

    private static final String ATTR_SKU_ID = "sku_id";

    private static final String ATTR_TAX = "tax";

    private static final BigDecimal TAX_013 = BigDecimal.valueOf(0.13);

    private static final BigDecimal TAX_017 = BigDecimal.valueOf(0.17);

    // 4--普通 5--免农 -- 生鲜的11%
    private static final BigDecimal TAX_011 = BigDecimal.valueOf(0.11);

    @Resource(name = "baseNamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Map<String, BigDecimal> selectSkuTaxMap(Integer market, Collection<String> wmSkuCodes) {
        if (CollectionUtils.isEmpty(wmSkuCodes)) {
            return Collections.emptyMap();
        }
        Map<String, Object> params = new HashMap<>(4);
        params.put("market_id", market);
        params.put("sku_ids", wmSkuCodes);
        List<Map<String, String>> list = this.jdbcTemplate.query(SELECT_TAX, params, new RowMapper<Map<String, String>>() {
            @Override
            public Map<String, String> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String, String> map = new HashMap<>(4);
                map.put(ATTR_SKU_ID, rs.getString(ATTR_SKU_ID));
                map.put(ATTR_TAX, rs.getString(ATTR_TAX));
                return map;
            }
        });
        Map<String, BigDecimal> map = new HashMap<>();
        for (Map<String, String> item : list) {
            String tax = item.get(ATTR_TAX);
            if (tax.equals("0")) {
                map.put(item.get(ATTR_SKU_ID), BigDecimal.ZERO);
            } else if (tax.equals("1")) {
                map.put(item.get(ATTR_SKU_ID), TAX_017);
            } else if (tax.equals("2") || tax.equals("3")) {
                map.put(item.get(ATTR_SKU_ID), TAX_013);
                // 4--普通 5--免农 -- 生鲜的11%
            } else if (tax.equals("4") || tax.equals("5")) {
                map.put(item.get(ATTR_SKU_ID), TAX_011);
            } else if (tax.equals(".11") || tax.equals(".13") || tax.equals(".17") || tax.equals("00")) {
                map.put(item.get(ATTR_SKU_ID), new BigDecimal("0" + tax));
            } else {
                //TODO:例外税率记录不支持
                throw new RuntimeException("tax_rate[" + tax + "] is error!!!");
            }
        }
        return map;
    }
}
