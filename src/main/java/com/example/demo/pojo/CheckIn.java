package com.example.demo.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author Gilbert
 * @date 2020/9/25 16:32
 */
@Document(collection = "checkin")
@Data
@Alias("CheckIn")
public class CheckIn {
    /**
     * 签到id
     */
    @Id
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
    /**
     * 照片
     */
    private String photoId;
    /**
     * 签到状态
     */
    private Integer status;
}
