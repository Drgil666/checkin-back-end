package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.pojo.Photo;
import com.example.demo.service.PhotoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gilbert
 * @date 2020/10/29 14:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class PhotoServiceImplTest {
    @Resource
    private PhotoService photoService;

    @Test
    public void createPhoto() {
        Photo photo = new Photo();
        photo.setPhotoId(new byte[20]);
        photoService.createPhoto(photo);
        System.out.println(photo.getId());
    }

    @Test
    public void updatePhoto() {
        Photo photo = photoService.getPhoto("5f9a6813735f570c5952b7ed");
        photo.setPhotoId(new byte[20]);
        photoService.updatePhoto(photo);
    }

    @Test
    public void getPhoto() {
        System.out.println(photoService.getPhoto("5f9a6813735f570c5952b7ed"));
    }

    @Test
    public void deletePhoto() {
        List<String> id = new ArrayList<>();
        id.add("5f9a6829cc229b42df361c82");
        photoService.deletePhoto(id);
    }
}