package com.lsh.ofc.core.wumartbasic;

import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.redis.RedisTemplate;
import com.lsh.ofc.proxy.context.WumartBasicContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.Map;

/**
 * Project Name: lsh-ofc
 * Author: panxudong
 * 北京链商电子商务有限公司
 * Desc: 类功能描述
 * Package Name: com.lsh.ofc.proxy.handler
 * Time: 2017-07-27 下午3:21
 */
public abstract class AbstractWumartBasicStrategy implements IWumartBasicStrategy {

    @Autowired
    private RedisTemplate redisTemplate;

    protected final boolean basicValidate(WumartBasicContext context) {
        if (!WumartBasicContext.isContain(context)) {
            return false;
        }
        if (context.getProperty(WumartBasicContext.REGION) != null) {
            return true;
        } else {
            return false;
        }
    }

    protected final Map<String, String> getConfigByRegion(WumartBasicContext context) {
        Integer region = (Integer) context.getProperty(WumartBasicContext.REGION);
        Map<String, String> configs = redisTemplate.hgetAll(this.buildRedisKey(region));
        return configs;
    }

    private String buildRedisKey(Integer region) {
        String redisKey = MessageFormat.format(Constants.OFC_SUPPLIER_CONFIG, region);
        return redisKey;
    }

}
