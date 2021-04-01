package com.example.demo.service.impl;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import com.example.demo.exception.ErrorCode;
import com.example.demo.pojo.FaceEngineFactory;
import com.example.demo.service.FaceService;
import com.example.demo.utils.AssertionUtil;
import com.example.demo.utils.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author DrGilbert
 * @date 2021/4/1 9:35
 */
@Slf4j
@Service
public class FaceServiceImpl implements FaceService {
    public static final String APP_ID = "Aabc3fgCYw5dfJEAAuJ9kwo9hzhY6Yu1azbQ6nkE5RjY";
    //    public static final String SDK_KEY = "Hzpq8QyVckTpsKEPLgSetXtS3k7CiYByNsFKb3qrjznD";
    public static final String SDK_KEY = "Hzpq8QyVckTpsKEPLgSetXtRudY5Vw7NJ4q7sGBZuvt5";
    //    public static final String SDK_LIB_PATH = "Y:\\Users\\DrGilbert\\IdeaProjects\\checkin-back-end\\libs\\WIN64";
    public static final String SDK_LIB_PATH = "/var/lib/jenkins/workspace/zjgsu-checkin-back-end/libs/LINUX64";
    public static final Integer DETECT_POO_SIZE = 5;
    public static final Integer COMPARE_POO_SIZE = 5;
    private ExecutorService compareExecutorService;
    private GenericObjectPool<FaceEngine> faceEngineGeneralPool;
    private GenericObjectPool<FaceEngine> faceEngineComparePool;

