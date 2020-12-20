package com.example.demo.service;

/**
 * @author Gilbert
 * @date 2020/11/30 16:11
 */
public interface TokenService {
    /**
     * 生成token
     *
     * @param id 用户openId
     * @param type 用户类型
     * @return 生成的token
     */
    String createToken(String id,Integer type);

    /**
     * 通过Token获取用户id
     *
     * @param id Token
     * @return 用户id
     */
    Integer getUserIdByToken(String id);

    /**
     * 用户登录校对
     *
     * @param token 用户token
     * @return 是否是最新的token
     */
    Boolean loginCheck(String token);
}
