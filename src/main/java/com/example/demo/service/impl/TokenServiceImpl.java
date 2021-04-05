package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.TokenDao;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.RedisUser;
import com.example.demo.pojo.RedisVerify;
import com.example.demo.pojo.User;
import com.example.demo.service.AdminService;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import com.example.demo.utils.AssertionUtil;
import com.example.demo.utils.VerificationUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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

    /**
     * 生成token
     *
     * @param id 用户openId
     * @return 生成的token
     */
    @Override
    public String createUserToken(@NotNull String id, @NotNull Integer type) {
        AssertionUtil.notNull(userService.isExist(id), ErrorCode.BIZ_PARAM_ILLEGAL, "用户不存在");
        String uuid = UUID.randomUUID().toString();
        RedisUser redisUserVO = new RedisUser();
        redisUserVO.setUsername(id);
        if (type.equals(RedisUser.TYPE_USER)) {
            redisUserVO.setType(RedisUser.ATTRIBUTE_USER);
        } else if (type.equals(RedisUser.TYPE_ADMIN)) {
            redisUserVO.setType(RedisUser.ATTRIBUTE_ADMIN);
        } else {
            throw new ErrorException(ErrorCode.BIZ_PARAM_ILLEGAL, "登录类型错误!");
        }
        String newId = JSON.toJSONString(redisUserVO);
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
        RedisUser redisUserVO = JSONObject.toJavaObject(json, RedisUser.class);
        AssertionUtil.isTrue(!(redisUserVO.getUsername() == null || redisUserVO.getType() == null),
                ErrorCode.BIZ_PARAM_ILLEGAL, "Token不存在!");
        if (RedisUser.ATTRIBUTE_USER.equals(redisUserVO.getType())) {
            User user = userMapper.getUserByUserName(redisUserVO.getUsername());
            return user.getId();
        } else if (RedisUser.ATTRIBUTE_ADMIN.equals(redisUserVO.getType())) {
            Admin admin = adminService.getAdminByUsername(redisUserVO.getUsername());
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

    /**
     * 获取Token对应的登录类型
     *
     * @param token 用户token
     * @return 用户的登录类型
     */
    @Override
    public String getLoginType(@NotNull String token) {
        JSONObject jsonObject = JSON.parseObject(tokenDao.getValue(token));
        return jsonObject.getString(RedisUser.ATTRIBUTE_TYPE);
    }

    /**
     * 生成用户的验证码
     *
     * @param token 用户Token
     * @return 验证码
     */
    @Override
    public String createMailToken(@NotNull String token) {
        AssertionUtil.isTrue(loginCheck(token) &&
                        getLoginType(token).equals(RedisUser.ATTRIBUTE_ADMIN),
                ErrorCode.UNKNOWN_ERROR, "您没有权限!请重新登录!");
        String verificationCode = VerificationUtil.getRandomVerificationCode(6);
        Integer adminId = getUserIdByToken(token);
        Admin admin = adminService.getAdmin(adminId);
        RedisVerify redisVerify = new RedisVerify();
        redisVerify.setDate(new Date());
        redisVerify.setVerificationCode(verificationCode);
        String exVerificationCode = tokenDao.getValue(admin.getUsername());
        if (exVerificationCode != null) {
            tokenDao.deleteValue(admin.getUsername());
        }
        tokenDao.setValue(admin.getUsername(), JSON.toJSONString(redisVerify));
        return verificationCode;
    }

    /**
     * 校验管理员的验证码
     *
     * @param token            用户Token
     * @param verificationCode 验证码
     * @return 是否一致
     */
    @Override
    public Boolean checkMailToken(@NotNull String token, @NotNull String verificationCode) {
        AssertionUtil.isTrue(loginCheck(token) &&
                        getLoginType(token).equals(RedisUser.ATTRIBUTE_ADMIN),
                ErrorCode.UNKNOWN_ERROR, "您没有权限!请重新登录!");
        String jsonString = tokenDao.getValue(verificationCode);
        AssertionUtil.notNull(jsonString, ErrorCode.BIZ_PARAM_ILLEGAL, "验证码不存在!");
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        AssertionUtil.notNull(jsonObject, ErrorCode.BIZ_PARAM_ILLEGAL, "验证码不存在!");
        RedisVerify redisVerify = JSONObject.toJavaObject(jsonObject, RedisVerify.class);
        if (System.currentTimeMillis() - redisVerify.getDate().getTime() >= RedisVerify.VALIDITY) {
            throw new ErrorException(ErrorCode.INNER_PARAM_ILLEGAL, "验证码已超时!");
        }
        Integer adminId = getUserIdByToken(token);
        Admin admin = adminService.getAdmin(adminId);
        if (redisVerify.getVerificationCode().equals(verificationCode)) {
            tokenDao.deleteValue(admin.getUsername());
            return true;
        } else {
            return false;
        }
    }
}
