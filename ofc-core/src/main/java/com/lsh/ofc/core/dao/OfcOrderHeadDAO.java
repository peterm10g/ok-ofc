package com.lsh.ofc.core.dao;

import com.lsh.ofc.core.base.BaseDAO;
import com.lsh.ofc.core.entity.OfcOrderHead;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * OFC订单头DAO
 * Created by huangdong on 16/8/1.
 */
@Repository
public interface OfcOrderHeadDAO extends BaseDAO<OfcOrderHead, Long> {

    /**
     * 更新返仓数量（增量）
     *
     * @param orderCode
     * @param skuReturnQty
     * @return
     */
    int update4Return(@Param("orderCode") Long orderCode, @Param("skuReturnQty") BigDecimal skuReturnQty);
}
