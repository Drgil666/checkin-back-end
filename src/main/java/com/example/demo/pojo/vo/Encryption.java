package com.example.demo.pojo.vo;

/**
 * @author Gilbert
 * @date 2020/10/1 21:22
 * 加密数据用
 */
public class Encryption {
    /**
     * 对密码进行RSA加密
     *
     * @param password 密码
     * @return 加密后的密码
     */

    public static String encodePassword(String password) {
        return password;
        //TODO:加密算法待补充
    }

    public static String decodePassword(String password) {
        return password;
        //TODO:解密算法待补充
    }
}
