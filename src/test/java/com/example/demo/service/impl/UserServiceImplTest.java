package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Gilbert
 * @date 2020/9/24 15:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class UserServiceImplTest {
    @Resource
    private UserService userService;

    @Test
    void createUser() {
        User user = new User();
        user.setSchool(0);
        user.setAcademy(0);
        user.setMajor(0);
        user.setMail("123456@qq.com");
        user.setNick("nick1");
        user.setPhotoId("111");
        user.setStuNo("120");
        user.setUsername("oz-Eg5WRyK549JCWx8Ar8Z8pCbH8");
        userService.createUser(user);
        System.out.println(user.getId());
    }

    @Test
    void updateUser() {
        User user = userService.getUser(1);
        System.out.println(userService.updateUser(user));
        System.out.println(user.getMail());
    }

    @Test
    void getUser() {
        User user = userService.getUser(1);
        System.out.println(user.getMail());
    }

    @Test
    void isExist() {
        System.out.println(userService.isExist("15613"));
    }
}