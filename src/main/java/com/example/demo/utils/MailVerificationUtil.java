package com.example.demo.utils;

import org.jetbrains.annotations.NotNull;

/**
 * @author Gilbert
 * @date 2021/4/5 11:20
 */
public class MailVerificationUtil {

    public static String getRandomVerificationCode(@NotNull Integer length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(Math.round(Math.random() * 9));
        }
        return result.toString();
    }
}
