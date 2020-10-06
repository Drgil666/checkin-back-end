package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.vo.Pair;
import com.example.demo.pojo.vo.RsaEncrypt;
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
    public Pair<Boolean, Integer> login(String username, String password) throws Exception {
        Integer userId = userMapper.isExist(username);
        Pair<Boolean, Integer> result = new Pair<>();
        if (userId != null) {
            if (RsaEncrypt.decrypt(userMapper.getUser(userId).getPassword()).equals(password)) {
                result.setFirst(Boolean.TRUE);
                result.setSecond(userId);
                return result;
            }
        }
        result.setFirst(Boolean.FALSE);
        result.setSecond(null);
        return result;
    }
}
