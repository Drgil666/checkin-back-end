package com.example.demo.pojo.vo;

import com.example.demo.pojo.Sign;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yutao
 * sign工具类，存含学生名和签到情况的签到信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SignVO extends Sign {
    /**
     * 签到人名字
     */
    private String nick;
    /**
     * 签到人学号
     */
    private String stuNo;
}
