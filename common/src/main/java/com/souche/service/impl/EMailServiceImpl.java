package com.souche.service.impl;

import com.souche.service.EMailService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


/**
 * @author guishangquan
 * @Description
 * @Package com.souche.service.impl
 * @date 17/3/28
 **/
@Slf4j
public class EMailServiceImpl implements EMailService {

    private final transient Properties props = System.getProperties();

    private static final String MAIL_TYPE = "text/html;charset=utf-8";

    /**
     * 邮箱session
     */
    private transient Session session;

    @Setter
    private String host;

    @Setter
    private String userName;

    @Setter
    private String password;


    @Override
    public void init() {
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }

        });
    }


    @Override
    public void send(List<String> recipients, List<String> cc, String subject, Object content,
                     List<File> attachmentList) {
        int i = 0;
        while (i++ < 3) {
            try {
                // 创建邮件
                final MimeMessage message = new MimeMessage(session);

                // 设置发件人地址
                message.setFrom(new InternetAddress(userName, "优惠券自动发送"));

                // 设置收件人地址（多个邮件地址）
                if (recipients != null && recipients.size() > 0) {
                    final int num = recipients.size();
                    InternetAddress[] addresses = new InternetAddress[num];
                    for (int j = 0; j < num; j++) {
                        addresses[j] = new InternetAddress(recipients.get(j));
                    }
                    message.setRecipients(MimeMessage.RecipientType.TO, addresses);
                }

                // 设置抄送人地址（多个邮件地址）
                if (cc != null && cc.size() > 0) {
                    final int num = cc.size();
                    InternetAddress[] addresses = new InternetAddress[num];
                    for (int j = 0; j < num; j++) {
                        addresses[j] = new InternetAddress(cc.get(j));
                    }
                    message.setRecipients(MimeMessage.RecipientType.CC, addresses);
                }

                // 设置邮件主题
                message.setSubject(subject);

                // 设置发送时间
                message.setSentDate(new Date());

                // 设置发送内容
                Multipart multipart = new MimeMultipart();
                MimeBodyPart contentPart = new MimeBodyPart();
                contentPart.setContent(content.toString(), MAIL_TYPE);
                multipart.addBodyPart(contentPart);

                // 设置附件
                if (attachmentList != null && attachmentList.size() > 0) {
                    for (File attachment : attachmentList) {
                        MimeBodyPart attachmentPart = new MimeBodyPart();
                        FileDataSource source = new FileDataSource(attachment);
                        attachmentPart.setDataHandler(new DataHandler(source));
                        attachmentPart
                                .setFileName(MimeUtility.encodeWord(attachment.getName(), "gb2312", null));
                        multipart.addBodyPart(attachmentPart);
                    }
                }

                message.setContent(multipart);
                Transport.send(message);

                log.info("email send success,content:{}", content);
                return;
            } catch (Exception e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                if (i == 3) {
                    log.error("email send error,content:{}", content, e);
                }
            }
        }
    }


    @Override
    public void send(String recipient, String subject, Object content) {
        List<String> recipients = new ArrayList<>();
        recipients.add(recipient);
        send(recipients, null, subject, content, null);
    }


    @Override
    public void send(List<String> recipients, String subject, Object content, List<File> attachmentList) {
        send(recipients, null, subject, content, attachmentList);
    }


    @Override
    public void send(List<String> recipients, List<String> cc, String subject, Object content) {
        send(recipients, cc, subject, content, null);
    }


    @Override
    public void send(List<String> recipients, String subject, Object content) {
        send(recipients, null, subject, content, null);
    }
}
