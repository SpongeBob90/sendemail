package com.wyw.sendemail.controller;

import com.wyw.sendemail.service.SendEmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author wyw
 * @date 2018\2\7 0007 11:31
 */
@Controller
public class SendEmailController {

    @Resource
    SendEmailService sendEmailService;

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @PostMapping(value = "/sendSimpleEmail")
    @ResponseBody
    public String sendSimpleEmail(String to, String subject, String content) {
        return sendEmailService.sendSimpleEmail(to, subject, content);
    }

    @PostMapping(value = "/sendHtmlEmail")
    @ResponseBody
    public String sendHtmlEmail(String to, String subject, String content) {
        return sendEmailService.sendHtmlEmail(to, subject, content);
    }

    @PostMapping(value = "/sendAttachmentsEmail")
    @ResponseBody
    public String sendAttachmentsEmail(String to, String subject, String content) {
        return sendEmailService.sendAttachmentsEmail(to, subject, content);
    }

}
