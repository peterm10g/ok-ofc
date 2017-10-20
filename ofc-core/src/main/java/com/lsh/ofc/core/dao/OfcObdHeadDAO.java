package com.lsh.ofc.core.dao;

import com.lsh.ofc.core.base.BaseDAO;
import com.lsh.ofc.core.entity.OfcObdHead;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * OFC OBD头DAO
 * Created by huangdong on 16/9/9.
 */
@Repository
public interface OfcObdHeadDAO extends BaseDAO<OfcObdHead, Long> {

    /**
     * 更新返仓数量（增量）
     *
     * @param soBillCode
     * @param skuReturnQty
     * @return
     */
    int update4Return(@Param("soBillCode") String soBillCode, @Param("skuReturnQty") BigDecimal skuReturnQty);
}
