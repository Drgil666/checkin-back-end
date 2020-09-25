package com.example.demo.pojo;

import lombok.Data;

/**
 * @author Gilbert
 * @date 2020/9/25 16:22
 */
@Data
public class Course {
    /**
     * 课程id
     */
    private Integer id;
    /**
     * 课程名
     */
    private String name;
    /**
     * 课程介绍
     */
    private String introduction;
    /**
     * 课程老师
     */
    private Integer teacher;
}
