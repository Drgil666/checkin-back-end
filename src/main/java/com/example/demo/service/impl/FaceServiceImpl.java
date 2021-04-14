package com.example.demo.service.impl;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import com.example.demo.exception.ErrorCode;
import com.example.demo.pojo.FaceEngineFactory;
import com.example.demo.pojo.ProcessInfo;
import com.example.demo.service.FaceService;
import com.example.demo.utils.AssertionUtil;
import com.example.demo.utils.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${face.app.id}")
    private String appId;
    @Value("${face.sdk.key}")
    private String sdkKey;
    @Value("${face.sdk.lib-path}")
    private String sdkLibPath;
    @Value("${face.detect-size}")
    private Integer detectPooSize;
    @Value("${face.compare-size}")
    private Integer comparePooSize;
    private ExecutorService compareExecutorService;
    private GenericObjectPool<FaceEngine> faceEngineGeneralPool;
    private GenericObjectPool<FaceEngine> faceEngineComparePool;

    @PostConstruct
    public void init() {
        GenericObjectPoolConfig detectPoolConfig = new GenericObjectPoolConfig();
        detectPoolConfig.setMaxIdle(detectPooSize);
        detectPoolConfig.setMaxTotal(detectPooSize);
        detectPoolConfig.setMinIdle(detectPooSize);
        detectPoolConfig.setLifo(false);
        EngineConfiguration detectCfg = new EngineConfiguration();
        FunctionConfiguration detectFunctionCfg = new FunctionConfiguration();
        detectFunctionCfg.setSupportFaceDetect(true);
        detectFunctionCfg.setSupportFaceRecognition(true);
        detectFunctionCfg.setSupportAge(true);
        detectFunctionCfg.setSupportGender(true);
        detectFunctionCfg.setSupportLiveness(true);
        detectFunctionCfg.setSupportIRLiveness(true);
        detectCfg.setFunctionConfiguration(detectFunctionCfg);
        detectCfg.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        detectCfg.setDetectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY);
        faceEngineGeneralPool = new GenericObjectPool(new FaceEngineFactory(sdkLibPath, appId, sdkKey, null, detectCfg), detectPoolConfig);
        GenericObjectPoolConfig comparePoolConfig = new GenericObjectPoolConfig();
        comparePoolConfig.setMaxIdle(comparePooSize);
        comparePoolConfig.setMaxTotal(comparePooSize);
        comparePoolConfig.setMinIdle(comparePooSize);
        comparePoolConfig.setLifo(false);
        EngineConfiguration compareCfg = new EngineConfiguration();
        FunctionConfiguration compareFunctionCfg = new FunctionConfiguration();
        compareFunctionCfg.setSupportFaceRecognition(true);
        compareCfg.setFunctionConfiguration(compareFunctionCfg);
        compareCfg.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        compareCfg.setDetectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY);
        faceEngineComparePool = new GenericObjectPool(new FaceEngineFactory(sdkLibPath, appId, sdkKey, null, compareCfg), comparePoolConfig);
        compareExecutorService = Executors.newFixedThreadPool(comparePooSize);
    }

    /**
     * 获取人脸特征
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
     * @param feature1 用户图片
     * @param feature2 拍摄图片
     * @return 相似度
     */
    @Override
    public Float compareFace(byte[] feature1, byte[] feature2) {
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
     * 活体检测
     *
     * @param img 图片base64
     */
    @Override
    public List<ProcessInfo> liveDetect(String img) {
        ImageInfo imageInfo = getImageInfo(img);
        List<FaceInfo> faceInfoList = detectFaces(img);
        FaceEngine faceEngine = null;
        try {
            //获取引擎对象
            faceEngine = faceEngineGeneralPool.borrowObject();
            AssertionUtil.notNull(faceEngine, ErrorCode.UNKNOWN_ERROR, "获取引擎失败");
            FunctionConfiguration functionConfiguration = new FunctionConfiguration();
            functionConfiguration.setSupportAge(true);
            functionConfiguration.setSupportGender(true);
            functionConfiguration.setSupportLiveness(true);
            int errorCode = faceEngine.process(imageInfo.getImageData(),
                    imageInfo.getWidth(),
                    imageInfo.getHeight(),
                    imageInfo.getImageFormat(),
                    faceInfoList, functionConfiguration);
            if (errorCode == 0) {
                List<ProcessInfo> processInfoList = new ArrayList<>();
                List<GenderInfo> genderInfoList = new ArrayList<GenderInfo>();
                faceEngine.getGender(genderInfoList);
                List<AgeInfo> ageInfoList = new ArrayList<AgeInfo>();
                faceEngine.getAge(ageInfoList);
                List<LivenessInfo> livenessInfoList = new ArrayList<>();
                faceEngine.getLiveness(livenessInfoList);
                for (int i = 0; i < genderInfoList.size(); i++) {
                    ProcessInfo processInfo = new ProcessInfo();
                    processInfo.setGender(genderInfoList.get(i).getGender());
                    processInfo.setAge(ageInfoList.get(i).getAge());
                    processInfo.setLiveness(livenessInfoList.get(i).getLiveness());
                    processInfoList.add(processInfo);
                }
                return processInfoList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (faceEngine != null) {
                faceEngineGeneralPool.returnObject(faceEngine);
            }
        }
        return null;
    }

    /**
     * 获取人脸信息
     *
     * @param img 图片base64
     * @return 字节数组
     */
    @Override
    public byte[] getFaceFeature(String img) {
        ImageInfo imageInfo = getImageInfo(img);
        List<FaceInfo> faceInfoList = detectFaces(img);
        return extractFaceFeature(imageInfo, faceInfoList.get(0));
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
