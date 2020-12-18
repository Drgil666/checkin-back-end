package com.example.demo.service;

import com.example.demo.pojo.User;

/**
 * @author Gilbert
 * @date 2020/9/24 15:41
 */
public interface UserService {
    /**
     * 创建用户
     *
     * @param user 要注册的用户
     * @return 是否创建成功
     */
    boolean createUser(User user);

    /**
     * 更新用户
     *
     * @param user 要更新的User
     * @return 更新好的User
     */
    long updateUser(User user);

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    User getUser(Integer id);

    /**
     * 通过学号获取用户信息
     *
     * @param stuNo 学号
     * @return 用户
     */
    User getUserByStuNo(String stuNo);

    /**
     * 通过邮箱获取用户信息
     *
     * @param mail 邮箱
     * @return 用户
     */
    User getUserByMail(String mail);

    /**
     * 通过姓名获取用户信息
     *
     * @param username 姓名
     * @return 用户
     */
    User getUserByUserName(String username);

    /**
     * 查询用户名是否存在
     *
     * @param username 用户名
     * @return 对应id
     */
    Integer isExist(String username);
}
