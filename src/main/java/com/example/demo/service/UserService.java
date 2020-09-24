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
    int updateUser(User user);

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    User getUser(Integer id);
}
