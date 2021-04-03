package com.example.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Gilbert
 * @date 2020/10/29 14:16
 * 存照片
 */
@Document(collection = "photo")
@Alias("photo")
@Data
@ApiModel(description = "照片信息实体类")
public class Photo {
    @Id
    @ApiModelProperty(value = "照片id", name = "id", required = true)
    private String id;
    /**
     * 照片的Base64串
     */
    @ApiModelProperty(value = "照片的字节串", name = "photoId", required = true)
    private byte[] photoId;
}
