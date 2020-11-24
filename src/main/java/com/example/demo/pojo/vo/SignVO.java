package com.example.demo.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author yutao
 * sign工具类，存含学生名和签到情况的签到信息
 */
@Data
public class SignVO {
    /**
     * 签到人名字
     */
    private String nick;
    /**
     * 签到时照片id
     */
    private Date signTime;
}
