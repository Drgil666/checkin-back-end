package com.example.demo.pojo.vo;

import com.example.demo.DemoApplication;
import com.example.demo.utils.RsaEncrypt;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class RsaEncryptTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void encrypt() throws Exception {
        String test = "123456";
        System.out.println(RsaEncrypt.encrypt(test));
    }

    @Test
    void decrypt() throws Exception {
        String test = "Bdf2Y6lrp0DZ0/DW0FHyCQMUtKR9LP7F1rE3t3ZZIygXAa80zZL0ALelDmpmSMQOC9Jwnss1tLKAa3/DqHzlD6AUtx6z01NnVnrH9Ex7DZNArhEW9+tB8TAmO2MqCuHjMRXZM4pNxPktDf7NycD3yW8ZNVbnihH4+jisfv+whgc=";
        System.out.println(RsaEncrypt.decrypt(test));
    }

    @Test
    void redisTest() {
        stringRedisTemplate.opsForValue().set("user", "testString");
        System.out.println(stringRedisTemplate.opsForValue().get("user"));
    }

    @Test
    void uuidTest() {
        System.out.println(UUID.randomUUID().toString());
    }
}