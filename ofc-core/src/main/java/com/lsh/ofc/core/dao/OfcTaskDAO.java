package com.lsh.ofc.core.dao;

import com.lsh.ofc.core.base.BaseDAO;
import com.lsh.ofc.core.entity.OfcTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OFC任务DAO
 * Created by huangdong on 16/8/28.
 */
@Repository
public interface OfcTaskDAO extends BaseDAO<OfcTask, Long> {

    /**
     * 根据类型和状态分片获取任务
     *
     * @param type
     * @param statuses
     * @param shardingCount
     * @param shardingItems
     * @param fetchSize
     * @return
     */
    List<OfcTask> fetchTasks(@Param("type") Integer type, @Param("statuses") List<Integer> statuses, @Param("shardingCount") int shardingCount, @Param("shardingItems") List<Integer> shardingItems, @Param("fetchSize") int fetchSize, @Param("delayTime") Integer delayTime);

    /**
     * 更新已处理任务状态
     *
     * @param id
     * @param preStatus
     * @param updateStatus
     * @return
     */
    int updateProcessedTask(@Param("id") Long id, @Param("expectStatus") Integer preStatus, @Param("updateStatus") Integer updateStatus);

    /**
     * 插入历史数据
     *
     * @param status
     * @return
     */
    int insert4History(@Param("status") Integer status);

    /**
     * 删除历史数据
     *
     * @param status
     * @return
     */
    int delete4History(@Param("status") Integer status);

    /**
     * 查询异常任务数据
     *
     * @param type
     * @param startTime
     * @return
     */
    List<OfcTask> fetchErrorTasks(@Param("type") int type, @Param("startTime") int startTime);
}