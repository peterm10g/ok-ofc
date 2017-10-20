package com.lsh.ofc.core.dao;

import com.lsh.ofc.core.base.BaseDAO;
import com.lsh.ofc.core.entity.OfcSoHead;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * OFC SO头DAO
 * Created by huangdong on 16/9/9.
 */
@Repository
public interface OfcSoHeadDAO extends BaseDAO<OfcSoHead, Long> {

    /**
     * 更新返仓数量（增量）
     *
     * @param soBillCode
     * @param skuReturnQty
     * @return
     */
    int update4Return(@Param("soBillCode") String soBillCode, @Param("skuReturnQty") BigDecimal skuReturnQty);

    /**
     * 根据时间戳,状态获取指定数量的OfcSoHead
     *
     * @param statuses
     * @param timeInterval
     * @param offset
     * @param size
     * @return
     */
    List<OfcSoHead> fetchSoByStatusAndTimeStamp(@Param("statuses") List<Integer> statuses, @Param("timeInterval") int timeInterval, @Param("offset") long offset, @Param("size") int size);

}
