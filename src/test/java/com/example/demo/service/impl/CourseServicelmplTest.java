package com.example.demo.service.impl;


import com.example.demo.DemoApplication;
import com.example.demo.pojo.Course;
import com.example.demo.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseServicelmplTest {
    @Resource
    private CourseService courseService;

    @Test
    void createCourse() {
        Course course = new Course();
        course.setName("math");
        course.setIntroduction("Math is very interesting!");
        course.setTeacher(0);
        courseService.createCourse(course);
        System.out.println(course.getId());
    }

    @Test
    void updateCourse() {
        Course course = courseService.getCourse(1);
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
        System.out.println(courseService.deleteCourse(1));
    }

    @Test
    void isExist() {
        System.out.println(courseService.isExist("math2"));
    }
}
