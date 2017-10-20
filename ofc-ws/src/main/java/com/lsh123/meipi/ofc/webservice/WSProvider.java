package com.lsh123.meipi.ofc.webservice;

import com.lsh.ofc.core.mail.EmailHandler;
import com.lsh.ofc.core.service.OfcObdService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.xml.ws.Endpoint;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * WS提供者
 * Created by huangdong on 16/8/28.
 */
public class WSProvider implements InitializingBean {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Value("${provider.ws.port}")
    private int port;

    @Autowired
    private OfcObdService service;

    @Autowired
    private EmailHandler emailHandler;

    private AtomicBoolean publish = new AtomicBoolean(false);

    @Override
    public void afterPropertiesSet() throws Exception {
        if (publish.compareAndSet(false, true)) {
            OFCWebService implementor = new OFCWebService();
            implementor.service = service;
            implementor.emailHandler = emailHandler;
            String address = "http://0.0.0.0:" + port + "/webservice/wsdl/wumart/so/push";
            Endpoint.publish(address, implementor);
            logger.info("ws start... " + address);
        }
    }
}
