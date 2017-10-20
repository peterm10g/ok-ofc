package com.lsh.ofc.core.proxy.handler;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.proxy.model.CreateSoReqHead;
import com.lsh.ofc.proxy.model.CreateSoRetHead;
import com.lsh.ofc.proxy.model.MeipiCustomer;
import com.lsh.ofc.proxy.model.ObdHead;
import com.lsh.ofc.core.proxy.service.WumartBasicService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.xml.ws.BindingProvider;
import java.util.Map;

/**
 * Created by huangdong on 16/9/29.
 */
public abstract class WumartSapHandler {

    protected final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    protected WumartBasicService wumartBasicService;

    @Value("${wumart.sap.username}")
    private String username;

    @Value("${wumart.sap.password}")
    private String password;

    public abstract String commitMeipiCustomer(MeipiCustomer model) throws BusinessException;

    public abstract CreateSoRetHead createMeipiSo(CreateSoReqHead order, boolean first) throws BusinessException;

    public abstract ObdHead queryObdStatus(String soCode, Integer regionCode, boolean isReturn) throws BusinessException;

    protected void auth(BindingProvider provider, Integer regionCode) {
        Map<String, Object> context = provider.getRequestContext();
        context.put(BindingProvider.USERNAME_PROPERTY, this.username);
        context.put(BindingProvider.PASSWORD_PROPERTY, this.password);
    }
}
