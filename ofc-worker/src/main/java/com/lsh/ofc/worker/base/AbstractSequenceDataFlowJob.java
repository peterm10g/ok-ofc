package com.lsh.ofc.worker.base;

import com.dangdang.ddframe.job.api.JobExecutionSingleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.dataflow.AbstractIndividualSequenceDataFlowElasticJob;
import com.lsh.ofc.core.base.SessionId;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by huangdong on 16/11/30.
 */
public abstract class AbstractSequenceDataFlowJob<T> extends AbstractIndividualSequenceDataFlowElasticJob<T> {

    protected final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public List<T> fetchData(JobExecutionSingleShardingContext context) {
        try {
            SessionId.reset();
            return this.fetchTasks(context);
        } finally {
            SessionId.clear();
        }
    }

    @Override
    public boolean processData(JobExecutionSingleShardingContext context, T tast) {
        try {
            SessionId.reset();
            return this.processTask(context, tast);
        } finally {
            SessionId.clear();
        }
    }

    protected abstract List<T> fetchTasks(JobExecutionSingleShardingContext context);

    protected abstract boolean processTask(JobExecutionSingleShardingContext context, T tast);
}
