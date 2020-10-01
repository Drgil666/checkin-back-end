package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.vo.Encryption;
import com.example.demo.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Gilbert
 * @date 2020/10/1 21:04
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserMapper userMapper;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 是否成功
     */
    @Override
    public boolean login(String username, String password) {
        Integer userId = userMapper.isExist(username);
        if (userId != null) {
            return userMapper.getUser(userId).getPassword().equals(Encryption.encodePassword(password));
        }
        return false;
    }
}
