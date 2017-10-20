package com.lsh.ofc.core.dao;

import com.lsh.ofc.core.base.BaseDAO;
import com.lsh.ofc.core.entity.OfcSoDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * OFC SO明细DAO
 * Created by huangdong on 16/9/9.
 */
@Repository
public interface OfcSoDetailDAO extends BaseDAO<OfcSoDetail, Long> {

    /**
     * 更新返仓数量（增量）
     *
     * @param soBillCode
     * @param id
     * @param skuReturnQty
     * @return
     */
    int update4Return(@Param("soBillCode") String soBillCode, @Param("id") Long id, @Param("skuReturnQty") BigDecimal skuReturnQty);
}
