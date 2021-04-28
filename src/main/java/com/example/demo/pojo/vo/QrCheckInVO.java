package com.example.demo.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Gilbert
 * @date 2021/3/16 10:44
 */
@Data
@ApiModel(description = "CudRequestVO")
public class QrCheckInVO {
    @ApiModelProperty(value = "签到id", name = "checkInId", required = true)
    private Integer checkInId;
    @ApiModelProperty(value = "类型", name = "type", required = true)
    private String type;
    @ApiModelProperty(value = "学校id", name = "schoolId", required = true)
    private Integer schoolId;
    @ApiModelProperty(value = "学院id", name = "academyId", required = true)
    private Integer academyId;
    @ApiModelProperty(value = "专业id", name = "majorId", required = true)
    private Integer majorId;
    @ApiModelProperty(value = "时间", name = "date", required = true)
    private Date date;
}
