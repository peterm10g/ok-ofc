package com.lsh.ofc.core.dao;

import com.lsh.ofc.core.base.BaseDAO;
import com.lsh.ofc.core.entity.OfcOperateLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OFC操作日志DAO
 * Created by huangdong on 16/9/9.
 */
@Repository
public interface OfcOperateLogDAO extends BaseDAO<OfcOperateLog, Long> {

    /**
     * 根据类型获取任务
     *
     * @param billType
     * @param offsetId
     * @param fetchSize
     * @return
     */
    List<OfcOperateLog> fetchLogs(@Param("billType") String billType, @Param("offsetId") long offsetId, @Param("fetchSize") int fetchSize);
}
