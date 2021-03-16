package com.example.demo.pojo.vo;

import com.sun.istack.internal.NotNull;
import lombok.Data;

/**
 * @author Gilbert
 * @date 2021/3/16 10:46
 */
@Data
public class LoginVO {
    @NotNull
    private String username;
    private String password;
}
