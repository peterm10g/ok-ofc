package com.lsh.ofc.worker.task;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.api.JobExecutionSingleShardingContext;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.core.base.SessionId;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.enums.SoStatus;
import com.lsh.ofc.core.mail.EmailHandler;
import com.lsh.ofc.core.proxy.service.WumartOfcServiceProxy;
import com.lsh.ofc.core.service.OfcSoCreateService;
import com.lsh.ofc.core.service.OfcSoService;
import com.lsh.ofc.proxy.context.WumartBasicContext;
import com.lsh.ofc.proxy.util.MethodCallLogCollector;
import com.lsh.ofc.worker.base.AbstractSequenceDataFlowJob;
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
 * 查询SO信息，刷新SO单状态
 * Created by panxudong on 16/11/30.
 */
@Component
public class OfcSoQueryJob extends AbstractSequenceDataFlowJob<OfcSoHead> {

    private final AtomicLong offset = new AtomicLong(0);
    @Autowired
    private OfcSoService ofcSoService;
    @Autowired
    private WumartOfcServiceProxy wumartOfcServiceProxy;
    @Autowired
    private OfcSoCreateService ofcSoCreateService;
    @Autowired
    private EmailHandler emailHandler;
    @Value("${so.query.delay.time}")
    private Integer queryInterval;

    private Set<SoStatus> getFetchSoStatus() {
        return new HashSet<>(Arrays.asList(SoStatus.CREATING));
    }

    @Override
    public List<OfcSoHead> fetchTasks(JobExecutionSingleShardingContext context) {
        long offset = this.offset.get();
        int size = context.getFetchDataCount();
        Set<SoStatus> statuses = this.getFetchSoStatus();
        String message = "查询SO信息，刷新SO单状态 fetchData(" + statuses + ", " + queryInterval + ", " + offset + ", " + size + ")";
        logger.info(message + " start...");
        try {
            List<OfcSoHead> list = ofcSoService.fetchSoByStatusAndTimeStamp(statuses, queryInterval.intValue(), offset, size);
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
    public boolean processTask(JobExecutionSingleShardingContext context, OfcSoHead ofcSoHead) {
        if (ofcSoHead == null) {
            return true;
        }

        Long orderCode = ofcSoHead.getOrderCode();
        String billCode = ofcSoHead.getSoBillCode();
        String prefix = "订单【" + orderCode + "】【" + billCode + "】查询SO信息，刷新SO单状态";
        logger.info(prefix + "处理开始！");
        if (!this.getFetchSoStatus().contains(SoStatus.valueOf(ofcSoHead.getSoStatus()))) {
            logger.warn(prefix + " process ignore...");
            return true;
        }
        String data = null;
        try {
            MethodCallLogCollector.init();
            MethodCallLogCollector.business(billCode, 10);
            data = wumartOfcServiceProxy.queryMeipiOrder(WumartOfcServiceProxy.OrderType.NORMAL, billCode,
                    WumartBasicContext.buildContext(ofcSoHead.getRegionCode(), ofcSoHead.getSupplierDc()));
            JSONObject soInfo = this.parseSoInfoFromJson(data);
            if (soInfo == null) {
                logger.warn(prefix + "处理未完成！");
                this.sendMail(orderCode, billCode, "查询SO信息，刷新SO单状态处理未完成！", data);
                return true;
            }
            CommonResult<Boolean> result = ofcSoCreateService.callback(soInfo);
            if (result.getData()) {
                logger.info(prefix + "处理成功！");
                return true;
            } else {
                logger.warn(prefix + "处理失败！");
                this.sendMail(orderCode, billCode, "查询SO信息，刷新SO单状态处理失败！", data);
                return true;
            }
        } catch (Throwable e) {
            if (e instanceof BusinessException) {
                this.sendMail(orderCode, billCode, "查询SO信息，刷新SO单状态处理异常！", data);
            }
            logger.error(prefix + "处理异常！" + e.getMessage(), e);
            return false;
        } finally {
            MethodCallLogCollector.upload();
            MethodCallLogCollector.clear();
        }
    }

    private JSONObject parseSoInfoFromJson(String json) throws BusinessException {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        JSONObject wmObject = JSONObject.parseObject(json);
        if (!"HANDLED".equals(wmObject.getString("status"))) {
            return null;
        }
        return wmObject.getJSONObject("soInfo");
    }

    private void sendMail(Long orderCode, String billCode, String message, String content) {
        StringBuilder builder = new StringBuilder(message);
        builder.append("订单号：").append(orderCode).append("\n");
        builder.append("业务单号：").append(billCode).append("\n");
        builder.append("查询响应报文：").append(content).append("\n");
        builder.append("日志ID：").append(SessionId.get()).append("\n");
        builder.append("订单履约结果超过" + this.queryInterval / 60 + "分钟未回传！").append("\n");
        this.emailHandler.buildEmail(EmailHandler.EmailTopic.SO_QUERY, builder.toString());
    }
}