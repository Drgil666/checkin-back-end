package com.example.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 */
@Data
@ApiModel(description = "管理员实体类")
public class Admin {
    @ApiModelProperty(value = "管理员id", name = "id", required = true)
    private Integer id;
    /**
     * 管理员用户名
     */
    @ApiModelProperty(value = "管理员用户名", name = "username", required = true)
    private String username;
    /**
     * 管理员密码
     */
    @ApiModelProperty(value = "管理员密码", name = "password", required = true)
    private String password;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", name = "nick", required = true)
    private String nick;

}
