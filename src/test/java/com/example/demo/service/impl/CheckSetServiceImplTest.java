package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.pojo.CheckSet;
import com.example.demo.service.CheckSetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chentao
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CheckSetServiceImplTest {
    @Resource
    private CheckSetService checkSetService;

    @Test
    public void createCheckSet() {
        CheckSet checkSet = new CheckSet();
        checkSet.setNick("阿里云测试");
        checkSet.setUserId(1);
        checkSet.setVisible(1);
        checkSetService.createCheckSet(checkSet);
        System.out.println(checkSet.getId());
    }

    @Test
    public void updateCheckSet() {
        CheckSet checkSet = checkSetService.getCheckSet(1);
        checkSet.setNick("更新测试");
        System.out.println(checkSetService.updateCheckSet(checkSet));
    }

    @Test
    public void getCheckSet() {
        CheckSet checkSet = checkSetService.getCheckSet(2);
        System.out.println(checkSet);
    }

    @Test
    public void getCheckSetList() {
        List<CheckSet> setList = checkSetService.getCheckSetListByTeacher(1, "");
        System.out.println(setList);
    }


    @Test
    public void deleteCheckSet() {
        List<Integer> id = new ArrayList<>();
        id.add(3);
        id.add(4);
        System.out.println(checkSetService.deleteCheckSet(id));
    }

    @Test
    public void getCheckByStu() {
        List<CheckSet> checkSetList = checkSetService.getCheckListByStu(1);
        System.out.println(checkSetList);
    }
}
