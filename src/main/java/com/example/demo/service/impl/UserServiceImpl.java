package com.example.demo.service.impl;

import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.AssertionUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public boolean createUser(@NotNull User user) {
        return userMapper.createUser(user);
    }

    /**
     * 更新用户
     *
     * @param user 要更新的User
     * @return 更新好的User
     */
    @Override
    public long updateUser(@NotNull User user) {
        AssertionUtil.notNull(user.getId(), ErrorCode.BIZ_PARAM_ILLEGAL, "user的id为空!");
        return userMapper.updateUser(user);
    }

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    @Override
    public User getUser(@NotNull Integer id) {
        return userMapper.getUser(id);
    }

    /**
     * 通过学号获取用户信息
     *
     * @param stuNo 学号
     * @return 用户
     */
    @Override
    public User getUserByStuNo(@NotNull String stuNo) {
        return userMapper.getUserByStuNo(stuNo);
    }

    /**
     * 通过邮箱获取用户信息
     *
     * @param mail 邮箱
     * @return 用户
     */
    @Override
    public User getUserByMail(@NotNull String mail) {
        return userMapper.getUserByMail(mail);
    }

    /**
     * 通过姓名获取用户信息
     *
     * @param username 姓名
     * @return 用户
     */
    @Override
    public User getUserByUserName(@NotNull String username) {
        return userMapper.getUserByUserName(username);
    }

    /**
     * 通过用户昵称获取用户名
     *
     * @param nick 用户昵称
     * @return 用户
     */
    @Override
    public List<User> getUserByNick(@NotNull String nick) {
        return userMapper.getUserByNick(nick);
    }

    /**
     * 查询用户名是否存在
     *
     * @param username 用户名
     * @return 对应id
     */
    @Override
    public Integer isExist(@NotNull String username) {
        return userMapper.isExist(username);
    }
}
