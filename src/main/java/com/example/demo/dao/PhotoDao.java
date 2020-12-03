package com.example.demo.dao;

import com.example.demo.pojo.Photo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Gilbert
 * @date 2020/10/29 14:23
 */
public interface PhotoDao {
    /**
     * 创建照片
     *
     * @param photo 照片类
     * @return 带有id的Photo类
     */
    Photo createPhoto(Photo photo);

    /**
     * 更新照片
     *
     * @param photo 照片类
     * @return 更新好的照片类
     */
    Photo updatePhoto(Photo photo);

    /**
     * 获取照片
     *
     * @param id 照片MongoId
     * @return 照片类
     */
    Photo getPhoto(String id);

    /**
     * 批量删除照片类
     *
     * @param id 要删除的照片的id
     * @return 影响行数
     */
    long deletePhoto(List<String> id);
}
