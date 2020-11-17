package com.example.demo.pojo;

import lombok.Data;

/**
 * @author Gilbert
 * @Date 2020/9/25 16:32
 */

@Data
public class CheckSet {
    /**
     * 签到id
     */
    private Integer id;
    /**
     * 签到名
     */
    private String nick;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 是否可见
     */
    private int visible;
}
