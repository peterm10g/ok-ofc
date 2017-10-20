package com.lsh.ofc.worker.task;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.lsh.ofc.core.entity.OfcTask;
import com.lsh.ofc.core.enums.OfcTaskStatus;
import com.lsh.ofc.core.enums.OfcTaskType;
import com.lsh.ofc.core.redis.RedisTemplate;
import com.lsh.ofc.core.service.OfcTaskService;
import com.lsh.ofc.worker.base.AbstractThroughputDataFlowJob;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by huangdong on 16/9/12.
 */
public abstract class AbstractOfcTaskJob extends AbstractThroughputDataFlowJob<OfcTask> {

    protected final String prefix = "OFC_" + this.getClass().getName() + "_";

    @Autowired
    private OfcTaskService ofcTaskService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<OfcTask> fetchTasks(JobExecutionMultipleShardingContext context) {
        int shardingCount = context.getShardingTotalCount();
        List<Integer> shardingItems = context.getShardingItems();
        int fetchSize = context.getFetchDataCount();
        OfcTaskType type = this.getFetchTaskType();
        Set<OfcTaskStatus> statuses = this.getFetchTaskStatus();
        String message = "[OFCTask(type=" + type + ", status=" + statuses + ")] fetchData(items=" + shardingItems + ",size=" + fetchSize + ")";
        logger.info(message + " start...");
        try {
            List<OfcTask> list = this.fetchTasks(type, statuses, shardingCount, shardingItems, fetchSize);
            logger.info(message + " end... data_size=" + list.size());
            return list;
        } catch (Throwable e) {
            logger.error(message + " error... " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public boolean processTask(JobExecutionMultipleShardingContext context, OfcTask task) {
        if (task == null) {
            return true;
        }
        List<Integer> shardingItems = context.getShardingItems();
        int fetchSize = context.getFetchDataCount();
        Integer type = this.getFetchTaskType().getValue();
        Set<OfcTaskStatus> statuses = this.getFetchTaskStatus();
        String message = "[OFCTask(type=" + type + ", status=" + statuses + ")][processData(items=" + shardingItems + ",size=" + fetchSize + ")][task(id=" + task.getId() + ")]";
        logger.info(message + " start...");
        logger.info(message + " tast=" + JSON.toJSONString(task));
        OfcTaskStatus status = OfcTaskStatus.valueOf(task.getStatus());
        if (!type.equals(task.getType()) || !statuses.contains(status)) {
            logger.warn(message + " process ignore...");
            return true;
        }
        Long id = task.getId();
        String key = this.prefix + id;
        logger.info(message + " process start...");
        if (!this.redisTemplate.lock(key, 30)) {
            logger.warn(message + " task is processing... id=" + id);
            return true;
        }
        try {
            int ret = this.processTask(task);
            if (ret > 0) {
                this.ofcTaskService.updateProcessedTask(id, status, true);
                logger.info(message + " process success...");
            } else if (ret == 0) {
                logger.info(message + " process ignore...");
            } else {
                this.ofcTaskService.updateProcessedTask(id, status, false);
                logger.info(message + " process fail...");
            }
            return true;
        } catch (Throwable e) {
            logger.error(message + " process error... " + e.getMessage(), e);
            this.ofcTaskService.updateProcessedTask(id, status, false);
            return false;
        } finally {
            this.redisTemplate.unlock(key);
        }
    }

    protected abstract OfcTaskType getFetchTaskType();

    /**
     * 处理任务
     *
     * @param task
     * @return 大于0：处理成功；等于0：未处理；小于0：处理失败
     * @throws Exception
     */
    protected abstract int processTask(OfcTask task) throws Exception;

    protected abstract List<OfcTask> fetchTasks(OfcTaskType type, Set<OfcTaskStatus> statuses, int shardingCount,
                                                List<Integer> shardingItems, int fetchSize);

    protected List<OfcTask> defaultFetchTasks(OfcTaskType type, Set<OfcTaskStatus> statuses, int shardingCount,
                                              List<Integer> shardingItems, int fetchSize) {
        return this.ofcTaskService.fetchTasks(type, statuses, shardingCount, shardingItems, fetchSize);
    }

    protected List<OfcTask> fetchTasksByDelayTime(OfcTaskType type, Set<OfcTaskStatus> statuses, int shardingCount,
                                              List<Integer> shardingItems, int fetchSize, int delayTime) {
        return this.ofcTaskService.fetchTasks(type, statuses, shardingCount, shardingItems, fetchSize, delayTime);
    }

    protected Set<OfcTaskStatus> getFetchTaskStatus() {
        return new HashSet<>(Arrays.asList(OfcTaskStatus.NEW, OfcTaskStatus.ERROR));
    }
}