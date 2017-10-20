package com.lsh.ofc.worker.base;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.dataflow.AbstractIndividualThroughputDataFlowElasticJob;
import com.lsh.ofc.core.base.SessionId;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by huangdong on 16/11/30.
 */
public abstract class AbstractThroughputDataFlowJob<T> extends AbstractIndividualThroughputDataFlowElasticJob<T> {

    protected final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public List<T> fetchData(JobExecutionMultipleShardingContext context) {
        try {
            SessionId.reset();
            return this.fetchTasks(context);
        } finally {
            SessionId.clear();
        }
    }

    @Override
    public boolean processData(JobExecutionMultipleShardingContext context, T tast) {
        try {
            SessionId.reset();
            return this.processTask(context, tast);
        } finally {
            SessionId.clear();
        }
    }

    protected abstract List<T> fetchTasks(JobExecutionMultipleShardingContext context);

    protected abstract boolean processTask(JobExecutionMultipleShardingContext context, T tast);
}
