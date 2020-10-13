package com.example.demo.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author Gilbert
 * @Date 2020/9/25 16:32
 */
@Data
public class CheckIn {
    /**
     * 签到id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 课程id
     */
    private Integer courseId;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 照片
     */
    private Integer status;

}