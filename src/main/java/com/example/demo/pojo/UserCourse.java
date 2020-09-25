package com.example.demo.pojo;

import lombok.Data;

/**
 * @author Gilbert
 * @date 2020/9/25 16:28
 */
@Data
public class UserCourse {
    /**
     * 学生id
     */
    private Integer stuId;
    /**
     * 课程id
     */
    private Integer courseId;
    /**
     * 签到次数
     */
    private Integer count;
}
