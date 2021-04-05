package com.example.demo.pojo.vo;

import lombok.Data;

/**
 * @author Gilbert
 * @date 2021/4/5 12:04
 */
@Data
public class RedisUserKeyVO {
    /**
     * 登录方式
     */
    private final String method = "LOGIN";
    /**
     * 用户的uuid
     */
    private String uuid;
}
