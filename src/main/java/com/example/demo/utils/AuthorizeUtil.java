package com.example.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

/**
 * @author DrGilbert
 * @date 2021/3/23 20:29
 */
public class AuthorizeUtil {
    public static final String ROOT_ATTRIBUTE = "root";
    public static final String SCHOOL_ATTRIBUTE = "school";
    public static final String ACADEMY_ATTRIBUTE = "academy";
    public static final String MAJOR_ATTRIBUTE = "major";
    public static final String NORMAL_ATTRIBUTE = "normal";
    public static final String USER_ATTRIBUTE = "user";

    @AllArgsConstructor
    @Getter
    public enum Character {
        /**
         * 超级用户
         */
        TYPE_ROOT(0, ROOT_ATTRIBUTE),
        /**
         * 校级管理员
         */
        TYPE_SCHOOL(1, SCHOOL_ATTRIBUTE),
        /**
         * 院级管理员
         */
        TYPE_ACADEMY(2, ACADEMY_ATTRIBUTE),
        /**
         * 普通管理员
         */
        TYPE_MAJOR(3, MAJOR_ATTRIBUTE),
        /**
         * 普通管理员
         */
        TYPE_NORMAL(4, NORMAL_ATTRIBUTE),
        /**
         * 普通用户
         */
        TYPE_USER(5, USER_ATTRIBUTE);
        private final Integer code;
        private final String name;
    }

    public static final Character[] CHARACTER_LIST = Character.values();
    public static final HashMap<String, Integer> CHARACTER_MAP = getCharacterMap();

    public static HashMap<String, Integer> getCharacterMap() {
        HashMap<String, Integer> hashMap = new HashMap<>(10);
        for (Character character : CHARACTER_LIST) {
            hashMap.put(character.name, character.code);
        }
        return hashMap;
    }

    public static Integer getCharacterByName(String name) {
        if (CHARACTER_MAP.containsKey(name)) {
            return CHARACTER_MAP.get(name);
        }
        return null;
    }

}
