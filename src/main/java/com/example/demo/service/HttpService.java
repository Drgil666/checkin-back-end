package com.example.demo.service;

import org.jetbrains.annotations.NotNull;

/**
 * @author Gilbert
 * @date 2021/1/15 14:21
 */
public interface HttpService {
    /**
     * 获取openId
     *
     * @param jsCode 对应参数
     * @return 对应的返回值
     */
    Object getOpenId(@NotNull String jsCode);
}
