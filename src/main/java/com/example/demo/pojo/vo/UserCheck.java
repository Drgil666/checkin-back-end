package com.example.demo.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author Gilbert
 * @date 2020/10/1 21:29
 * 用户本地记住密码用的信息
 */
@Data
public class UserCheck {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 记住密码的时间
     */
    private Date logTime;
}
