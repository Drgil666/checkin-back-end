package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
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
     * 通过学号获取用户信息
     *
     * @param stuNo 学号
     * @return 用户
     */
    @Override
    public User getUserByStuNo(String stuNo) {
        return userMapper.getUserByStuNo(stuNo);
    }

    /**
     * 通过邮箱获取用户信息
     *
     * @param mail 邮箱
     * @return 用户
     */
    @Override
    public User getUserByMail(String mail) {
        return userMapper.getUserByMail(mail);
    }

    /**
     * 通过姓名获取用户信息
     *
     * @param username 姓名
     * @return 用户
     */
    @Override
    public User getUserByUserName(String username) {
        return userMapper.getUserByUserName(username);
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
