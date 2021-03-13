package com.example.demo.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author chentao
 */
@Data
public class Sign {
    private Integer id;
    /**
     * 签到人id
     */
    private Integer stuId;
    /**
     * 签到时间
     */
    private Date signTime;
    /**
     * 签到时照片id
     */
    private String photoId;
    /**
     * 对应签到id
     */
    private Integer checkId;
    /**
     * 签到人名字
     */
    private String nick;
    /**
     * 签到人学号
     */
    private String stuNo;
}
