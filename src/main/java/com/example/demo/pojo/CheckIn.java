package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

/**
 * @author Gilbert
 * @Date 2020/9/25 16:32
 */
@Data
public class CheckIn {
    /**
     * 签到id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 签到状态 未开始/进行中/已结束
     */
    private Integer status;
    /**
     * 签到名
     */
    private String nick;

    /**
     * 是否可见
     */
    private int visible;

    @AllArgsConstructor
    @Getter
    public enum CheckInIdentity {
        /**
         * 签到
         */
        BEGIN(0, "begin"),
        /**
         * 签退
         */
        END(1, "end");
        private final Integer code;
        private final String name;
    }

    /**
     * 签到环节 签到/签退
     */

    private CheckInIdentity type;
    public static final CheckInIdentity[] CHECK_IN_IDENTITIES_LIST = new CheckInIdentity[]{
            CheckInIdentity.BEGIN, CheckInIdentity.END
    };


    public void setType(int index) {
        this.type = CHECK_IN_IDENTITIES_LIST[index];
    }
}