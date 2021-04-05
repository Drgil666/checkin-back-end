package com.example.demo.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author Gilbert
 * @date 2021/4/5 10:37
 */
@Data
public class RedisMailVerifyValueVO {
    /**
     * 验证码
     */
    private String verificationCode;
    /**
     * 日期
     */
    private Date date;
    /**
     * 有效市场（单位毫秒）
     */
    public static final Long VALIDITY = 5 * 60 * 1000L;
}
