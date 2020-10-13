package com.example.demo.pojo.vo;

import org.junit.jupiter.api.Test;

class RsaEncryptTest {

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
}