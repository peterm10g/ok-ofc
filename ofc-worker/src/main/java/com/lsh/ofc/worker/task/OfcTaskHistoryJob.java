package com.lsh.ofc.worker.task;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.enums.OfcTaskStatus;
import com.lsh.ofc.core.service.OfcTaskService;
import com.lsh.ofc.worker.base.AbstractSimpleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * OFC任务历史Job
 * Created by huangdong on 16/9/20.
 */
@Component
public class OfcTaskHistoryJob extends AbstractSimpleJob {

    @Autowired
    private OfcTaskService ofcTaskService;

    @Override
    public void execute(JobExecutionMultipleShardingContext context) {
        try {
            logger.info("ofc_task数据移动到历史表开始...");
            long start = System.currentTimeMillis();
            int cnt = this.ofcTaskService.moveHistory(OfcTaskStatus.SUCEESS);
            long end = System.currentTimeMillis();
            logger.info("ofc_task数据移动到历史表完成... 处理行数：" + cnt + "，耗时：" + (end - start));
        } catch (BusinessException e) {
            logger.error("ofc_task数据移动到历史表异常... " + e.getMessage(), e);
        }
    }
}
