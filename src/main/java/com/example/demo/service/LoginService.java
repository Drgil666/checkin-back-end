package com.example.demo.service;

/**
 * @author Gilbert
 * @date 2020/10/1 21:02
 */
public interface LoginService {
    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 是否成功
     */
    boolean login(String username, String password);
}
