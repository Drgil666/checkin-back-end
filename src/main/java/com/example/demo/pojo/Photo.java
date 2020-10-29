package com.example.demo.pojo;

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
public class Photo {
    @Id
    private String id;
    /**
     * 照片的Base64串
     */
    private String photoId;
}
