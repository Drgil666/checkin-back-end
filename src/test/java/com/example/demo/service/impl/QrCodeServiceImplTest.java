package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.pojo.User;
import com.example.demo.service.QrCodeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class QrCodeServiceImplTest {
    @Resource
    private QrCodeService qrCodeService;

    @Test
    void createImage() {
        HashMap<String, Object> map = new HashMap<>();
        User user = new User();
        user.setSchool(0);
        user.setAcademy(0);
        user.setMajor(0);
        user.setMail("123456@qq.com");
        user.setAvatar("test1");
        user.setFriendId("test2");
        user.setStatus(0);
        user.setNick("nick1");
        user.setPhotoId("111");
        user.setStuNo("120");
        user.setUsername("112233");
        map.put("test1", "111");
        map.put("test2", "222");
        map.put("test3", user);
        System.out.println(qrCodeService.createQr(map));
    }

    @Test
    void writeToBase64() {
    }

    @Test
    void createQr() {
    }
}