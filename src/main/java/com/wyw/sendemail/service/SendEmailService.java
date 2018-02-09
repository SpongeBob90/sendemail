package com.wyw.sendemail.service;

/**
 * @author wyw
 * @date 2018\2\8 0008 9:54
 */
public interface SendEmailService {

    String sendSimpleEmail(String to, String subject, String content);

    String sendHtmlEmail(String to, String subject, String content);

    String sendAttachmentsEmail(String to, String subject, String content);

}
