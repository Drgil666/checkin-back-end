package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.pojo.Friend;
import com.example.demo.pojo.User;
import com.example.demo.service.FriendService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gilbert
 * @date 2020/9/26 13:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class FriendServiceImplTest {
    @Resource
    private FriendService friendService;

    @Test
    void createFriend() {
        System.out.println(friendService.createFriend(1, 3));
    }

    @Test
    void getFriend() {
        Friend friend = friendService.getFriend(1);
        for (int id : friend.getFriends()) {
            System.out.println(id);
        }
    }

    @Test
    void deleteFriend() {
        List<Integer> id = new ArrayList<>();
        id.add(3);
        System.out.println(friendService.deleteFriend(1, id));
    }

    @Test
    void getFriendUserList() {
        List<User> users = friendService.getFriendUserList(friendService.getFriend(1));
        System.out.println(users);
    }
}