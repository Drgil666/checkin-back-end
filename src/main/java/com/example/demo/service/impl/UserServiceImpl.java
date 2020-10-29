package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Gilbert
 * @date 2020/9/24 15:42
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 创建用户
     *
     * @param user 要注册的用户
     * @return 带有id的用户
     */
    @Override
    public boolean createUser(User user) {
        return userMapper.createUser(user);
    }

    /**
     * 更新用户
     *
     * @param user 要更新的User
     * @return 更新好的User
     */
    @Override
    public long updateUser(User user) {
        return userMapper.updateUser(user);
    }

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    @Override
    public User getUser(Integer id) {
        return userMapper.getUser(id);
    }

    /**
     * 查询用户名是否存在
     *
     * @param username 用户名
     * @return 对应id
     */
    @Override
    public Integer isExist(String username) {
        return userMapper.isExist(username);
    }
}
