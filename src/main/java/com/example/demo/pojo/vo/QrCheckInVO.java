package com.example.demo.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author Gilbert
 * @date 2021/3/16 10:44
 */
@Data
public class QrCheckInVO {
    private Integer checkInId;
    private String type;
    private Integer schoolId;
    private Integer academyId;
    private Integer majorId;
    private Date date;
}
