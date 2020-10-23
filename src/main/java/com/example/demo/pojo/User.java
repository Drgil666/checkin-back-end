package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

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
     * 身份的枚举类，必要时可以扩充
     * Getter是自动生成对应的Get方法
     * AllArgsConstructor表示构造成函数
     */
    @AllArgsConstructor
    @Getter
    public enum UserIdentity {
        /**
         * 管理员
         */
        ADMIN(0, "admin"),
        /**
         * 教师
         */
        TEACHER(1, "teacher"),
        /**
         * 学生
         */
        STUDENT(2, "student");
        private final Integer code;
        private final String name;
    }

    public static final UserIdentity[] USER_IDENTITY_LIST = new UserIdentity[]{
            UserIdentity.ADMIN, UserIdentity.TEACHER, UserIdentity.STUDENT
    };
    private UserIdentity status;
    /**
     * 照片对应的MongoId
     */
    private String photoId;
    /**
     * 好友对应的MongoID
     */
    private String friendId;
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
    //TODO:学校、学院、专业的类型有待商榷

    public void setStatus(Integer index) {
        this.status = USER_IDENTITY_LIST[index];
    }
}
