package com.lsh.ofc.worker.task;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.entity.OfcObdHead;
import com.lsh.ofc.core.entity.OfcOperateLog;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.enums.BillType;
import com.lsh.ofc.core.enums.OfcOperateEnum;
import com.lsh.ofc.core.proxy.service.WumartBasicService;
import com.lsh.ofc.core.redis.RedisTemplate;
import com.lsh.ofc.core.service.EsService;
import com.lsh.ofc.core.service.OfcObdService;
import com.lsh.ofc.core.service.OfcOperateLogService;
import com.lsh.ofc.core.service.OfcSoService;
import com.lsh.ofc.core.service.impl.EsServiceImpl;
import com.lsh.ofc.proxy.context.WumartBasicContext;
import com.lsh.ofc.worker.base.AbstractSimpleJob;
import com.lsh.oms.api.model.es.OfcSoInfo4ES;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create So ElasticSearch Index
 * Created by panxudong on 16/12/07.
 */
@Component
public class OfcCreateIndexJob extends AbstractSimpleJob {

    private static final int FETCH_SIZE = 100;
    private static final BillType BILL_TYPE = BillType.SO;
    @Autowired
    private OfcSoService ofcSoService;
    @Autowired
    private OfcObdService ofcObdService;
    @Autowired
    private EsService esService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OfcOperateLogService ofcOperateLogService;
    @Autowired
    private WumartBasicService wumartBasicService;

    private Set<OfcOperateEnum> getFetchOperateType() {
        return new HashSet<>(Arrays.asList(OfcOperateEnum.SO_UNCREATED, OfcOperateEnum.SO_CREATING, OfcOperateEnum.SO_CREATED, OfcOperateEnum.SO_DELIVERED, OfcOperateEnum.SO_CREATE_FAIL));
    }

    @Override
    protected void execute(JobExecutionMultipleShardingContext context) {
        long offset = NumberUtils.toLong(redisTemplate.get(Constants.KEY_SO_ES_ID_OFFSET), 0);
        while (true) {
            String message = "根据OFC操作日志信息，创建或更新ES索引记录 fetchData(" + BILL_TYPE + ", " + offset + ", " + FETCH_SIZE + ")";
            logger.info(message + " start...");
            List<OfcOperateLog> list = ofcOperateLogService.fetchLogs(BILL_TYPE, offset, FETCH_SIZE);
            if (CollectionUtils.isEmpty(list)) {
                logger.info(message + " end... data_size=0");
                break;
            } else {
                logger.info(message + " end... data_size=" + list.size());
            }
            boolean update = false;
            long id = 0;
            try {
                for (OfcOperateLog operateLog : list) {
                    if (operateLog == null) {
                        continue;
                    }
                    id = operateLog.getId().longValue();
                    this.processTask(operateLog);
                    if (offset < id) {
                        offset = id;
                        update = true;
                    }
                }
                if (list.size() < FETCH_SIZE) {
                    break;
                }
            } catch (Throwable e) {
                logger.error("根据OFC操作日志信息，创建或更新ES索引记录 processData(" + id + ") error... " + e.getMessage(), e);
                break;
            } finally {
                if (update) {
                    logger.info("更新OFC操作日志查询offset id=" + offset);
                    this.redisTemplate.set(Constants.KEY_SO_ES_ID_OFFSET, Long.toString(offset));
                }
            }
        }
    }

    private boolean processTask(OfcOperateLog ofcOperateLog) throws Exception {
        logger.info("processTask:" + ofcOperateLog.getId());
        String billCode = ofcOperateLog.getBillCode();

        String prefix = "So单【" + billCode + "】查询OfcOperateLog信息，创建或更新So ElasticSearch索引 ";
        logger.info(prefix + "处理开始！");

        if (!this.getFetchOperateType().contains(OfcOperateEnum.valueOf(ofcOperateLog.getOperateType()))) {
            logger.warn(prefix + " process ignore...");
            return true;
        }

        try {
            OfcSoHead param = new OfcSoHead();
            param.setSoBillCode(billCode);
            OfcSoHead ofcSoHead = ofcSoService.findOne(param, Boolean.FALSE);
            if (ofcSoHead == null) {
                logger.warn(prefix + "So单信息为空!");
                return true;
            }

            OfcObdHead ofcObdHead = null;
            if (ofcOperateLog.getOperateType().intValue() == OfcOperateEnum.SO_DELIVERED.getType().intValue()) {
                OfcObdHead obdParam = new OfcObdHead();
                obdParam.setSoBillCode(billCode);
                ofcObdHead = ofcObdService.findOne(obdParam, Boolean.FALSE);
                if (ofcObdHead == null) {
                    logger.warn(prefix + "Obd单信息为空");
                    return true;
                }
            }

            String tuPrefix = wumartBasicService.getTuCodePrefix(WumartBasicContext.buildContext(ofcSoHead.getRegionCode(), ofcSoHead.getSupplierDc()));

            OfcSoInfo4ES ofcSoInfo = EsServiceImpl.getOfcSoInfo(ofcSoHead, ofcObdHead, tuPrefix);

            if (ofcOperateLog.getOperateType().intValue() == OfcOperateEnum.SO_CREATED.getType().intValue()) {
                ofcSoInfo.setSoCreateTime(Long.valueOf(ofcOperateLog.getCreateTime()));
                ofcSoInfo.setLackCreateQty(ofcSoHead.getTotalSkuOrderQty().subtract(ofcSoHead.getTotalSkuSupplyQty()));
            }

            if (ofcOperateLog.getOperateType().intValue() == OfcOperateEnum.SO_DELIVERED.getType().intValue()) {
                ofcSoInfo.setObdCreateTime(Long.valueOf(ofcOperateLog.getCreateTime()));
                ofcSoInfo.setLackPickQty(ofcSoHead.getTotalSkuSupplyQty().subtract(ofcSoHead.getTotalSkuDeliverQty()));
            }

            esService.saveOrUpdateIndexSync(ofcSoInfo);
        } catch (Exception e) {
            logger.error(prefix + "处理异常！" + e.getMessage(), e);
            throw e;
        }
        return true;
    }
}