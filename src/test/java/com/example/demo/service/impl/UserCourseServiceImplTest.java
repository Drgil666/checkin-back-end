package com.example.demo.service.impl;


import com.example.demo.DemoApplication;
import com.example.demo.mapper.UserCourseMapper;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserCourse;
import com.example.demo.service.UserCourseService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class UserCourseServiceImplTest {
    @Resource
    private UserCourseService usercourseService;

    @Test
    void createUserCourse() {
        UserCourse usercourse = new UserCourse();
       usercourse.setStuId(5);
        usercourse.setCourseId(2);
        usercourse.setCount(0);
        usercourseService.createUserCourse(usercourse);
        System.out.println(usercourse.getStuId());
    }

    @Test
    void updateUserCourse() {
        UserCourse usercourse = usercourseService.getUserCourse(5);
        System.out.println(usercourseService.updateUsercourse(usercourse));
        System.out.println(usercourse.getStuId());
    }
}

