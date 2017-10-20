package com.lsh.ofc.core.kafka;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.exception.EC;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Properties;

/**
 * Created by huangdong on 16/8/28.
 */
public class KafkaTemplate implements InitializingBean, DisposableBean {

    private final Logger logger = Logger.getLogger(KafkaTemplate.class);

    private Properties props;

    private KafkaProducer producer;

    public KafkaTemplate(Properties props) {
        this.props = props;
    }

    public synchronized boolean send(String topic, String content) throws BusinessException {
        logger.info("[send][" + topic + "][start]msg:" + content);
        try {
            this.producer.send(new ProducerRecord<String, String>(topic, content)).get();
            logger.info("[send][" + topic + "][end]msg:" + content);
            return true;
        } catch (Exception e) {
            logger.error("[send][" + topic + "][error]msg:" + content + "\n" + e.getMessage(), e);
            throw new BusinessException(EC.ERROR.getCode(), "[send][" + topic + "][error]msg:" + content + "\n" + e.getMessage(), e);
        }
    }

    @Override
    synchronized public void afterPropertiesSet() throws Exception {
        this.producer = new KafkaProducer(props);
    }

    @Override
    synchronized public void destroy() throws Exception {
        if (this.producer != null) {
            this.producer.close();
        }
    }
}
