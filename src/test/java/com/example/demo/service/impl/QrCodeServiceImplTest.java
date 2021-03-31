package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.pojo.User;
import com.example.demo.service.QrCodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class QrCodeServiceImplTest {
    @Resource
    private QrCodeService qrCodeService;

    @Test
    public void createImage() {
        String vo = "sss";
        User user = new User();
        user.setSchool(0);
        user.setAcademy(0);
        user.setMajor(0);
        user.setMail("123456@qq.com");
        user.setNick("nick1");
        user.setPhotoId("111");
        user.setStuNo("120");
        user.setUsername("112233");
        System.out.println(qrCodeService.createQr(vo));
    }

    @Test
    public void writeToBase64() {
    }

    @Test
    public void createQr() {
    }
}