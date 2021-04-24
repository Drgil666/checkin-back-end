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
}
