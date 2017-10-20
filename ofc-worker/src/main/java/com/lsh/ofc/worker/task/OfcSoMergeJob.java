package com.lsh.ofc.worker.task;

import com.lsh.ofc.core.entity.OfcTask;
import com.lsh.ofc.core.enums.OfcTaskStatus;
import com.lsh.ofc.core.enums.OfcTaskType;
import com.lsh.ofc.core.service.OfcSoCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 合并SO任务
 * Created by huangdong on 16/9/10.
 */
@Component
public class OfcSoMergeJob extends AbstractOfcTaskJob {

    @Autowired
    private OfcSoCreateService ofcSoCreateService;

    @Override
    protected OfcTaskType getFetchTaskType() {
        return OfcTaskType.SO_MERGE;
    }

    @Override
    protected int processTask(OfcTask task) throws Exception {
        return this.ofcSoCreateService.merge(task.getRefId());
    }

    @Override
    protected List<OfcTask> fetchTasks(OfcTaskType type, Set<OfcTaskStatus> statuses, int shardingCount, List<Integer> shardingItems, int fetchSize) {
        return super.defaultFetchTasks(type, statuses, shardingCount, shardingItems, fetchSize);
    }
}
