package com.example.demo.service;

import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.toolkit.ImageInfo;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2021/4/1 9:33
 */
public interface FaceService {
    /**
     * 人脸检测
     *
     * @param img 照片base64
     * @return 人脸信息
     */
    List<FaceInfo> detectFaces(String img);

    /**
     * 人脸对比
     *
     * @param img1 用户图片
     * @param img2 拍摄图片
     * @return 相似度
     */
    Float compareFace(String img1, String img2);

    /**
     * 获取人脸特征
     *
     * @param imageInfo 照片信息
     * @param faceInfo  人脸信息
     * @return byte[]
     */
    byte[] extractFaceFeature(ImageInfo imageInfo, FaceInfo faceInfo);

}
