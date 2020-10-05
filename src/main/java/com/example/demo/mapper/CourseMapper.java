package com.example.demo.mapper;

import com.example.demo.pojo.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {
    /**
     * 创建课程
     *
     * @param course 要创建的课程
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into course (name,introduction,teacher) values  (#{course.name},#{course.introduction},#{course.teacher})")
    Course createCourse(@Param("course") Course course);

    /**
     * 更新课程
     *
     * @param course 要更新的Course
     * @return 更新后的Course
     */
    @Update("update course set name=#{course.name},introduction=#{course.introduction},teacher=#{course.teacher} where id=#{course.id}")
    Course updateCourse(@Param("Course") Course course);

    /**
     * 通过id获取课程信息
     *
     * @param id 课程id
     * @return 课程
     */
    @Select("select * from course where id=#{id}")
    Course getCourseById(@Param("id") Integer id);

    /**
     * 通过name获取课程列表
     *
     * @param name 课程name
     * @return 课程列表
     */
    List<Course> getCourseByName(String name);
}
