package com.example.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Gilbert
 * @Date 2020/9/25 16:32
 */
@Data
@ApiModel(description = "签到信息实体类")
public class CheckIn {
    /**
     * 签到id
     */
    @ApiModelProperty(value = "小签到id", name = "id", required = true)
    private Integer id;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", name = "startTime", required = true)
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", name = "endTime", required = true)
    private Date endTime;

    /**
     * 签到状态 未开始/进行中/已结束
     */
    @ApiModelProperty(value = "签到状态", name = "status", required = true)
    private Integer status;
    /**
     * 是否可见
     */
    @ApiModelProperty(value = "是否可见", name = "visible", required = true)
    private int visible;

    /**
     * 签到环节 签到/签退
     */
    @ApiModelProperty(value = "签到环节", name = "type", required = true)
    private Integer type;
    /**
     * 对应checkset的id
     */
    @ApiModelProperty(value = "大签到id", name = "setId", required = true)
    private Integer setId;

}