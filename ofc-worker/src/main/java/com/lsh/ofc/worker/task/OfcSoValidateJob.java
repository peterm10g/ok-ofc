package com.lsh.ofc.worker.task;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.entity.OfcTask;
import com.lsh.ofc.core.enums.OfcTaskStatus;
import com.lsh.ofc.core.enums.OfcTaskType;
import com.lsh.ofc.core.enums.SoStatus;
import com.lsh.ofc.core.proxy.service.WumartOfcServiceProxy;
import com.lsh.ofc.core.service.OfcSoService;
import com.lsh.ofc.proxy.context.WumartBasicContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * SO创建验证任务
 * Created by huangdong on 16/9/5.
 */
@Component
public class OfcSoValidateJob extends AbstractOfcTaskJob {

    @Autowired
    private WumartOfcServiceProxy wumartOfcServiceProxy;

    @Autowired
    private OfcSoService ofcSoService;

    @Value("${so.validate.delay.time}")
    private Integer delayTime;

    @Override
    protected OfcTaskType getFetchTaskType() {
        return OfcTaskType.SO_VALIDATE;
    }

    @Override
    protected int processTask(OfcTask task) throws Exception {
        if (task == null) {
            return 1;
        }

        String soBillCode = task.getContent();

        String prefix = "So单【" + soBillCode + "】" + "OBD信息验证";
        logger.info(prefix + "处理开始！");

        OfcSoHead param = new OfcSoHead();
        param.setSoBillCode(soBillCode);
        OfcSoHead ofcSoHead = ofcSoService.findOne(param, Boolean.FALSE);
        if (ofcSoHead.getSoStatus().intValue() != SoStatus.CREATED.getValue().intValue()) {
            return 1;
        }

        String result;
        try {
            result = wumartOfcServiceProxy.queryMeipiOrder(WumartOfcServiceProxy.OrderType.NORMAL, soBillCode,
                    WumartBasicContext.buildContext(ofcSoHead.getRegionCode(), ofcSoHead.getSupplierDc()));
            logger.info(prefix + " Response result:" + result);
        } catch (Exception e) {
            logger.error(prefix + "处理失败");
            logger.error(e.getMessage(), e);
            return -1;
        }

        JSONObject resultJson = JSONObject.parseObject(result);
        String status = resultJson.getString("status");
        if (!"HANDLED".equals(status)) {
            logger.error(prefix + " 状态不正确,status:" + status);
            return -1;
        }
        if (!resultJson.containsKey("obdInfo")) {
            logger.error(prefix + " obdInfo不存在");
            return -1;
        }
        JSONObject obdInfoJson = resultJson.getJSONObject("obdInfo");
        if (!obdInfoJson.containsKey("obdCode")) {
            logger.error(prefix + " obdCode不存在");
            return -1;
        }
        String obdCode = obdInfoJson.getString("obdCode");
        if (StringUtils.isBlank(obdCode)) {
            logger.error(prefix + " obdCode信息不正确,obdCode:" + obdCode);
            return -1;
        }

        String ext = ofcSoHead.getExt();
        JSONObject extObj;
        if (StringUtils.isBlank(ext)) {
            extObj = new JSONObject();
        } else {
            extObj = JSONObject.parseObject(ext);
        }
        extObj.put("obdCode", obdCode);

        OfcSoHead update = new OfcSoHead();
        update.setExt(extObj.toJSONString());
        OfcSoHead filter = new OfcSoHead();
        filter.setId(ofcSoHead.getId());
        int ret = ofcSoService.updateByFilter(update, filter);

        logger.info(prefix + "处理完成.");
        return ret;
    }

    @Override
    protected List<OfcTask> fetchTasks(OfcTaskType type, Set<OfcTaskStatus> statuses, int shardingCount, List<Integer> shardingItems, int fetchSize) {
        return super.fetchTasksByDelayTime(type, statuses, shardingCount, shardingItems, fetchSize, delayTime);
    }
}
