package com.lsh.ofc.proxy.util;

import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.container.spring.SpringContainer;
import com.alibaba.fastjson.JSONObject;
import com.lsh.ofc.proxy.service.EsServiceProxy;
import com.lsh.oms.api.model.es.OfcLog4ES;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangdong on 16/12/28.
 */
public class MethodCallLogCollector {

    private static final Logger LOGGER = Logger.getLogger(MethodCallLogCollector.class);

    private static final ThreadLocal<MethodCallLog> LOCAL = new ThreadLocal<>();

    private static final LinkedBlockingQueue<MethodCallLog> LOG_QUEUE = new LinkedBlockingQueue<>();

    private static final Byte DEFAULT_SYS = 1;

    private static String HOST;

    static {
        HOST = NetUtils.getLocalHost() + " " + System.getProperty("snowflake.node");
        new Timer().schedule(new CreateIndexTask(), 60 * 1000, 60 * 1000);
    }

    public static void init() {
        LOCAL.set(new MethodCallLog());
    }

    public static void clear() {
        LOCAL.remove();
    }

    protected static MethodCallLog get() {
        return LOCAL.get();
    }

    public static void upload() {
        MethodCallLog log = get();
        if (log != null && log.valid()) {
            long createTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            log.setCreateTime(createTime);
            LOG_QUEUE.offer(log);
        }
    }

    /**
     * @param bizId
     * @param bizType 10-创建SO；20-OBD回传
     */
    public static void business(String bizId, int bizType) {
        MethodCallLog log = get();
        if (log != null) {
            log.setBusiness(bizId, bizType);
        }
    }

    public static void params(String params) {
        MethodCallLog log = get();
        if (log != null) {
            log.setParams(params);
        }
    }

    public static void result(String result, int cost) {
        MethodCallLog log = get();
        if (log != null) {
            log.setResult(result, cost, false);
        }
    }

    public static void except(Throwable t, int cost) {
        MethodCallLog log = get();
        if (log != null) {
            log.setResult(ExceptionUtils.getStackTrace(t), cost, true);
        }
    }

    private static class MethodCallLog {

        private String bizId;

        private Integer bizType;

        private String params;

        private String result;

        private long createTime;

        private int cost;

        private boolean error = false;

        public void setBusiness(String bizId, Integer bizType) {
            this.bizId = bizId;
            this.bizType = bizType;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public void setResult(String result, int cost, boolean error) {
            this.result = result;
            this.cost = cost;
            this.error = error;
        }

        public String getBizId() {
            return bizId;
        }

        public Integer getBizType() {
            return bizType;
        }

        public String getParams() {
            return params;
        }

        public String getResult() {
            return result;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getCost() {
            return cost;
        }

        public boolean isError() {
            return error;
        }

        public boolean valid() {
            return !StringUtils.isAnyEmpty(this.bizId, this.params, this.result) && this.bizType != null;
        }
    }


    private static class CreateIndexTask extends TimerTask {

        @Override
        public void run() {
            LOGGER.info(" [Create ofc log index start]");

            List<OfcLog4ES> list = new ArrayList<>();
            while (true) {
                MethodCallLog log = LOG_QUEUE.poll();
                if (log == null) {
                    break;
                }
                if (!log.valid()) {
                    continue;
                }

                JSONObject content = new JSONObject();
                content.put("error", log.isError());
                content.put("cost", log.getCost());
                content.put("params", log.getParams());
                content.put("result", log.getResult());

                String uuid = UUID.randomUUID().toString();

                OfcLog4ES ofcLog = new OfcLog4ES();
                ofcLog.setId(uuid);
                ofcLog.setBillCode(log.getBizId());
                ofcLog.setLogType(log.getBizType() == null ? null : Long.parseLong(log.getBizType().toString()));
                ofcLog.setSys(DEFAULT_SYS);
                ofcLog.setIp(HOST);
                ofcLog.setContent(content.toJSONString());
                ofcLog.setCreateTime(log.getCreateTime());

                list.add(ofcLog);
            }

            int size = list.size();

            try {
                SpringContainer.getContext().getBean(EsServiceProxy.class).batchCreateIndex(list);
            } catch (Exception e) {
                LOGGER.error(" [Create ofc log index error][Error message:" + e.getMessage() + "]");
                LOGGER.error(e.getMessage(), e);
            }

            LOGGER.info(" [Create ofc log index end][Size:" + size + "]");
        }

    }

}
