package com.example.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gilbert
 * @date 2021/3/14 11:31
 */
@Data
@ApiModel(description = "学校信息实体类")
public class School {
    /**
     * 学校id
     */
    @ApiModelProperty(value = "学校id", name = "id", required = true)
    private Integer id;
    /**
     * 学校名
     */
    @ApiModelProperty(value = "学校名", name = "nick", required = true)
    private String nick;
}
