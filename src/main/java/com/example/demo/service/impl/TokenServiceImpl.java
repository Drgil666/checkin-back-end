package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.TokenDao;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.User;
import com.example.demo.service.AdminService;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import com.example.demo.utils.AssertionUtil;
import com.sun.istack.internal.NotNull;
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
    public static final String ATTRIBUTE_USER = "user";
    public static final String ATTRIBUTE_ADMIN = "admin";
    public static final String ATTRIBUTE_TYPE = "type";
    public static final String ATTRIBUTE_USERNAME = "username";
    public static final String ATTRIBUTE_PASSWORD = "password";

    /**
     * 生成token
     *
     * @param id 用户openId
     * @return 生成的token
     */
    @Override
    public String createToken(@NotNull String id, @NotNull Integer type) {
        AssertionUtil.notNull(userService.isExist(id), ErrorCode.BIZ_PARAM_ILLEGAL, "用户不存在");
        String uuid = UUID.randomUUID().toString();
        JSONObject json = new JSONObject();
        json.put(ATTRIBUTE_USERNAME, id);
        if (type.equals(TYPE_USER)) {
            json.put(ATTRIBUTE_TYPE, ATTRIBUTE_USER);
        } else if (type.equals(TYPE_ADMIN)) {
            json.put(ATTRIBUTE_TYPE, ATTRIBUTE_ADMIN);
        }
        String newId = json.toJSONString();
        String exToken = tokenDao.getValue(newId);
        if (exToken != null) {
            tokenDao.deleteValue(exToken);
        }
        tokenDao.deleteValue(newId);
        tokenDao.setValue(uuid, newId);
        tokenDao.setValue(newId, uuid);
        return uuid;
    }

    /**
     * 通过Token获取用户id
     *
     * @param token Token
     * @return 用户id
     */
    @Override
    public Integer getUserIdByToken(@NotNull String token) {
        String jsonString = tokenDao.getValue(token);
        JSONObject json = JSONObject.parseObject(jsonString);
        String username = json.getString(ATTRIBUTE_USERNAME);
        String type = json.getString(ATTRIBUTE_TYPE);
        AssertionUtil.isTrue((username == null || type == null), ErrorCode.BIZ_PARAM_ILLEGAL, "Token不存在!");
        if (ATTRIBUTE_USER.equals(type)) {
            User user = userMapper.getUserByUserName(username);
            return user.getId();
        } else if (ATTRIBUTE_ADMIN.equals(type)) {
            Admin admin = adminService.getAdminByUsername(username);
            return admin.getId();
        } else {
            throw new ErrorException(ErrorCode.BIZ_PARAM_ILLEGAL, "登录类型错误!");
        }
    }

    /**
     * 用户登录校对
     *
     * @param token 用户token
     * @return 是否是最新的token
     */
    @Override
    public Boolean loginCheck(@NotNull String token) {
        String userId = tokenDao.getValue(token);
        if (userId != null) {
            return tokenDao.getValue(userId).equals(token);
        }
        return false;
    }
}
