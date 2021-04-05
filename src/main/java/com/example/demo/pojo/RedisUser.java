package com.example.demo.pojo;

import lombok.Data;

/**
 * @author Gilbert
 * @date 2021/4/5 10:35
 */
@Data
public class RedisUser {
    /**
     * 用户登录类型
     */
    private String type;
    /**
     * 用户名
     */
    private String username;
    public static final Integer TYPE_USER = 1;
    public static final Integer TYPE_ADMIN = 2;
    public static final String ATTRIBUTE_USER = "user";
    public static final String ATTRIBUTE_ADMIN = "admin";
    public static final String ATTRIBUTE_TYPE = "type";
    public static final String ATTRIBUTE_USERNAME = "username";
    public static final String ATTRIBUTE_PASSWORD = "password";
}
