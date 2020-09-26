package com.example.demo.service.impl;

import com.example.demo.mapper.FriendDao;
import com.example.demo.pojo.Friend;
import com.example.demo.pojo.User;
import com.example.demo.service.FriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gilbert
 * @date 2020/9/26 13:43
 */
@Service
public class FriendServiceImpl implements FriendService {
    @Resource
    private FriendDao friendDao;

    /**
     * 新建立朋友关系
     *
     * @param userId 用户id
     * @param friend 朋友id
     * @return 是否创建成功
     */
    @Override
    public Friend createFriend(int userId, int friend) {
        return friendDao.createFriend(userId, friend);
    }

    /**
     * 获取好友列表
     *
     * @param userId 用户id
     * @return Friend信息
     */
    @Override
    public Friend getFriend(int userId) {
        return friendDao.getFriend(userId);
    }

    /**
     * 删除
     *
     * @param userId  用户id
     * @param friends 要删除的好友列表
     * @return 影响行数
     */
    @Override
    public long deleteFriend(int userId, List<Integer> friends) {
        return friendDao.deleteFriend(userId, friends);
    }

    /**
     * 获取好友详细信息
     *
     * @param friend Friend类
     * @return 用户列表
     */
    @Override
    public List<User> getFriendUserList(Friend friend) {
        return friendDao.getFriendUserList(friend);
    }
}
