package com.example.demo.service.impl;

import com.example.demo.dao.PhotoDao;
import com.example.demo.pojo.Photo;
import com.example.demo.service.PhotoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gilbert
 * @date 2020/10/29 14:40
 */
@Service
public class PhotoServiceImpl implements PhotoService {
    @Resource
    private PhotoDao photoDao;

    /**
     * 创建照片
     *
     * @param photo 照片类
     * @return 带有id的Photo类
     */
    @Override
    public Photo createPhoto(Photo photo) {
        return photoDao.createPhoto(photo);
    }

    /**
     * 更新照片
     *
     * @param photo 照片类
     * @return 影响行数
     */
    @Override
    public Photo updatePhoto(Photo photo) {
        return photoDao.updatePhoto(photo);
    }

    /**
     * 获取照片
     *
     * @param id 照片MongoId
     * @return 照片类
     */
    @Override
    public Photo getPhoto(String id) {
        return photoDao.getPhoto(id);
    }

    /**
     * 批量删除照片类
     *
     * @param id 要删除的照片的id
     * @return 影响行数
     */
    @Override
    public long deletePhoto(List<String> id) {
        return photoDao.deletePhoto(id);
    }
}
