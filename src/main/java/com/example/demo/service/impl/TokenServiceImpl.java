package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.TokenDao;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.User;
import com.example.demo.service.AdminService;
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
    @Resource
    private AdminService adminService;
    public static final Integer TYPE_USER = 1;
    public static final Integer TYPE_ADMIN = 2;

    /**
     * 生成token
     *
     * @param id 用户openId
     * @return 生成的token
     */
    @Override
    public String createToken(String id, Integer type) {
        if (userService.isExist(id) != null) {
            String uuid = UUID.randomUUID().toString();
            String exToken = tokenDao.getValue(id);
            tokenDao.deleteValue(id);
            if (exToken != null) {
                tokenDao.deleteValue(exToken);
            }
            JSONObject json = new JSONObject();
            json.put("username", id);
            if (type.equals(TYPE_USER)) {
                json.put("type", "user");
            } else if (type.equals(TYPE_ADMIN)) {
                json.put("type", "admin");
            }
            String newId = json.toJSONString();
            tokenDao.setValue(uuid, newId);
            tokenDao.setValue(newId, uuid);
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
        String jsonString = tokenDao.getValue(token);
        JSONObject json = JSONObject.parseObject(jsonString);
        String username = json.getString("username");
        String type = json.getString("type");
        if (username == null || type == null) {
            return null;
        }
        if ("user".equals(type)) {
            User user = userMapper.getUserByUserName(username);
            return user.getId();
        } else if ("admin".equals(type)) {
            Admin admin = adminService.getAdminByUsername(username);
            return admin.getId();
        }
        return null;
    }

    /**
     * 用户登录校对
     *
     * @param token 用户token
     * @return 是否是最新的token
     */
    @Override
    public Boolean loginCheck(String token) {
        String userId = tokenDao.getValue(token);
        if (userId != null) {
            return tokenDao.getValue(userId).equals(token);
        }
        return false;
    }
}
