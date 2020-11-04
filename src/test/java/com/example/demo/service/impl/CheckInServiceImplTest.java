package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.pojo.CheckIn;
import com.example.demo.service.CheckInService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;


/**
 * @author chentao
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CheckInServiceImplTest {
    @Resource
    private CheckInService checkInService;

    @Test
    void createCheckIn() {
        CheckIn checkin = new CheckIn();
        checkin.setUserId(1);
        checkin.setStartTime(new Date());
        checkin.setEndTime(new Date());
        checkin.setType(0);
        checkin.setStatus(0);
        checkin.setNick("test");
        checkInService.createCheckIn(checkin);
        System.out.println(checkin.getId());
    }

    @Test
    void updateCheckIn() {
        CheckIn checkin = checkInService.getCheckIn(1);
        System.out.println(checkInService.updateCheckIn(checkin));
    }
    @Test
    void getCheckInList(){
        ArrayList<CheckIn> checkInList = new ArrayList<CheckIn>();
        checkInList=checkInService.getCheckInList(1);
        System.out.println(checkInList);
    }
    @Test
    void getCheckInNick(){
        ArrayList<CheckIn> checkInList = new ArrayList<CheckIn>();
                checkInList=checkInService.getCheckInNick("test");
        System.out.println(checkInList);
    }
    @Test
    void deleteCheckIn(){
        checkInService.deleteCheckIn(5);
    }
}
