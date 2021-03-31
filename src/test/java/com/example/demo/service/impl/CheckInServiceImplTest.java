package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.pojo.CheckIn;
import com.example.demo.service.CheckInService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author chentao
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CheckInServiceImplTest {
    @Resource
    private CheckInService checkInService;

    @Test
    public void createCheckIn() {
        CheckIn checkin = new CheckIn();
        checkin.setStartTime(new Date());
        checkin.setEndTime(new Date());
        checkin.setType(0);
        checkin.setStatus(0);
        checkin.setSetId(1);
        checkInService.createCheckIn(checkin);
        System.out.println(checkin.getId());
    }

    @Test
    public void updateCheckIn() {
        CheckIn checkin = checkInService.getCheckIn(1);
        checkin.setSetId(2);
        System.out.println(checkInService.updateCheckIn(checkin));
    }

    @Test
    public void getCheckIn() {
        int id = 1;
        System.out.println(checkInService.getCheckIn(id));
    }

    @Test
    public void getCheckInListBySetId() {
        List<CheckIn> checkInList = checkInService.getCheckInListBySetId(1);
        System.out.println(checkInList);
    }

    @Test
    public void deleteCheckIn() {
        List<Integer> id = new ArrayList<>();
        id.add(36);
        id.add(40);
        System.out.println(checkInService.deleteCheckIn(id));
    }
}
