package com.souche.service;

import java.io.File;
import java.util.List;


/**
 * @author guishangquan
 * @Description
 * @Package com.souche.service
 * @date 17/3/28
 **/
public interface EMailService {

    /**
     * 初始化
     */
    public void init();


    /**
     * 群发邮件
     *
     * @param recipients
     *            收件人邮箱地址
     * @param cc
     *            抄送人邮箱地址
     * @param subject
     *            主题
     * @param content
     *            内容
     * @param attachmentList
     *            附件列表
     */
    void send(List<String> recipients, List<String> cc, String subject, Object content,
              List<File> attachmentList);


    /**
     * 简单发送邮件
     */
    void send(String recipient, String subject, Object content);


    /**
     * 简单发送邮件
     */
    void send(List<String> recipients, String subject, Object content, List<File> attachmentList);


    /**
     * 简单发送邮件
     */
    void send(List<String> recipients, List<String> cc, String subject, Object content);


    /**
     * 简单发送邮件
     */
    void send(List<String> recipients, String subject, Object content);

}
