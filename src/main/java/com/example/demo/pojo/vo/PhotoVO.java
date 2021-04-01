package com.example.demo.pojo.vo;

import lombok.Data;

/**
 * @author DrGilbert
 * @date 2021/4/1 10:44
 */
@Data
public class PhotoVO {
    /**
     * 录入人脸模式
     */
    public static final String TYPE_USER = "user";
    /**
     * 拍照签到模式
     */
    public static final String TYPE_SIGNIN = "signin";
    public static final Float COMPARE_THRESHOLD = 0.9F;
    private Integer checkInId;
    private String type;
    private String img;
}
