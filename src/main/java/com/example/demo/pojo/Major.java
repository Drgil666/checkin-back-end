package com.example.demo.pojo;

import lombok.Data;

/**
 * @author Gilbert
 * @date 2021/3/14 11:34
 */
@Data
public class Major {
    /**
     * 专业id
     */
    private Integer id;
    /**
     * 专业名
     */
    private String nick;
    /**
     * 学院id
     */
    private Integer academyId;
}
