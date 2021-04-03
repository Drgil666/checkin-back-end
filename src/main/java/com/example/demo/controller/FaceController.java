package com.example.demo.controller;

import com.arcsoft.face.FaceInfo;
import com.example.demo.exception.ErrorCode;
import com.example.demo.pojo.Photo;
import com.example.demo.pojo.ProcessInfo;
import com.example.demo.pojo.Sign;
import com.example.demo.pojo.User;
import com.example.demo.pojo.vo.PhotoVO;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.*;
import com.example.demo.utils.AssertionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2021/4/1 10:36
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/face")
@Api(tags = "人脸相关")
public class FaceController {
    @Resource
    private FaceService faceService;
    @Resource
    private TokenService tokenService;
    @Resource
    private PhotoService photoService;
    @Resource
    private UserService userService;
    @Resource
    private SignService signService;

    @ResponseBody
    @PostMapping()
    public Response<Float> compareDetect(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                         @ApiParam(value = "包含大签到信息，操作信息") @RequestBody PhotoVO request) {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        Integer userId = tokenService.getUserIdByToken(token);
        AssertionUtil.notNull(userId, ErrorCode.BIZ_PARAM_ILLEGAL, "userId为空!");
        List<ProcessInfo> processInfos = faceService.liveDetect(request.getImg());
        AssertionUtil.isTrue(processInfos.get(0).getLiveness() == 1,
                ErrorCode.UNKNOWN_ERROR, "活体检测失败!请对着人脸拍摄");
        byte[] imgFeature = faceService.getFaceFeature(request.getImg());
        switch (request.getType()) {
            case PhotoVO.TYPE_SIGNIN: {
                User user = userService.getUser(userId);
                String photoId = user.getPhotoId();
                if (photoId == null || photoId.length() == 0) {
                    return Response.createErr("您未录入人脸!请去个人中心录入!");
                }
                byte[] userFeature = photoService.getPhoto(photoId).getPhotoId();
                Float result = faceService.compareFace(userFeature, imgFeature);
                AssertionUtil.notNull(result, ErrorCode.BIZ_PARAM_ILLEGAL, "人脸比对失败!");
                if (result >= PhotoVO.COMPARE_THRESHOLD) {
                    Photo photo = new Photo();
                    photo.setPhotoId(imgFeature);
                    if (photoService.createPhoto(photo) == null) {
                        return Response.createErr("签到照片存储失败!请重新签到!");
                    }
                    Sign anotherSign = signService.getSignByCheckIdAndUserId(request.getCheckInId(), userId);
                    if (anotherSign != null) {
                        return Response.createErr("您已经签到!无需重复签到!");
                    }
                    Sign sign = new Sign();
                    sign.setNick(user.getNick());
                    sign.setSignTime(new Date());
                    sign.setStuId(user.getId());
                    sign.setStuNo(user.getStuNo());
                    sign.setPhotoId(photo.getId());
                    sign.setCheckId(request.getCheckInId());
                    if (signService.createSign(sign)) {
                        return Response.createSuc(null);
                    } else {
                        return Response.createErr("创建签到失败!请重新签到!");
                    }
                } else {
                    return Response.createErr("人脸吻合度不足!请重新拍照!");
                }
            }
            case PhotoVO.TYPE_USER: {
                User user = userService.getUser(userId);
                if (user.getPhotoId() != null && user.getPhotoId().length() != 0) {
                    return Response.createErr("您已录入人脸!");
                }
                List<FaceInfo> faceInfoList = faceService.detectFaces(request.getImg());
                if (CollectionUtils.isEmpty(faceInfoList)) {
                    return Response.createErr("未检测到人脸!");
                }
                Photo photo = new Photo();
                photo.setPhotoId(imgFeature);
                if (photoService.createPhoto(photo) != null) {
                    user.setPhotoId(photo.getId());
                    if (userService.updateUser(user) == 1) {
                        return Response.createSuc(null);
                    }
                }
                return Response.createErr("照片录入失败!");
            }
            default:
                return Response.createErr("Type错误!");
        }
    }
}
