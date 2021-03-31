package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author DrGilbert
 * @date 2021/3/31 10:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class MailServiceImplTest {
    @Resource
    private MailService mailService;

    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail("2574105038@qq.com", "这是一个主题", "这是邮件正文");
    }

    @Test
    public void sendHtmlMail() {
        mailService.sendHtmlMail("2574105038@qq.com", "subject1", "content1");
    }

    @Test
    public void sendAttachmentsMail() {
    }
}