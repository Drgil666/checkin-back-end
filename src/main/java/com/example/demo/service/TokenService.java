package com.example.demo.service;

import org.jetbrains.annotations.NotNull;

/**
 * @author Gilbert
 * @date 2020/11/30 16:11
 */
public interface TokenService {
    /**
     * 生成token
     *
     * @param id   用户openId
     * @param type 用户类型
     * @return 生成的token
     */
    String createUserToken(@NotNull String id, @NotNull Integer type);

    /**
     * 通过Token获取用户id
     *
     * @param id Token
     * @return 用户id
     */
    Integer getUserIdByToken(@NotNull String id);

    /**
     * 用户登录校对
     *
     * @param token 用户token
     * @return 是否是最新的token
     */
    Boolean loginCheck(@NotNull String token);

    /**
     * 获取Token对应的登录类型
     *
     * @param token 用户token
     * @return 用户的登录类型
     */
    String getLoginType(@NotNull String token);

    /**
     * 生成用户的验证码
     *
     * @param adminId 管理员id
     * @return 验证码
     */
    String createVerificationCode(@NotNull Integer adminId);

    /**
     * 校验管理员的验证码
     *
     * @param adminId          管理员id
     * @param verificationCode 验证码
     * @return 是否一致
     */
    Boolean checkVerificationCode(@NotNull Integer adminId, @NotNull String verificationCode);
}
