package com.example.demo.pojo;

import lombok.Data;

/**
 * @author Gilbert
 * @date 2020/9/23 21:50
 */
@Data
public class User {
    private Integer id;
    /**
     * 账户名
     */
    private String username;
    /**
     * 学号
     */
    private String stuNo;
    /**
     * 邮箱
     */
    private String mail;
    /**
     * 照片对应的MongoId
     */
    private String photoId;
    /**
     * 昵称
     */
    private String nick;
    /**
     * 学校
     */
    private Integer school;
    /**
     * 学院
     */
    private Integer academy;
    /**
     * 专业
     */
    private Integer major;
}
