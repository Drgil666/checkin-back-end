package com.example.demo.service.impl;


import com.example.demo.mapper.CourseMapper;
import com.example.demo.pojo.Course;
import com.example.demo.service.CourseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;

    /**
     * 创建课程
     *
     * @param course 要创建的课程
     * @return 课程
     */
    @Override
    public Course createCourse(Course course) {
        return courseMapper.createCourse(course);
    }

    /**
     * 更新课程
     *
     * @param course 要更新的课程
     * @return 课程
     */
    @Override
    public long updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    /**
     * 通过name获取课程列表
     *
     * @param name 查找的课程名
     * @return 课程列表
     */
    @Override
    public List<Course> getCourseListByName(String name) {
        return courseMapper.getCourseByName(name);
    }

    /**
     * 通过id查找课程
     *
     * @param id 课程id
     * @return 课程
     */
    @Override
    public Course getCourse(Integer id) {
        return courseMapper.getCourseById(id);
    }

    /**
     * 删除课程
     *
     * @param id 删除的课程
     * @return 是否删除成功
     */
    @Override
    public Course deleteCourse(Integer id) {
        return courseMapper.deleteCourse(id);
    }

    /**
     * 查询课程名是否存在
     *
     * @param name 用户名
     * @return 对应id
     */
    @Override
    public Integer isExist(@Param("name") String name) {
        return courseMapper.isExist(name);
    }
}
