package com.example.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gilbert
 * @date 2020/9/23 21:50
 */
@Data
@ApiModel(description = "学生信息实体类")
public class User {
    @ApiModelProperty(value = "用户id", name = "id", required = true)
    private Integer id;
    /**
     * 账户名
     */
    @ApiModelProperty(value = "账户名", name = "username", required = true)
    private String username;
    /**
     * 学号
     */
    @ApiModelProperty(value = "学号", name = "stuNo", required = true)
    private String stuNo;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", name = "mail", required = true)
    private String mail;
    /**
     * 照片对应的MongoId
     */
    @ApiModelProperty(value = "照片对应的MongoId", name = "photoId", required = true)
    private String photoId;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", name = "nick", required = true)
    private String nick;
    /**
     * 学校
     */
    @ApiModelProperty(value = "学校", name = "school", required = true)
    private Integer school;
    /**
     * 学院
     */
    @ApiModelProperty(value = "学院", name = "academy", required = true)
    private Integer academy;
    /**
     * 专业
     */
    @ApiModelProperty(value = "专业", name = "major", required = true)
    private Integer major;
    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像", name = "avatarUrl", required = true)
    private String avatarUrl;
}
