package com.example.demo.pojo;

import lombok.Data;

/**
 * @author chentao
 */
@Data
public class Admin {
    private Integer id;
    /**
     * 管理员用户名
     */
    private String username;
    /**
     * 管理员密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nick;

}
