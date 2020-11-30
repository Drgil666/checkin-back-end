package com.example.demo.service.impl;

import com.example.demo.dao.TokenDao;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Gilbert
 * @date 2020/11/30 16:16
 */
@Component("TokenServiceImpl")
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
        if (userService.isExist(id)!=null) {
            String uuid = UUID.randomUUID().toString();
            tokenDao.setValue(uuid, id);
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
       String username=tokenDao.getValue(token);
       User user=userMapper.getUserByUserName(username);
       return user.getId();
    }
}
