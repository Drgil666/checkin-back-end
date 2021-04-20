package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Gilbert
 * @date 2021/4/5 11:20
 */
public class MailVerificationUtil {
    @Value("${mail.code.length}")
    public static Integer codeLength;

    public static String getRandomVerificationCode() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            result.append(Math.round(Math.random() * 9));
        }
        return result.toString();
    }
}
