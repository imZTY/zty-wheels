package com.zty.framework.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.zty.framework.request.SmtpMailRequest;
import com.zty.framework.response.SmtpMailResponse;
import com.zty.framework.service.SmtpMailService;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 基于smtp协议发送邮件的接口定义.
 */
@Service
public class SmtpMailServiceImpl implements SmtpMailService {

    private final static Logger logger = LogManager.getLogger(SmtpMailServiceImpl.class);

    // TODO: 2021/7/7 从配置里读取这些值
    private static final String myEmailSMTPHost = "smtp.exmail.qq.com";
    private static final String smtpPort = "465";
    private static final Map<String, String> MAIL_ACCOUNT = new HashMap<>();
    static {
        MAIL_ACCOUNT.put("tianyi.zeng@swiftpass.cn", "");
    }

    /**
     * 发送电子邮件.
     */
    @Override
    public SmtpMailResponse sendEMail(SmtpMailRequest request) {
        try {
            // 参数检查
            parameterCheck(request);
            String mailAccount = request.getFromMail();
            String mailPassword = MAIL_ACCOUNT.get(mailAccount);
            // 获取配置
            Properties props = getSmtpProperties();
            // 创建会话
            Session session = Session.getDefaultInstance(props) ;
            session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
            // 获取传输对象
            Transport transport = session.getTransport();
            // 创建邮件
            MimeMessage message = createMimeMessage(session, request);
            // 建立连接并发送邮件
            transport.connect(mailAccount, mailPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return new SmtpMailResponse(true);
        } catch (NoSuchProviderException e) {
            logger.error("获取Transport失败, ", e);
            return new SmtpMailResponse(false, "获取Transport失败, "+e.getMessage());
        } catch (UnsupportedEncodingException e) {
            logger.error("生成邮件内容异常, ", e);
            return new SmtpMailResponse(false, "生成邮件内容异常, "+e.getMessage());
        } catch (MessagingException e) {
            logger.error("邮件发送失败, ", e);
            return new SmtpMailResponse(false, "邮件发送失败, "+e.getMessage());
        }
    }

    private void parameterCheck(SmtpMailRequest request) {
        if (StringUtils.isBlank(request.getFromMail())) {
            throw new IllegalArgumentException("未输入寄件人邮箱");
        }
        if (CollectionUtils.isEmpty(request.getToMails())) {
            throw new IllegalArgumentException("未输入收件人邮箱");
        }
        if (StringUtils.isBlank(request.getSubject())) {
            throw new IllegalArgumentException("未输入邮件主题");
        }
        if (StringUtils.isBlank(request.getContent())) {
            throw new IllegalArgumentException("未输入邮件正文");
        }
        if (!MAIL_ACCOUNT.containsKey(request.getFromMail())) {
            // 如果没有该寄件人的邮箱账号，则添加
            if (StringUtils.isBlank(request.getFromMailPassword())) {
                throw new IllegalArgumentException(String.format("没有此邮箱%s的密码", request.getFromMail()));
            }
            MAIL_ACCOUNT.put(request.getFromMail(), request.getFromMailPassword());
        }
    }

    private MimeMessage createMimeMessage(Session session, SmtpMailRequest request) throws UnsupportedEncodingException, MessagingException {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(request.getFromMail(), "", "UTF-8"));
        logger.info("发件人: {}", request.getFromMail());

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        if (!CollectionUtils.isEmpty(request.getToMails())) {
            message.setRecipients(MimeMessage.RecipientType.TO, String.join(",", request.getToMails()));
            logger.info("收件人: {}", request.getToMails());
        }
        if (!CollectionUtils.isEmpty(request.getCcMails())) {
            message.setRecipients(MimeMessage.RecipientType.CC, String.join(",", request.getCcMails()));
            logger.info("抄送人: {}", request.getCcMails());
        }
        if (!CollectionUtils.isEmpty(request.getBccMails())) {
            message.setRecipients(MimeMessage.RecipientType.BCC, String.join(",", request.getBccMails()));
            logger.info("密抄人: {}", request.getBccMails());
        }

        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject(request.getSubject(), "UTF-8");
        logger.info("邮件标题: {}", request.getSubject());

        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        message.setContent(request.getContent(), "text/html;charset=UTF-8");
        logger.info("邮件正文: {}", request.getContent());

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

    private Properties getSmtpProperties() {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "true");
        props.setProperty("mail.smtp.ssl.enable", "true");
        return props;
    }
}
