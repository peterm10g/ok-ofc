package com.lsh.ofc.core.handler;

import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.fastjson.JSON;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.entity.OfcSupplier;
import com.lsh.ofc.core.mail.EmailHandler;
import com.lsh.ofc.core.redis.RedisTemplate;
import com.lsh.ofc.core.service.OfcSupplierService;
import com.lsh.ofc.core.wumartbasic.IWumartBasicStrategy;
import com.lsh.ofc.proxy.context.WumartBasicContext;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Project Name: lsh-ofc
 * Author: panxudong
 * 北京链商电子商务有限公司
 * Desc: 类功能描述
 * Package Name: com.lsh.ofc.proxy.handler
 * Time: 2017-07-27 下午3:17
 */
@Service
public class WumartBasicHandler {

    private static final Logger logger = Logger.getLogger(WumartBasicHandler.class);
    private static String HOST = NetUtils.getLocalHost() + " " + System.getProperty("snowflake.node");
    @Autowired
    private List<IWumartBasicStrategy> strategies;
    @Autowired
    private OfcSupplierService ofcSupplierService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private EmailHandler emailHandler;

    @PostConstruct
    public void init() {
        this.initConfig();

        new Timer().schedule(new WumartBasicHandler.CheckWumartBasicRedisInfoTask(), 300 * 1000, 300 * 1000);
    }

    public void updateConfig(OfcSupplier supplier) {
        String key = supplier.getId().toString();

        String redisKey = MessageFormat.format(Constants.OFC_SUPPLIER_CONFIG_UPDATE_LOCK, supplier.getId());
        if (!this.redisTemplate.lock(redisKey, 30)) {
            logger.warn("updating ofc supplier config... id:" + key);
            return;
        }

        logger.info("update ofc supplier config start. id:" + key);
        try {
            Integer regionCode = supplier.getRegionCode();
            String dc = supplier.getSupplierDc();
            String config = supplier.getConfig();

            String configRedisKey = MessageFormat.format(Constants.OFC_SUPPLIER_CONFIG, regionCode);
            if (!this.redisTemplate.hexists(configRedisKey, dc)) {
                this.redisTemplate.hset(configRedisKey, dc, config);
            } else {
                String targetConfig = this.redisTemplate.hget(configRedisKey, dc);

                this.setConfig(redisKey, dc, config, targetConfig);
            }

            logger.info("update ofc supplier config end. id:" + key);
        } catch (Throwable e) {
            logger.error("update ofc supplier config error. id:" + key);
            logger.error(e.getMessage(), e);
            this.handleException(e, "update", JSON.toJSONString(supplier));
        } finally {
            this.redisTemplate.unlock(redisKey);
        }
    }

    public String getConfigValue(WumartBasicContext context) {
        IWumartBasicStrategy strategy = null;
        for (IWumartBasicStrategy iStrategy : strategies) {
            if (iStrategy.validate(context)) {
                strategy = iStrategy;
                break;
            }
        }

        if (strategy == null) {
            return null;
        }

        return strategy.getConfigValue(context);
    }

    private void initConfig() {
        if (!this.redisTemplate.lock(Constants.OFC_SUPPLIER_CONFIG_LOCK, 120)) {
            logger.warn("initializing ofc supplier config...");
            return;
        }

        logger.info("initialize ofc supplier config start.");
        try {
            OfcSupplier params = new OfcSupplier();
            List<OfcSupplier> list = this.ofcSupplierService.findList(params);

            Map<Integer, Map<String, OfcSupplier>> supplierMap = new HashMap<>();
            for (OfcSupplier supplier : list) {
                Integer regionCode = supplier.getRegionCode();
                Map<String, OfcSupplier> suppliers = supplierMap.get(regionCode);
                if (suppliers == null) {
                    suppliers = new HashMap<>();
                    supplierMap.put(regionCode, suppliers);
                }

                suppliers.put(supplier.getSupplierDc(), supplier);
            }

            for (Map.Entry<Integer, Map<String, OfcSupplier>> entry : supplierMap.entrySet()) {
                Integer regionCode = entry.getKey();
                Map<String, OfcSupplier> dcSupplierMap = entry.getValue();

                String redisKey = MessageFormat.format(Constants.OFC_SUPPLIER_CONFIG, regionCode);
                Map<String, String> configs = this.redisTemplate.hgetAll(redisKey);

                if (configs != null && configs.isEmpty()) {
                    for (Map.Entry<String, String> config : configs.entrySet()) {
                        String dc = config.getKey();
                        if (!dcSupplierMap.containsKey(dc)) {
                            this.redisTemplate.hdel(redisKey, dc);
                        } else {
                            OfcSupplier supplier = dcSupplierMap.get(dc);
                            String sourceConfig = supplier.getConfig();
                            String targetConfig = config.getValue();

                            this.setConfig(redisKey, dc, sourceConfig, targetConfig);

                            dcSupplierMap.remove(dc);
                        }
                    }
                }

                for (Map.Entry<String, OfcSupplier> supplierEntry : dcSupplierMap.entrySet()) {
                    this.redisTemplate.hset(redisKey, supplierEntry.getKey(), supplierEntry.getValue().getConfig());
                }
            }

            logger.info("initialize ofc supplier config end.");
        } catch (Throwable e) {
            logger.error("initialize ofc supplier config error.");
            logger.error(e.getMessage(), e);
            this.handleException(e, "initialize", "");
        } finally {
            this.redisTemplate.unlock(Constants.OFC_SUPPLIER_CONFIG_LOCK);
        }
    }

    private void setConfig(String redisKey, String dc, String sourceConfig, String targetConfig) {
        if (StringUtils.hasText(sourceConfig)) {
            if (!sourceConfig.equals(targetConfig)) {
                this.redisTemplate.hset(redisKey, dc, sourceConfig);
            }
        } else {
            if (StringUtils.hasText(targetConfig)) {
                this.redisTemplate.hdel(redisKey, dc);
            }
        }
    }

    private void handleException(Throwable throwable, String type, String params) {
        StringBuilder sb = new StringBuilder();
        sb.append("操作:").append(type).append("\nIP:").append(HOST).append("\n参数:").append(params).append("\n异常:")
                .append(ExceptionUtils.getStackTrace(throwable));
        this.emailHandler.buildEmail(EmailHandler.EmailTopic.SET_WUMART_CONFIG, sb.toString());
    }

    private class CheckWumartBasicRedisInfoTask extends TimerTask {

        @Override
        public void run() {
            initConfig();
        }

    }

}
