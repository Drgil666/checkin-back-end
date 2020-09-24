package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Gilbert
 * @date 2020/9/24 16:33
 */
@Data
@AllArgsConstructor
public class Response<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> Response<T> createSuc(T o) {
        return new Response<T>(0, null, o);
    }

    public static <T> Response<T> createErr(String msg) {
        return new Response<T>(-1, msg, null);
    }

    public static <T> Response<T> createErr(int code, String msg) {
        return new Response<T>(code, msg, null);
    }
}
