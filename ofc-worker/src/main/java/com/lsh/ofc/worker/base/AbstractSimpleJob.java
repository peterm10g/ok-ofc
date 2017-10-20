package com.lsh.ofc.worker.base;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.lsh.ofc.core.base.SessionId;
import org.apache.log4j.Logger;

/**
 * Created by huangdong on 16/11/30.
 */
public abstract class AbstractSimpleJob extends AbstractSimpleElasticJob {
    
    protected final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void process(JobExecutionMultipleShardingContext context) {
        try {
            SessionId.reset();
            this.execute(context);
        } finally {
            SessionId.clear();
        }

    }

    protected abstract void execute(JobExecutionMultipleShardingContext context);
}