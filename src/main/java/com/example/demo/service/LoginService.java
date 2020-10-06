package com.example.demo.service;


import com.example.demo.pojo.vo.Pair;

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
    Pair<Boolean, Integer> login(String username, String password) throws Exception;
}
