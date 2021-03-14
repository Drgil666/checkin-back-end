package com.example.demo.dao;

/**
 * @author Gilbert
 * @date 2020/12/2 8:31
 */
public interface BcryptDao {
    /**
     * 密码加密
     *
     * @param password 要加密的密码
     * @return 加密好的密码
     */
    String encode(String password);

    /**
     * 比对密码是否正确
     *
     * @param password 明文
     * @param hash     密文
     * @return 是否匹配
     */
    Boolean checkPassword(String password, String hash);
}
