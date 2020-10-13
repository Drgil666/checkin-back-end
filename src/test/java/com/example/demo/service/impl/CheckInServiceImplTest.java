package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.pojo.CheckIn;
import com.example.demo.pojo.UserCourse;
import com.example.demo.service.CheckInService;
import com.example.demo.service.UserCourseService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CheckInServiceImplTest {
    @Resource
    private CheckInService checkInService;

    @Test
    void createCheckIn() {
        CheckIn checkin = new CheckIn();
        checkin.setUserId(5);
        checkin.setCourseId(1);
        checkin.setStartTime(new Date());
        checkin.setEndTime(new Date());
        checkin.setStatus(0);
        checkInService.createCheckIn(checkin);
        System.out.println(checkin.getId());
    }

    @Test
    void updateCheckIn() {
       CheckIn checkin = checkInService.getCheckIn(1);
        System.out.println(checkInService.updateCheckIn(checkin));
        System.out.println(checkin.getCourseId());
    }



}
