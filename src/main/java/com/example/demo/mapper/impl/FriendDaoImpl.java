package com.example.demo.mapper.impl;

import com.example.demo.mapper.FriendDao;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Friend;
import com.example.demo.pojo.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gilbert
 * @date 2020/9/26 13:24
 */
@Component("FriendDaoImpl")
public class FriendDaoImpl implements FriendDao {
    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private UserMapper userMapper;

    /**
     * 新建立朋友关系
     *
     * @param userId 用户id
     * @param friend 朋友id
     * @return 是否创建成功
     */
    @Override
    public Friend createFriend(int userId, int friend) {
        Friend friend1 = getFriend(userId);
        if (friend1 == null) {
            friend1 = new Friend();
            friend1.setUserId(userId);
            List<Integer> list = new ArrayList<>();
            list.add(friend);
            friend1.setFriends(list);
            mongoTemplate.insert(friend1);
            return friend1;
        }
        if (friend1.getFriends().contains(friend)) {
            return null;
        }
        friend1.getFriends().add(friend);
        mongoTemplate.save(friend1);
        return friend1;
    }

    /**
     * 获取好友列表
     *
     * @param userId 用户id
     * @return Friend信息
     */
    @Override
    public Friend getFriend(int userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, Friend.class);
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
        Friend friend = getFriend(userId);
        long count = 0;
        for (Integer id : friends) {
            if (friend.getFriends().contains(id)) {
                friend.getFriends().remove(id);
                count++;
            }
        }
        mongoTemplate.save(friend);
        return count;
    }

    /**
     * 获取好友详细信息
     *
     * @param friend Friend类
     * @return 用户列表
     */
    @Override
    public List<User> getFriendUserList(Friend friend) {
        List<User> users = new ArrayList<>();
        for (int id : friend.getFriends()) {
            users.add(userMapper.getUser(id));
        }
        return users;
    }
}
