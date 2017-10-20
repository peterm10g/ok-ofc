package com.lsh.ofc.core.mail;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 邮件模板
 * Created by huangdong on 16/9/20.
 */
public class EmailTemplate {

    private final Logger logger = Logger.getLogger(this.getClass());

    private static final String EMAIL_NAME = "panxudong@lsh123.com";

    @Autowired
    private MailSender mailSender;

    public boolean send(String subject, String[] to, String content) throws Exception {
        Assert.notEmpty(to);
        Assert.hasText(subject);
        Assert.hasText(content);

        this.logger.info("邮件发送开始... 标题：" + subject + "，内容：" + content);
        if (!StringUtils.hasText(content)) {
            return false;
        }
        SimpleMailMessage mail = new SimpleMailMessage();
        try {
            mail.setFrom(EMAIL_NAME);//发送者
            mail.setTo(to);//接受者
            mail.setSubject(subject);//主题
            mail.setText(content);//邮件内容
            this.mailSender.send(mail);
            logger.info("邮件发送完成... 标题：" + subject);
            return true;
        } catch (Exception e) {
            logger.error("邮件发送开始... 标题：" + subject, e);
            throw e;
        }
    }

}
