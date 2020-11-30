package com.example.demo.dao;

/**
 * @author Gilbert
 * @date 2020/11/30 16:05
 */
public interface TokenDao {
    /**
     * 为redis设置键值对
     * @param key 键
     * @param value 值
     */
    void setValue(String key,String value);

    /**
     * 获取键对应的值
     * @param key 键
     * @return 对应值
     */
    String getValue(String key);
}
