package com.lsh.ofc.worker.task;

import com.lsh.ofc.core.entity.OfcTask;
import com.lsh.ofc.core.enums.OfcTaskStatus;
import com.lsh.ofc.core.enums.OfcTaskType;
import com.lsh.ofc.core.service.OfcCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 刷新客户信息任务
 * Created by huangdong on 16/11/16.
 */
@Component
public class OfcCustomerRefreshJob extends AbstractOfcTaskJob {

    @Autowired
    private OfcCustomerService ofcCustomerService;

    @Override
    protected OfcTaskType getFetchTaskType() {
        return OfcTaskType.CUSTOMER_REFRESH;
    }

    @Override
    protected int processTask(OfcTask task) throws Exception {
        this.ofcCustomerService.refreshCustomer(task.getRefId());
        return 1;
    }

    @Override
    protected List<OfcTask> fetchTasks(OfcTaskType type, Set<OfcTaskStatus> statuses, int shardingCount, List<Integer> shardingItems, int fetchSize) {
        return super.defaultFetchTasks(type, statuses, shardingCount, shardingItems, fetchSize);
    }
}
