package com.example.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gilbert
 * @date 2021/3/14 11:34
 */
@Data
@ApiModel(description = "专业实体类")
public class Major {
    /**
     * 专业id
     */
    @ApiModelProperty(value = "专业id", name = "id", required = true)
    private Integer id;
    /**
     * 专业名
     */
    @ApiModelProperty(value = "专业名", name = "id", required = true)
    private String nick;
    /**
     * 学院id
     */
    @ApiModelProperty(value = "学院id", name = "id", required = true)
    private Integer academyId;
}
