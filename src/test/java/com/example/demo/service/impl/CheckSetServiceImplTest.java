package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.pojo.CheckSet;
import com.example.demo.service.CheckSetService;
import org.junit.jupiter.api.Test;
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
    void createCheckSet() {
        CheckSet checkSet = new CheckSet();
        checkSet.setNick("阿里云测试");
        checkSet.setUserId(1);
        checkSet.setVisible(1);
        checkSetService.createCheckSet(checkSet);
        System.out.println(checkSet.getId());
    }

    @Test
    void updateCheckSet() {
        CheckSet checkSet = checkSetService.getCheckSet(1);
        checkSet.setNick("更新测试");
        System.out.println(checkSetService.updateCheckSet(checkSet));
    }

    @Test
    void getCheckSet() {
        int i = 2;
        CheckSet checkSet = checkSetService.getCheckSet(i);
        System.out.println(checkSet);
    }

    @Test
    void getCheckSetList() {
        List<CheckSet> setList = checkSetService.getCheckSetList(1);
        System.out.println(setList);
    }

    @Test
    void getCheckSetNick() {
        List<CheckSet> setList = checkSetService.getCheckSetNick("web课签到");
        System.out.println(setList);
    }

    @Test
    void deleteCheckSet() {
        List<Integer> id = new ArrayList<>();
        id.add(3);
        id.add(4);
        System.out.println(checkSetService.deleteCheckSet(id));
    }
}
