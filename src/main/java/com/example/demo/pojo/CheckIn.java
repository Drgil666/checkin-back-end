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
     * 课程id
     */
    private Integer courseId;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;

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

    public static final CheckInIdentity[] CHECK_IN_IDENTITIES_LIST = new CheckInIdentity[]{
            CheckInIdentity.BEGIN, CheckInIdentity.END
    };
    /**
     * 签到类型
     */
    private CheckInIdentity status;

    public void setStatus(int index) {
        this.status = CHECK_IN_IDENTITIES_LIST[index];
    }
}