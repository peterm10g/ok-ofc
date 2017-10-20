package com.lsh.ofc.core.mail;

import com.alibaba.dubbo.common.utils.NetUtils;
import com.lsh.ofc.core.enums.Switch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by panxudong on 17/4/19.
 */
@Service
public class EmailHandler {

    private static final Logger logger = Logger.getLogger(EmailHandler.class);

    private static final LinkedBlockingQueue<Email> EMAIL_QUEUE = new LinkedBlockingQueue<>();

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Autowired
    private EmailTemplate emailTemplate;

    @Value("${email.app}")
    private String app;

    @Value("${email.env}")
    private String env;

    @Value("${email.switch}")
    private String emailSwitchStr;

    @Value("${email.default.interval}")
    private String defaultIntervalStr;

    @Value("${email.default.to}")
    private String defaultToStr;

    private boolean emailSwitch;

    private int defaultInterval;

    private String[] defaultEmailTo;

    private String host;

    private Map<EmailTopic, String[]> emailToMap;

    @PostConstruct
    public void init() {
        Switch sw = Switch.valuesOf(emailSwitchStr);
        emailSwitch = sw == null ? false : sw == Switch.ON ? true : false;

        if(emailSwitch) {
            defaultInterval = defaultIntervalStr == null ? 1800 : Integer.parseInt(defaultIntervalStr);
            defaultEmailTo = defaultToStr == null ? new String[]{} : defaultToStr.split(",");
            host = NetUtils.getLocalHost() + " " + System.getProperty("snowflake.node");
            emailToMap = new HashMap<>();

            for(EmailTopic topic : EmailTopic.values()) {
                emailToMap.put(topic, defaultEmailTo);
            }

            new Timer().schedule(new EmailHandler.SendEmailTask(), defaultInterval * 1000, defaultInterval * 1000);
        }
    }

    public void buildEmail(EmailTopic topic, String content) {
        if(!emailSwitch) {
            return;
        }
        Email email = new Email(topic, content);
        executor.submit(new AsyncAppendToQueueTask(email));
    }

    public void setEmailToForTopic(EmailTopic topic, String[] emailTo) {
        emailToMap.put(topic, emailTo);
    }

    @Getter
    @AllArgsConstructor
    public enum EmailTopic {

        SO_CALLBACK("物美OFC回调SO异常"),
        RO_CALLBACK("物美OFC回调RO异常"),
        OBD("物美OBD发货回传异常"),
        TASK_EXECUTE("OFC任务执行失败超过10次"),
        SO_QUERY("查询创建SO信息异常"),
        RO_QUERY("查询创建RO信息异常"),
        CANCEL_VALIDATE("查询取消订单结果信息异常"),
        DEFAULT("不明异常"),

        SET_WUMART_CONFIG("设置Supplier异常");

        private String topic;

    }

    private class SendEmailTask extends TimerTask {

        @Override
        public void run() {
            String uuid = UUID.randomUUID().toString();
            String prefix = "[SendEmailTask][" + uuid + "]";
            logger.info(prefix + "[start]");

            Map<EmailTopic, List<String>> emailMap = new HashMap<>();

            Email email;
            int count = 0;
            while ((email = EMAIL_QUEUE.poll()) != null && count < 100) {
                List<String> emails;

                EmailTopic topic = email.getTopic();
                String content = email.getContent();

                if (emailMap.containsKey(topic)) {
                    emails = emailMap.get(topic);
                } else {
                    emails = new ArrayList<>();
                    emailMap.put(topic, emails);
                }

                emails.add(content);
                count++;
            }

            String emailPrefix = prefix + "[Send Email]";
            for (Map.Entry<EmailTopic, List<String>> entry : emailMap.entrySet()) {
                EmailTopic emailTopic = entry.getKey();
                List<String> emails = entry.getValue();

                String[] to = emailToMap.get(emailTopic);
                String topic = this.buildTopic(emailTopic);
                String content = this.buildContent(emails);

                logger.info(emailPrefix + "[start][Topic:" + topic + ", To:" + Arrays.toString(to) + "]");
                try {
                    boolean isSuccess = emailTemplate.send(topic, to, content);

                    if(isSuccess) {
                        logger.info(emailPrefix + "[end][Success]");
                    } else {
                        logger.info(emailPrefix + "[end][Fail]");
                    }
                } catch (Exception e) {
                    logger.error(emailPrefix + "[error][Error class:" + e.getClass() + ", Error message:" + e.getMessage() + "]");
                    logger.error(e.getMessage(), e);
                }
            }

            logger.info(prefix + "[end]");
        }

        private String buildContent(List<String> emails) {
            StringBuilder sb = new StringBuilder();

            sb.append(app).append(" ").append(host).append("\n\n");

            for (String email : emails) {
                sb.append(email).append("\n\n");
            }

            return sb.toString();
        }

        private String buildTopic(EmailTopic topic) {
            StringBuilder sb = new StringBuilder();

            sb.append(topic.getTopic()).append("[").append(env).append("]");

            return sb.toString();
        }

    }

    @AllArgsConstructor
    private class AsyncAppendToQueueTask implements Runnable {

        private Email email;

        @Override
        public void run() {
            EMAIL_QUEUE.offer(email);
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private class Email {

        private EmailTopic topic;

        private String content;

    }

}
