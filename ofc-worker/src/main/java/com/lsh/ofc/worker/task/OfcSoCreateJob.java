package com.lsh.ofc.worker.task;

import com.alibaba.fastjson.JSON;
import com.lsh.ofc.core.entity.OfcOrderHead;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.entity.OfcTask;
import com.lsh.ofc.core.enums.FulfillStatus;
import com.lsh.ofc.core.enums.OfcTaskStatus;
import com.lsh.ofc.core.enums.OfcTaskType;
import com.lsh.ofc.core.service.OfcOrderService;
import com.lsh.ofc.core.service.OfcSoCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * 创建SO任务
 * Created by huangdong on 16/9/5.
 */
@Component
public class OfcSoCreateJob extends AbstractOfcTaskJob {

    @Autowired
    private OfcOrderService ofcOrderService;

    @Autowired
    private OfcSoCreateService ofcSoCreateService;

    @Override
    protected OfcTaskType getFetchTaskType() {
        return OfcTaskType.SO_CREATE;
    }

    @Override
    protected int processTask(OfcTask task) throws Exception {
        Long orderCode = task.getRefId();
        OfcOrderHead filter = new OfcOrderHead();
        filter.setOrderCode(orderCode);
        filter.setFulfillStatus(FulfillStatus.NEW.getValue());
        List<OfcOrderHead> list = ofcOrderService.findList(filter, true);
        if (list == null || list.size() != 1) {
            logger.warn("订单信息不存在，或者订单状态不为\"" + FulfillStatus.NEW.getDesc() + "\"！订单号=" + orderCode);
            return Integer.MAX_VALUE;
        }
        OfcOrderHead ofcOrderHead = list.get(0);
        List<OfcSoHead> sos = this.ofcSoCreateService.prepare(ofcOrderHead);
        if (CollectionUtils.isEmpty(sos)) {
            logger.error("SO信息不存在！订单号=" + orderCode);
            return Integer.MIN_VALUE;
        }
        logger.info("创建SO集合：" + JSON.toJSONString(sos));
        return ofcSoCreateService.process(ofcOrderHead, sos);
    }

    @Override
    protected List<OfcTask> fetchTasks(OfcTaskType type, Set<OfcTaskStatus> statuses, int shardingCount, List<Integer> shardingItems, int fetchSize) {
        return super.defaultFetchTasks(type, statuses, shardingCount, shardingItems, fetchSize);
    }
}
