package com.example.demo.utils;

import com.example.demo.exception.ErrorCode;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author DrGilbert
 * @date 2021/3/23 20:29
 */
public class AuthorizeUtil {

    @AllArgsConstructor
    @Getter
    public enum Character {
        /**
         * 超级用户
         */
        TYPE_ROOT(0, "root"),
        /**
         * 校级管理员
         */
        TYPE_SCHOOL(1, "school"),
        /**
         * 院级管理员
         */
        TYPE_ACADEMY(2, "academy"),
        /**
         * 普通用户
         */
        TYPE_USER(3, "user");
        private final int code;
        private final String name;
    }


    public static final Character[] CHARACTER_LIST = Character.values();

    public static Integer getCharacterByName(String name) {
        for (Character character : CHARACTER_LIST) {
            if (character.getName().equals(name)) {
                return character.getCode();
            }
        }
        return null;
    }

    /**
     * 判断是否有权利操作
     *
     * @param userRole 用户角色
     * @param role     用户角色
     * @return 是否有权利
     */
    Boolean isAuthorized(@NotNull String userRole, @NotNull String role) {
        if (role.equals(Character.TYPE_USER.name)) {
            return role.equals(userRole);
        } else {
            Integer userLevel = getCharacterByName(userRole);
            Integer level = getCharacterByName(role);
            AssertionUtil.notNull(userLevel, ErrorCode.BIZ_PARAM_ILLEGAL, "此用户类型不存在!");
            AssertionUtil.notNull(level, ErrorCode.BIZ_PARAM_ILLEGAL, "此请求允许的用户类型不存在!");
            return userLevel <= level;
        }
    }
}
