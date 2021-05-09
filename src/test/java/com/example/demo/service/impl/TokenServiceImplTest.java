package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.service.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static com.example.demo.service.impl.TokenServiceImpl.TYPE_ADMIN;

/**
 * @author DrGilbert
 * @date 2021/3/23 19:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TokenServiceImplTest {
    @Resource
    private TokenService tokenService;

    @Test
    public void createToken() {
        System.out.println(tokenService.createUserToken("123456", TYPE_ADMIN));
    }

    @Test
    public void getUserIdByToken() {
        System.out.println(tokenService.getUserIdByToken("89cfe691-a83f-4258-8e0a-736fecedd6ab"));
    }

    @Test
    public void loginCheck() {
        System.out.println(tokenService.loginCheck("c34cd9c9-ea25-4871-a76c-ae839a20af60"));
    }

    @Test
    public void getLoginType() {
        System.out.println(tokenService.getLoginType("b4cedf0c-1729-47c6-9bae-979393baf0c4"));
    }

    @Test
    public void createMailToken() {

    }

    @Test
    public void checkMailToken() {

    }
}