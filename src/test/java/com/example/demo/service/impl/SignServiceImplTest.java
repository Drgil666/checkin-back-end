package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.pojo.Sign;
import com.example.demo.service.SignService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author chentao
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class SignServiceImplTest {
    @Resource
    private SignService signService;

    @Test
    void createSign(){
        Sign sign =new Sign();
        sign.setStuId(1);
        sign.setSignTime(new Date());
        sign.setPhotoId("1");
        sign.setCheckId(1);
        signService.createSign(sign);
        System.out.println(sign.getId());
    }

    @Test
    void updateSign(){
        Sign sign=signService.getSign(1);
        sign.setPhotoId("222");
        signService.updateSign(sign);
    }
    @Test
    void getSign()
    {
        Sign sign=signService.getSign(1);
        System.out.println(sign.getId());
    }
}
