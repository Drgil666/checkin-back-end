package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.service.HttpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Gilbert
 * @date 2021/1/15 14:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class HttpServiceImplTest {
    @Resource
    private HttpService httpService;

    @Test
    public void getOpenId() {
        System.out.println(httpService.getOpenId("083H5Q0w36UWFV2CGW2w3arTj20H5Q02"));
    }
}