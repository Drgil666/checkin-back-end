package com.example.demo.pojo.vo;

import lombok.Data;

/**
 * @author Gilbert
 * @date 2021/4/5 10:35
 */
@Data
public class RedisUserValueVO {
    /**
     * 用户登录类型
     */
    private String type;
    /**
     * 用户名
     */
    private String username;
}
