package com.lsh.ofc.core.service.impl;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.dao.OfcTaskDAO;
import com.lsh.ofc.core.entity.OfcTask;
import com.lsh.ofc.core.enums.OfcTaskStatus;
import com.lsh.ofc.core.enums.OfcTaskType;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.service.OfcTaskService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by huangdong on 16/8/28.
 */
@Service
public class OfcTaskServiceImpl implements OfcTaskService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private OfcTaskDAO ofcTaskDAO;

    @Override
    public int addTask(OfcTask task) {
        return ofcTaskDAO.insert(task);
    }

    @Override
    public int countTask(OfcTask task) {
        return ofcTaskDAO.count(task);
    }

    @Override
    public List<OfcTask> fetchTasks(OfcTaskType type, Set<OfcTaskStatus> statusSet, int shardingCount, List<Integer> shardingItems, int fetchSize) {
        return this.fetchTasks(type, statusSet, shardingCount, shardingItems, fetchSize, null);
    }

    @Override
    public List<OfcTask> fetchTasks(OfcTaskType type, Set<OfcTaskStatus> statusSet, int shardingCount, List<Integer> shardingItems, int fetchSize, int delayTime) {
        return this.fetchTasks(type, statusSet, shardingCount, shardingItems, fetchSize, Integer.valueOf(delayTime));
    }

    @Override
    public int updateProcessedTask(Long id, OfcTaskStatus preStatus, boolean success) {
        return this.ofcTaskDAO.updateProcessedTask(id, preStatus.getValue(), (success ? OfcTaskStatus.SUCEESS : OfcTaskStatus.ERROR).getValue());
    }

    @Transactional
    @Override
    public int moveHistory(OfcTaskStatus status) throws BusinessException {
        int cnt1 = this.ofcTaskDAO.insert4History(status.getValue());
        int cnt2 = this.ofcTaskDAO.delete4History(status.getValue());
        if (cnt1 != cnt2) {
            throw EC.ERROR.exception("插入数[" + cnt1 + "]与删除数[" + cnt2 + "]不相等!");
        }
        logger.info("ofc_task移动到历史表数量：" + cnt1);
        return cnt1;
    }

    @Override
    public List<OfcTask> fetchErrorTasks(OfcTaskType type, int startTime) {
        return this.ofcTaskDAO.fetchErrorTasks(type.getValue(), startTime);
    }

    private List<OfcTask> fetchTasks(OfcTaskType type, Set<OfcTaskStatus> statusSet, int shardingCount, List<Integer> shardingItems, int fetchSize, Integer delayTime) {
        if (shardingCount <= 0 || fetchSize <= 0 || CollectionUtils.isEmpty(shardingItems)) {
            return Collections.emptyList();
        }
        List<Integer> statuses = new ArrayList<>(statusSet.size());
        for (OfcTaskStatus status : statusSet) {
            statuses.add(status.getValue());
        }
        List<OfcTask> list = this.ofcTaskDAO.fetchTasks(type.getValue(), statuses, shardingCount, shardingItems, fetchSize, delayTime);
        return list;
    }

}
