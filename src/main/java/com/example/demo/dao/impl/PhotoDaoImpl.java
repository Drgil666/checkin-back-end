package com.example.demo.dao.impl;

import com.example.demo.dao.PhotoDao;
import com.example.demo.pojo.Photo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gilbert
 * @date 2020/10/29 14:26
 */
@Component("PhotoDaoImpl")
public class PhotoDaoImpl implements PhotoDao {
    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 创建照片
     *
     * @param photo 照片类
     * @return 带有id的Photo类
     */
    @Override
    public Photo createPhoto(Photo photo) {
        return mongoTemplate.insert(photo);
    }

    /**
     * 更新照片
     *
     * @param photo 照片类
     * @return 影响行数
     */
    @Override
    public Photo updatePhoto(Photo photo) {
        return mongoTemplate.save(photo);
    }

    /**
     * 获取照片
     *
     * @param id 照片MongoId
     * @return 照片类
     */
    @Override
    public Photo getPhoto(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Photo.class);
    }

    /**
     * 批量删除照片类
     *
     * @param id 要删除的照片的id
     * @return 影响行数
     */
    @Override
    public long deletePhoto(List<String> id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").in(id));
        return mongoTemplate.remove(query, Photo.class).getDeletedCount();
    }
}
