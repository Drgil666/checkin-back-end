package com.example.demo.service;

import com.example.demo.pojo.Course;

import java.util.List;

public interface CourseService {
    /**
     * 创建课程
     *
     * @param course 要创建的课程
     * @return 是否创建成功
     */
    Course createCourse(Course course);

    /**
     * 更新课程
     *
     * @param course 要更新的课程
     * @return 更新好的课程
     */
    Course updateCourse(Course course);

    /**
     * 通过name获取课程列表
     *
     * @param name 查找的课程名
     * @return 课程列表
     */
    List<Course> getCourseListByName(String name);

    /**
     * 获取课程信息
     *
     * @param id 课程id
     * @return 课程
     */
    Course getCourse(Integer id);
}
