package com.wyw.sendemail.service.impl;

import com.wyw.sendemail.service.SendEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author wyw
 * @date 2018\2\8 0008 9:54
 */
@Service
public class SendEmailServiceImpl implements SendEmailService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private TemplateEngine templateEngine;

    @Value("${mail.fromMail.addr}")
    private String from;

    @Override
    public String sendSimpleEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            logger.info("发送简单邮件成功！");
            return "send simple mail success";
        } catch (Exception e) {
            logger.error("发送简单邮件异常！", e);
            return "send simple mail fail";
        }
    }

    @Override
    public String sendHtmlEmail(String to, String subject, String content) {
        String rscId = "picture";
        String rscPath = "F:\\2017BONC\\exercise\\2.gif";
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //使用模板创建邮件正文
            Context context = new Context();
            context.setVariable("rscId", rscId);
            context.setVariable("content", content);
            String emailContent = templateEngine.process("emailTemplate", context);
            //设置邮件参数
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(emailContent, true);
            //添加正文中的静态资源（必须在setText之后添加，否则资源不会被关联）
            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);
            //发送邮件
            mailSender.send(message);
            logger.info("发送html邮件成功！");
            return "send html mail success";
        } catch (MessagingException e) {
            logger.error("发送html邮件失败！", e);
            return "send html mail fail";
        }
    }

    @Override
    public String sendAttachmentsEmail(String to, String subject, String content) {
        String filePath = "F:\\2017BONC\\exercise\\3.gif";
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //设置邮件参数
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            //添加附件
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            //发送邮件
            mailSender.send(message);
            logger.info("发送带附件的邮件成功！");
            return "send attachment mail success";
        } catch (MessagingException e) {
            logger.error("发送带附件的邮件失败！", e);
            return "send attachment mail fail";
        }
    }

}
