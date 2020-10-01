package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.service.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gilbert
 * @date 2020/10/1 21:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class LoginServiceImplTest {
    @Resource
    private LoginService loginService;

    @Test
    void login() {
        System.out.println(loginService.login("11", "22"));
    }
}