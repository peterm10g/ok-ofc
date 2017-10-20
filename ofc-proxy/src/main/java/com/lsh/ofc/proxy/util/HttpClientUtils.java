package com.lsh.ofc.proxy.util;

import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * Created by huangdong on 16/8/28.
 */
public class HttpClientUtils {

    private static final Logger LOGGER = Logger.getLogger(HttpClientUtils.class);

    public static CommonResult<String> post(String uri, HttpEntity entity, Header... headers) throws BusinessException {
        LOGGER.info("[POST][" + uri + "] start");
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost method = new HttpPost(uri);
        if (!ArrayUtils.isEmpty(headers)) {
            for (Header header : headers) {
                method.addHeader(header);
            }
        }
        long start = System.currentTimeMillis();
        int cost = Integer.MIN_VALUE;
        try {
            method.setEntity(entity);
            HttpResponse result = client.execute(method);
            cost = (int) (System.currentTimeMillis() - start);
            int sc = result.getStatusLine().getStatusCode();
            String content = EntityUtils.toString(result.getEntity());
            JSONObject obj = new JSONObject(4);
            obj.put("sc", sc);
            obj.put("content", content);
            MethodCallLogCollector.result(obj.toJSONString(), cost);
            if (sc != HttpStatus.SC_OK) {
                String message = "[POST][" + uri + "]error response status!!! sc=" + sc;
                return CommonResult.error(message, content);
            }
            LOGGER.info("[POST][" + uri + "] end... content=" + content);
            return CommonResult.success(content);
        } catch (Throwable e) {
            if (cost == Integer.MIN_VALUE) {
                cost = (int) (System.currentTimeMillis() - start);
            }
            MethodCallLogCollector.except(e, cost);
            String message = "[POST][" + uri + "]" + e.getMessage();
            LOGGER.error(message, e);
            throw new BusinessException(CommonResult.ERROR, message);
        } finally {
            method.releaseConnection();
            client.close();
        }
    }
}
