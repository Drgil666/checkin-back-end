package com.example.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gilbert
 * @Date 2020/9/25 16:32
 */

@Data
@ApiModel(description = "签到实体类")
public class CheckSet {
    /**
     * 签到id
     */
    @ApiModelProperty(value = "大签到id", name = "id", required = true)
    private Integer id;
    /**
     * 签到名
     */
    @ApiModelProperty(value = "签到名", name = "nick", required = true)
    private String nick;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "userId", required = true)
    private Integer userId;
    /**
     * 是否可见
     */
    @ApiModelProperty(value = "是否可见", name = "visible", required = true)
    private int visible;
}
