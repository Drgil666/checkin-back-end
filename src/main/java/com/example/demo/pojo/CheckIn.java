package com.example.demo.pojo;

import lombok.Data;

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
     * 是否可见
     */
    private int visible;

    /**
     * 签到环节 签到/签退
     */

    private Integer type;
    /**
     * 对应checkset的id
     */
    private Integer setId;

}