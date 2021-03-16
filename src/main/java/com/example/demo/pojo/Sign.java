package com.example.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author chentao
 */
@Data
@ApiModel(description = "签到记录实体类")
public class Sign {
    @ApiModelProperty(value = "签到记录id", name = "id", required = true)
    private Integer id;
    /**
     * 签到人id
     */
    @ApiModelProperty(value = "签到人id", name = "stuId", required = true)
    private Integer stuId;
    /**
     * 签到时间
     */
    @ApiModelProperty(value = "签到时间", name = "signTime", required = true)
    private Date signTime;
    /**
     * 签到时照片id
     */
    @ApiModelProperty(value = "签到时照片id", name = "photoId", required = true)
    private String photoId;
    /**
     * 对应签到id
     */
    @ApiModelProperty(value = "对应签到id", name = "checkId", required = true)
    private Integer checkId;
    /**
     * 签到人名字
     */
    @ApiModelProperty(value = "签到人名字", name = "nick", required = true)
    private String nick;
    /**
     * 签到人学号
     */
    @ApiModelProperty(value = "签到人学号", name = "stuNo", required = true)
    private String stuNo;
}
