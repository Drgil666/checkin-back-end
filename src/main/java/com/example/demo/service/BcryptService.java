package com.example.demo.service;

import org.jetbrains.annotations.NotNull;

/**
 * @author Gilbert
 * @date 2020/12/2 8:36
 */
public interface BcryptService {
    /**
     * 密码加密
     *
     * @param password 要加密的密码
     * @return 加密好的密码
     */
    String encode(@NotNull String password);

    /**
     * 比对密码是否正确
     *
     * @param password 明文
     * @param hash     密文
     * @return 是否匹配
     */
    Boolean checkPassword(@NotNull String password, @NotNull String hash);
}
