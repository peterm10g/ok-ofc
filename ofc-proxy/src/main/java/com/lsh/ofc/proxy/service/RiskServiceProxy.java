package com.lsh.ofc.proxy.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.proxy.util.HttpClientUtils;
import org.apache.http.Header;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Risk服务代理
 * Created by huangdong on 16/8/28.
 */
@Service
public class RiskServiceProxy {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Value("${risk.server.path}")
    private String riskServerPath;

    private static final String REPORT_URI = "/api/rsm/java/v1/risk/report/ofc";

    private Header[] buildHeaders() {
        Header[] headers = {new BasicHeader("api-version", "1.1"), new BasicHeader("platform", "ofc"), new BasicHeader("random", "1")};
        return headers;
    }

    public boolean report(String content) throws BusinessException {
        String uri = this.riskServerPath + REPORT_URI;
        logger.info("[POST][URI=" + uri + "]" + content);
        StringEntity entity = new StringEntity(content, ContentType.APPLICATION_JSON);//解决中文乱码问题
        String json = HttpClientUtils.post(uri, entity, this.buildHeaders()).getData();
        if (json == null) {
            return false;
        }
        logger.info("[POST][URI=" + uri + "]" + content);
        JSONObject obj = JSON.parseObject(json);
        if (obj == null) {
            return false;
        }
        JSONObject head = obj.getJSONObject("head");
        if (head == null) {
            return false;
        }
        return Integer.valueOf(1).equals(head.getInteger("status"));
    }
}
