package com.example.demo.mapper;

import com.example.demo.pojo.Friend;
import com.example.demo.pojo.User;

import java.util.List;

/**
 * @author Gilbert
 * @date 2020/9/26 12:56
 */
public interface FriendDao {
    /**
     * 新建立朋友关系
     *
     * @param userId 用户id
     * @param friend 朋友id
     * @return 是否创建成功
     */
    boolean createFriend(int userId, int friend);

    /**
     * 获取好友信息列表
     *
     * @param userId 用户id
     * @return Friend信息
     */
    Friend getFriend(int userId);

    /**
     * 删除
     *
     * @param userId  用户id
     * @param friends 要删除的好友列表
     * @return 影响行数
     */
    long deleteFriend(int userId, List<Integer> friends);

    /**
     * 获取好友详细信息
     *
     * @param friend Friend类
     * @return 用户列表
     */
    List<User> getFriendUserList(Friend friend);
}
