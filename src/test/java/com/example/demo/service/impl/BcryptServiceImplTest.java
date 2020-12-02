package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.service.BcryptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Gilbert
 * @date 2020/12/2 8:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class BcryptServiceImplTest {
    @Resource
    private BcryptService bcryptService;

    @Test
    public void encode() {
        String password = "123456";
        System.out.println(bcryptService.encode(password));
    }

    @Test
    public void checkPassword() {
        System.out.println(bcryptService.checkPassword("123456", "$2a$11$G6OLXT9reM2wXZN00IDyYukMFFwd9pHHHuHbyBk3znNxlmEbEqEwu"));
    }
}