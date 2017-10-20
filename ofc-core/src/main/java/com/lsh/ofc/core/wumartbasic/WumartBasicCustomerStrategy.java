package com.lsh.ofc.core.wumartbasic;

import com.alibaba.fastjson.JSONObject;
import com.lsh.ofc.proxy.context.WumartBasicContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Project Name: lsh-ofc
 * Author: panxudong
 * 北京链商电子商务有限公司
 * Desc: 类功能描述
 * Package Name: com.lsh.ofc.proxy.handler
 * Time: 2017-07-27 下午3:21
 */
@Service
public class WumartBasicCustomerStrategy extends AbstractWumartBasicStrategy {

    @Override
    public boolean validate(WumartBasicContext context) {
        if (context.size() == 2) {
            if (super.basicValidate(context)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getConfigValue(WumartBasicContext context) {
        Map<String, String> configs = super.getConfigByRegion(context);
        if (configs.isEmpty()) {
            return null;
        }
        String config = configs.entrySet().iterator().next().getValue();
        if (!StringUtils.hasText(config)) {
            return null;
        }
        JSONObject configObject = JSONObject.parseObject(config);

        String key = String.valueOf(context.getProperty(WumartBasicContext.PARAM));
        return configObject.getString(key);
    }

}
