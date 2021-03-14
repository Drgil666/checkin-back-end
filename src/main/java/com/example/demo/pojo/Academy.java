package com.example.demo.pojo;

import lombok.Data;

/**
 * @author Gilbert
 * @date 2021/3/14 11:33
 */
@Data
public class Academy {
    /**
     * 学院id
     */
    private Integer id;
    /**
     * 学院名
     */
    private String nick;
    /**
     * 学校id
     */
    private Integer schoolId;
}
