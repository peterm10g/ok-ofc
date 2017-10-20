package com.lsh.ofc.worker.task;

import com.dangdang.ddframe.job.api.JobExecutionSingleShardingContext;
import com.lsh.ofc.core.base.SessionId;
import com.lsh.ofc.core.entity.OfcRoHead;
import com.lsh.ofc.core.enums.RoStatus;
import com.lsh.ofc.core.mail.EmailHandler;
import com.lsh.ofc.core.service.OfcRoService;
import com.lsh.ofc.worker.base.AbstractSequenceDataFlowJob;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 查询RO信息，刷新RO单状态
 * Created by panxudong on 16/11/30.
 */
@Component
public class OfcRoQueryJob extends AbstractSequenceDataFlowJob<OfcRoHead> {

    protected final Logger logger = Logger.getLogger(this.getClass());
    private final AtomicLong offset = new AtomicLong(0);
    @Autowired
    private OfcRoService ofcRoService;
    @Autowired
    private EmailHandler emailHandler;
    @Value("${ro.query.delay.time}")
    private Integer queryInterval;

    private Set<RoStatus> getFetchRoStatus() {
        return new HashSet<>(Arrays.asList(RoStatus.CREATING));
    }

    @Override
    public List<OfcRoHead> fetchTasks(JobExecutionSingleShardingContext context) {
        long offset = this.offset.get();
        int size = context.getFetchDataCount();

        Set<RoStatus> statuses = this.getFetchRoStatus();
        String message = "查询RO信息，刷新RO单状态 fetchData(" + statuses + ", " + queryInterval + ", " + offset + ", " + size + ")";
        logger.info(message + " start...");
        try {
            List<OfcRoHead> list = ofcRoService.fetchRoByStatusAndTimeStamp(statuses, queryInterval.intValue(), offset, size);
            logger.info(message + " end... data_size=" + list.size());
            long id = (CollectionUtils.isEmpty(list)) ? 0 : list.get(list.size() - 1).getId().longValue();
            this.offset.compareAndSet(offset, id);
            logger.info(message + " 更新offset=" + id);
            return list;
        } catch (Throwable e) {
            logger.error(message + " error... " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public boolean processTask(JobExecutionSingleShardingContext context, OfcRoHead ofcRoHead) {
        if (ofcRoHead == null) {
            return true;
        }

        Long returnCode = ofcRoHead.getReturnCode();
        String billCode = ofcRoHead.getRoBillCode();
        String prefix = "返仓单【" + returnCode + "】【" + billCode + "】查询RO信息，刷新RO单状态";
        logger.info(prefix + "处理开始！");
        Integer roStatus = ofcRoHead.getRoStatus();
        if (!this.getFetchRoStatus().contains(RoStatus.valueOf(roStatus))) {
            logger.warn(prefix + " process ignore...");
            return true;
        }
        try {
            Integer ret = ofcRoService.refreshRoStatus(ofcRoHead);
            if (RoStatus.CREATED.getValue().equals(ret) || RoStatus.COMPLETED.getValue().equals(ret)) {
                logger.info(prefix + "处理成功！");
            } else {
                logger.warn(prefix + "未处理成功！");
                this.sendMail(returnCode, billCode, "查询RO信息，刷新RO单状态未处理成功！");
            }
            return true;
        } catch (Throwable e) {
            logger.error(prefix + "处理异常！" + e.getMessage(), e);
            this.sendMail(returnCode, billCode, "查询RO信息，刷新RO单状态处理异常！");
            return false;
        }
    }

    private void sendMail(Long returnCode, String billCode, String message) {
        StringBuilder builder = new StringBuilder(message);
        builder.append("返仓单号：").append(returnCode).append("\n");
        builder.append("业务单号：").append(billCode).append("\n");
        builder.append("日志ID：").append(SessionId.get()).append("\n");
        builder.append("返仓履约结果超过" + this.queryInterval / 60 + "分钟未回传！").append("\n");
        this.emailHandler.buildEmail(EmailHandler.EmailTopic.RO_QUERY, builder.toString());
    }
}