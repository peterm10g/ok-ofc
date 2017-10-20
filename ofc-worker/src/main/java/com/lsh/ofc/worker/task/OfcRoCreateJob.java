package com.lsh.ofc.worker.task;

import com.lsh.ofc.core.entity.OfcTask;
import com.lsh.ofc.core.enums.OfcTaskStatus;
import com.lsh.ofc.core.enums.OfcTaskType;
import com.lsh.ofc.core.service.OfcRoCreateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 创建RO任务
 * Created by huangdong on 16/9/5.
 */
@Component
public class OfcRoCreateJob extends AbstractOfcTaskJob {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private OfcRoCreateService ofcRoCreateService;

    @Override
    protected OfcTaskType getFetchTaskType() {
        return OfcTaskType.RO_CREATE;
    }

    @Override
    protected int processTask(OfcTask task) throws Exception {
        return this.ofcRoCreateService.process(task.getRefId()) ? 1 : -1;
    }

    @Override
    protected List<OfcTask> fetchTasks(OfcTaskType type, Set<OfcTaskStatus> statuses, int shardingCount, List<Integer> shardingItems, int fetchSize) {
        return super.defaultFetchTasks(type, statuses, shardingCount, shardingItems, fetchSize);
    }
}
