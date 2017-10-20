package com.lsh.ofc.worker.task;

import com.lsh.ofc.core.base.SessionId;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.entity.OfcTask;
import com.lsh.ofc.core.enums.OfcTaskStatus;
import com.lsh.ofc.core.enums.OfcTaskType;
import com.lsh.ofc.core.enums.SoStatus;
import com.lsh.ofc.core.mail.EmailHandler;
import com.lsh.ofc.core.proxy.service.WumartOfcServiceProxy;
import com.lsh.ofc.core.service.OfcSoService;
import com.lsh.ofc.proxy.context.WumartBasicContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 取消订单验证任务
 * Created by huangdong on 16/9/5.
 */
@Component
public class OfcCancelValidateJob extends AbstractOfcTaskJob {

    @Autowired
    private WumartOfcServiceProxy wumartOfcServiceProxy;

    @Autowired
    private OfcSoService ofcSoService;

    @Autowired
    private EmailHandler emailHandler;

    @Value("${cancel.validate.delay.time}")
    private Integer delayTime;

    @Override
    protected OfcTaskType getFetchTaskType() {
        return OfcTaskType.CANCEL_VALIDATE;
    }

    @Override
    protected int processTask(OfcTask task) throws Exception {
        if (task == null) {
            return 1;
        }

        String soBillCode = task.getContent();

        String prefix = "取消So单【" + soBillCode + "】" + "OBD信息验证";
        logger.info(prefix + "处理开始！");

        OfcSoHead param = new OfcSoHead();
        param.setSoBillCode(soBillCode);
        OfcSoHead ofcSoHead = ofcSoService.findOne(param, Boolean.FALSE);
        if (ofcSoHead.getSoStatus().intValue() != SoStatus.CANCELED.getValue().intValue()) {
            return 1;
        }

        String result;
        try {
            result = wumartOfcServiceProxy.queryMeipiOrder(WumartOfcServiceProxy.OrderType.CANCEL, soBillCode,
                    WumartBasicContext.buildContext(ofcSoHead.getRegionCode(), ofcSoHead.getSupplierDc()));
        } catch (Exception e) {
            logger.error(prefix + "处理失败");
            logger.error(e.getMessage(), e);
            return -1;
        }

        //物美接口返回不正确 暂时跳过此定时任务的处理
//        JSONObject resultJson = JSONObject.parseObject(result);
//        String status = resultJson.getString("status");
//        if (!"HANDLED".equals(status)) {
//            logger.error(prefix + " 状态不正确,status:" + status);
//            return -1;
//        }
//        if (!resultJson.containsKey("obdInfo")) {
//            logger.error(prefix + " obdInfo不存在");
//            return -1;
//        }
//        JSONObject obdInfoJson = resultJson.getJSONObject("obdInfo");
//        if (obdInfoJson.containsKey("obdCode")) {
//            String obdCode = obdInfoJson.getString("obdCode");
//            this.sendMail(ofcSoHead.getOrderCode(), ofcSoHead.getSoBillCode(), obdCode, prefix + "异常", result);
//            logger.error(prefix + " obdCode存在");
//            return 1;
//        }

        logger.info(prefix + "处理完成.");
        return 1;
    }

    @Override
    protected List<OfcTask> fetchTasks(OfcTaskType type, Set<OfcTaskStatus> statuses, int shardingCount, List<Integer> shardingItems, int fetchSize) {
        return super.fetchTasksByDelayTime(type, statuses, shardingCount, shardingItems, fetchSize, delayTime);
    }

    private void sendMail(Long orderCode, String soBillCode, String obdCode, String title, String content) {
        StringBuilder builder = new StringBuilder(title);
        builder.append("订单号:").append(orderCode).append("\n");
        builder.append("业务单号:").append(soBillCode).append("\n");
        builder.append("OBD号:").append(obdCode).append("\n");
        builder.append("异常:取消订单成功,但物美生成发货任务").append("\n");
        builder.append("查询响应报文:").append(content).append("\n");
        builder.append("日志ID:").append(SessionId.get()).append("\n");
        this.emailHandler.buildEmail(EmailHandler.EmailTopic.CANCEL_VALIDATE, builder.toString());
    }
}
