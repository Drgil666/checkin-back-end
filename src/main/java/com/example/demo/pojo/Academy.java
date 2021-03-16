package com.example.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gilbert
 * @date 2021/3/14 11:33
 */
@Data
@ApiModel(description = "学院实体类")
public class Academy {
    /**
     * 学院id
     */
    @ApiModelProperty(value = "学院id", name = "id", required = true)
    private Integer id;
    /**
     * 学院名
     */
    @ApiModelProperty(value = "学院名", name = "nick", required = true)
    private String nick;
    /**
     * 学校id
     */
    @ApiModelProperty(value = "学校id", name = "schoolId", required = true)
    private Integer schoolId;
}
