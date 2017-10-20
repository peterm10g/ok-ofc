package com.lsh.ofc.core.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.OfcTask;
import com.lsh.ofc.core.enums.OfcTaskStatus;
import com.lsh.ofc.core.enums.OfcTaskType;

import java.util.List;
import java.util.Set;

/**
 * OFC任务Service
 * Created by huangdong on 16/8/28.
 */
public interface OfcTaskService {

    /**
     * 添加任务
     *
     * @param task
     * @return
     */
    int addTask(OfcTask task);

    /**
     * @param task
     * @return
     */
    int countTask(OfcTask task);

    /**
     * 根据类型和状态分片获取任务
     *
     * @param type
     * @param statusSet
     * @param shardingCount
     * @param shardingItems
     * @param fetchSize
     * @return
     */
    List<OfcTask> fetchTasks(OfcTaskType type, Set<OfcTaskStatus> statusSet, int shardingCount, List<Integer> shardingItems, int fetchSize);

    /**
     * 根据类型和状态分片获取任务
     *
     * @param type
     * @param statusSet
     * @param shardingCount
     * @param shardingItems
     * @param fetchSize
     * @return
     */
    List<OfcTask> fetchTasks(OfcTaskType type, Set<OfcTaskStatus> statusSet, int shardingCount, List<Integer> shardingItems, int fetchSize, int delayTime);

    /**
     * 更新已处理任务
     *
     * @param id
     * @param preStatus
     * @param success
     * @return
     */
    int updateProcessedTask(Long id, OfcTaskStatus preStatus, boolean success);

    /**
     * 移动历史数据
     *
     * @param status
     * @return
     */
    int moveHistory(OfcTaskStatus status) throws BusinessException;

    /**
     * 查询异常任务数据
     *
     * @param type
     * @param startTime
     * @return
     */
    List<OfcTask> fetchErrorTasks(OfcTaskType type, int startTime);
}
