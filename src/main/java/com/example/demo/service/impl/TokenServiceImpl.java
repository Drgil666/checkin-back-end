package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.TokenDao;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.User;
import com.example.demo.pojo.vo.*;
import com.example.demo.service.AdminService;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import com.example.demo.utils.AssertionUtil;
import com.example.demo.utils.MailVerificationUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
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
    public static final String LOGIN = "login";
    public static final String MAIL = "mail";
    public static final String KAPTCHA = "kaptcha";
    public static final Integer TYPE_USER = 1;
    public static final Integer TYPE_ADMIN = 2;
    public static final String ATTRIBUTE_USER = "user";
    public static final String ATTRIBUTE_ADMIN = "admin";
    public static final String ATTRIBUTE_TYPE = "type";
    public static final String ATTRIBUTE_USERNAME = "username";
    public static final String ATTRIBUTE_PASSWORD = "password";
    @Value("${mail.validity}")
    private Long validity;
    @Value("${mail.code.length}")
    public Integer codeLength;

    /**
     * 生成token
     *
     * @param username 用户openId
     * @return 生成的token
     */
    @Override
    public String createUserToken(@NotNull String username, @NotNull Integer type) {
        AssertionUtil.notNull(userService.isExist(username), ErrorCode.BIZ_PARAM_ILLEGAL, "用户不存在");
        String uuid = UUID.randomUUID().toString();
        RedisUserValueVO redisUserValueVO = new RedisUserValueVO();
        redisUserValueVO.setUsername(username);
        if (type.equals(TYPE_USER)) {
            redisUserValueVO.setType(ATTRIBUTE_USER);
        } else if (type.equals(TYPE_ADMIN)) {
            redisUserValueVO.setType(ATTRIBUTE_ADMIN);
        } else {
            throw new ErrorException(ErrorCode.BIZ_PARAM_ILLEGAL, "登录类型错误!");
        }
        RedisUserKeyVO redisUserKeyVO = new RedisUserKeyVO();
        redisUserKeyVO.setUuid(uuid);
        String keyString = JSON.toJSONString(redisUserKeyVO);
        String valueString = JSON.toJSONString(redisUserValueVO);
        String exKeyString = tokenDao.getValue(valueString);
        if (exKeyString != null) {
            tokenDao.deleteValue(valueString);
            tokenDao.deleteValue(exKeyString);
        }
        tokenDao.deleteValue(keyString);
        tokenDao.setValue(keyString, valueString);
        tokenDao.setValue(valueString, keyString);
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
        RedisUserValueVO valueVO = getRedisUserValue(token);
        AssertionUtil.isTrue(!(valueVO.getUsername() == null || valueVO.getType() == null),
                ErrorCode.BIZ_PARAM_ILLEGAL, "Token不存在!");
        if (ATTRIBUTE_USER.equals(valueVO.getType())) {
            User user = userMapper.getUserByUserName(valueVO.getUsername());
            return user.getId();
        } else if (ATTRIBUTE_ADMIN.equals(valueVO.getType())) {
            Admin admin = adminService.getAdminByUsername(valueVO.getUsername());
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
        RedisUserKeyVO keyVO = new RedisUserKeyVO();
        keyVO.setUuid(token);
        String keyJson = JSON.toJSONString(keyVO);
        String valueJson = tokenDao.getValue(keyJson);
        AssertionUtil.notNull(valueJson, ErrorCode.TOKEN_AUTHORIZE_ILLEGAL, "Token已失效或不存在!");
        JSONObject jsonObject = JSONObject.parseObject(valueJson);
        AssertionUtil.notNull(jsonObject, ErrorCode.TOKEN_AUTHORIZE_ILLEGAL, "Token已失效或不存在!");
        return tokenDao.getValue(valueJson).equals(keyJson);
    }

    /**
     * 获取Token对应的登录类型
     *
     * @param token 用户token
     * @return 用户的登录类型
     */
    @Override
    public String getLoginType(@NotNull String token) {
        RedisUserValueVO redisUserValueVO = getRedisUserValue(token);
        return redisUserValueVO.getType();
    }

    /**
     * 生成用户的验证码
     *
     * @param adminId 管理员id
     * @return 验证码
     */
    @Override
    public String createVerificationCode(@NotNull Integer adminId) {
        String verificationCode = MailVerificationUtil.getRandomVerificationCode(codeLength);
        Admin admin = adminService.getAdminById(adminId);
        RedisMailVerifyValueVO valueVO = new RedisMailVerifyValueVO();
        valueVO.setDate(new Date());
        valueVO.setVerificationCode(verificationCode);
        String valueJson = JSON.toJSONString(valueVO);
        RedisMailVerifyKeyVO keyVO = new RedisMailVerifyKeyVO();
        keyVO.setUsername(admin.getUsername());
        String keyJson = JSON.toJSONString(keyVO);
        tokenDao.setValue(keyJson, valueJson);
        return verificationCode;
    }

    /**
     * 校验管理员的验证码
     *
     * @param mailUserVO       用户Token信息
     * @param verificationCode 验证码
     * @return 是否一致
     */
    @Override
    public Boolean checkVerificationCode(@NotNull MailUserVO mailUserVO, @NotNull String verificationCode) {
        Admin admin = adminService.getAdminById(mailUserVO.getAdminId());
        RedisMailVerifyKeyVO keyVO = new RedisMailVerifyKeyVO();
        keyVO.setUsername(admin.getUsername());
        String keyJson = JSON.toJSONString(keyVO);
        String valueJson = tokenDao.getValue(keyJson);
        AssertionUtil.notNull(valueJson, ErrorCode.BIZ_PARAM_ILLEGAL, "验证码不存在!");
        RedisMailVerifyValueVO valueVO = JSONObject.toJavaObject(JSONObject.parseObject(valueJson),
                RedisMailVerifyValueVO.class);
        if (System.currentTimeMillis() - valueVO.getDate().getTime() >= validity * 60 * 1000) {
            throw new ErrorException(ErrorCode.INNER_PARAM_ILLEGAL, "验证码已过期!");
        }
        if (valueVO.getVerificationCode().equals(verificationCode)) {
            tokenDao.deleteValue(keyJson);
            return true;
        } else {
            return false;
        }
    }

    RedisUserValueVO getRedisUserValue(@NotNull String token) {
        RedisUserKeyVO keyVO = new RedisUserKeyVO();
        keyVO.setUuid(token);
        String keyJson = JSON.toJSONString(keyVO);
        String valueJson = tokenDao.getValue(keyJson);
        AssertionUtil.notNull(valueJson, ErrorCode.BIZ_PARAM_ILLEGAL, "Token无效!");
        return JSONObject.toJavaObject(JSONObject.parseObject(valueJson), RedisUserValueVO.class);
    }
}
