package com.example.demo.service.impl;


import com.example.demo.DemoApplication;
import com.example.demo.pojo.Course;
import com.example.demo.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseServiceImplTest {
    @Resource
    private CourseService courseService;

    @Test
    void createCourse() {
        Course course = new Course();
        course.setName("math");
        course.setIntroduction("Math is very interesting!");
        course.setTeacher(6);
        courseService.createCourse(course);
        System.out.println(course.getId());
    }

    @Test
    void updateCourse() {
        Course course = courseService.getCourse(1);
        course.setName("math");
        System.out.println(courseService.updateCourse(course));
        System.out.println(course.getName());
    }

    @Test
    void getCourse() {
        Course course = courseService.getCourse(1);
        System.out.println(course.getName());
    }

    @Test
    void getCourseListByName() {
        List<Course> courses = courseService.getCourseListByName("math");
        System.out.println(courses);
    }

    @Test
    void deleteCourse() {
        List<Integer> id = new ArrayList<>();
        id.add(1);
        id.add(4);
        System.out.println(courseService.deleteCourse(id));
    }

    @Test
    void getCourseListById() {
        Integer id=5;
        List<Course> courseList=courseService.getCourseListById(id);
        for(Course course:courseList)
        {
            System.out.println(course.getId());
        }
    }
}
