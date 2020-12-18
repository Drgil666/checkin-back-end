package com.example.demo.service.impl;

import com.example.demo.dao.TokenDao;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Gilbert
 * @date 2020/11/30 16:16
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Resource
    private UserService userService;
    @Resource
    private TokenDao tokenDao;
    @Resource
    private UserMapper userMapper;

    /**
     * 生成token
     *
     * @param id 用户openId
     * @return 生成的token
     */
    @Override
    public String createToken(String id) {
        if (userService.isExist(id) != null) {
            String uuid = UUID.randomUUID().toString();
            String exToken = tokenDao.getValue(id);
            tokenDao.deleteValue(id);
            if (exToken != null) {
                tokenDao.deleteValue(exToken);
            }
            tokenDao.setValue(uuid, id);
            tokenDao.setValue(id, uuid);
            return uuid;
        } else {
            return null;
        }
    }

    /**
     * 通过Token获取用户id
     *
     * @param token Token
     * @return 用户id
     */
    @Override
    public Integer getUserIdByToken(String token) {
        String username = tokenDao.getValue(token);
        if (username == null) {
            return null;
        }
        User user = userMapper.getUserByUserName(username);
        return user.getId();
    }

    /**
     * 用户登录校对
     *
     * @param token 用户token
     * @return 是否是最新的token
     */
    @Override
    public boolean loginCheck(String token) {
        String userId = tokenDao.getValue(token);
        if (userId != null) {
            return tokenDao.getValue(userId).equals(token);
        }
        return false;
    }
}