    @PostConstruct
    public void init() {
        GenericObjectPoolConfig detectPoolConfig = new GenericObjectPoolConfig();
        detectPoolConfig.setMaxIdle(DETECT_POO_SIZE);
        detectPoolConfig.setMaxTotal(DETECT_POO_SIZE);
        detectPoolConfig.setMinIdle(DETECT_POO_SIZE);
        detectPoolConfig.setLifo(false);
        EngineConfiguration detectCfg = new EngineConfiguration();
        FunctionConfiguration detectFunctionCfg = new FunctionConfiguration();
        detectFunctionCfg.setSupportFaceDetect(true);
        detectFunctionCfg.setSupportFaceRecognition(true);
        detectFunctionCfg.setSupportAge(true);
        detectFunctionCfg.setSupportGender(true);
        detectFunctionCfg.setSupportLiveness(true);
        detectCfg.setFunctionConfiguration(detectFunctionCfg);
        detectCfg.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        detectCfg.setDetectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY);
        faceEngineGeneralPool = new GenericObjectPool(new FaceEngineFactory(SDK_LIB_PATH, APP_ID, SDK_KEY, null, detectCfg), detectPoolConfig);
        GenericObjectPoolConfig comparePoolConfig = new GenericObjectPoolConfig();
        comparePoolConfig.setMaxIdle(COMPARE_POO_SIZE);
        comparePoolConfig.setMaxTotal(COMPARE_POO_SIZE);
        comparePoolConfig.setMinIdle(COMPARE_POO_SIZE);
        comparePoolConfig.setLifo(false);
        EngineConfiguration compareCfg = new EngineConfiguration();
        FunctionConfiguration compareFunctionCfg = new FunctionConfiguration();
        compareFunctionCfg.setSupportFaceRecognition(true);
        compareCfg.setFunctionConfiguration(compareFunctionCfg);
        compareCfg.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        compareCfg.setDetectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY);
        faceEngineComparePool = new GenericObjectPool(new FaceEngineFactory(SDK_LIB_PATH, APP_ID, SDK_KEY, null, compareCfg), comparePoolConfig);
        compareExecutorService = Executors.newFixedThreadPool(COMPARE_POO_SIZE);
    }

    /**
     * 人脸检测
     *
     * @param img 照片base64
     * @return 人脸信息
     */
    @Override
    public List<FaceInfo> detectFaces(String img) {
        ImageInfo imageInfo = getImageInfo(img);
        FaceEngine faceEngine = null;
        try {
            faceEngine = faceEngineGeneralPool.borrowObject();
            AssertionUtil.notNull(faceEngine, ErrorCode.INNER_PARAM_ILLEGAL, "获取引擎失败");
            List<FaceInfo> faceInfoList = new ArrayList<>();
            int errorCode = faceEngine.detectFaces(imageInfo.getImageData(),
                    imageInfo.getWidth(),
                    imageInfo.getHeight(),
                    imageInfo.getImageFormat(),
                    faceInfoList);
            if (errorCode == 0) {
                return faceInfoList;
            } else {
                log.error("人脸检测失败，errorCode：" + errorCode);
            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if (faceEngine != null) {
                faceEngineGeneralPool.returnObject(faceEngine);
            }
        }
        return null;
    }

    /**
     * 人脸对比
     *
     * @param img1 用户图片
     * @param img2 拍摄图片
     * @return 相似度
     */
    @Override
    public Float compareFace(String img1, String img2) {
        ImageInfo imageInfo1 = getImageInfo(img1);
        ImageInfo imageInfo2 = getImageInfo(img2);
        List<FaceInfo> faceInfoList1 = detectFaces(img1);
        List<FaceInfo> faceInfoList2 = detectFaces(img2);
        AssertionUtil.isTrue(!(CollectionUtils.isEmpty(faceInfoList1) ||
                CollectionUtils.isEmpty(faceInfoList2)), ErrorCode.UNKNOWN_ERROR, "未检测到人脸");

        byte[] feature1 = extractFaceFeature(imageInfo1, faceInfoList1.get(0));
        byte[] feature2 = extractFaceFeature(imageInfo2, faceInfoList2.get(0));

        FaceEngine faceEngine = null;
        try {
            faceEngine = faceEngineGeneralPool.borrowObject();
            AssertionUtil.notNull(faceEngine, ErrorCode.UNKNOWN_ERROR, "获取引擎失败");
            FaceFeature faceFeature1 = new FaceFeature();
            faceFeature1.setFeatureData(feature1);
            FaceFeature faceFeature2 = new FaceFeature();
            faceFeature2.setFeatureData(feature2);
            FaceSimilar faceSimilar = new FaceSimilar();
            int errorCode = faceEngine.compareFaceFeature(faceFeature1, faceFeature2, faceSimilar);
            if (errorCode == 0) {
                return faceSimilar.getScore();
            } else {
                log.error("特征提取失败，errorCode：" + errorCode);
            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if (faceEngine != null) {
                faceEngineGeneralPool.returnObject(faceEngine);
            }
        }
        return null;
    }

    /**
     * 获取人脸特征
     *
     * @param imageInfo 照片信息
     * @param faceInfo  人脸信息
     * @return byte[]
     */
    @Override
    public byte[] extractFaceFeature(ImageInfo imageInfo, FaceInfo faceInfo) {

        FaceEngine faceEngine = null;
        try {
            faceEngine = faceEngineGeneralPool.borrowObject();
            AssertionUtil.notNull(faceEngine, ErrorCode.UNKNOWN_ERROR, "获取引擎失败");
            FaceFeature faceFeature = new FaceFeature();
            //提取人脸特征
            int errorCode = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfo, faceFeature);
            if (errorCode == 0) {
                return faceFeature.getFeatureData();
            } else {
                log.error("特征提取失败，errorCode：" + errorCode);
            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if (faceEngine != null) {
                //释放引擎对象
                faceEngineGeneralPool.returnObject(faceEngine);
            }
        }
        return null;
    }

    /**
     * 将Base64转化为图片
     *
     * @param base64 图片base64编码
     * @return imageInfo
     */
    private ImageInfo getImageInfo(String base64) {
        byte[] bytes = Base64Util.base64ToBytes(base64);
        return ImageFactory.getRGBData(bytes);
    }
}
