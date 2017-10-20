package com.lsh.ofc.core.dao;

import com.lsh.ofc.core.base.BaseDAO;
import com.lsh.ofc.core.entity.OfcRoHead;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OFC RO头DAO
 * Created by huangdong on 16/9/9.
 */
@Repository
public interface OfcRoHeadDAO extends BaseDAO<OfcRoHead, Long> {

    /**
     * 根据时间戳,状态获取指定数量的OfcRoHead
     *
     * @param statuses
     * @param timeInterval
     * @param offset
     * @param size
     * @return
     */
    List<OfcRoHead> fetchRoByStatusAndTimeStamp(@Param("statuses") List<Integer> statuses, @Param("timeInterval") int timeInterval, @Param("offset") long offset, @Param("size") int size);

}
