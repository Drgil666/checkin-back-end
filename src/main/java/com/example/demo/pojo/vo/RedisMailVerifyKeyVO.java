package com.example.demo.pojo.vo;

import lombok.Data;

/**
 * @author Gilbert
 * @date 2021/4/5 12:04
 */
@Data
public class RedisMailVerifyKeyVO {
    /**
     * 登录方式
     */
    private final String method = "MAIL";
    /**
     * 用户名
     */
    private String username;
}
