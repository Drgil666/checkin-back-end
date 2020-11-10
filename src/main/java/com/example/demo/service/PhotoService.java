package com.example.demo.service;

import com.example.demo.pojo.Photo;

import java.util.List;

/**
 * @author Gilbert
 * @date 2020/10/29 14:40
 */
public interface PhotoService {
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
     * @return 影响行数
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
