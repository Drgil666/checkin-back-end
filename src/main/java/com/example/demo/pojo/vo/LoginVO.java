package com.example.demo.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gilbert
 * @date 2021/3/16 10:46
 */
@Data
@ApiModel(description = "CUDRequest")
public class LoginVO {
    @ApiModelProperty(value = "用户名", name = "username", required = true)
    private String username;
    @ApiModelProperty(value = "密码", name = "password", required = true)
    private String password;
}
