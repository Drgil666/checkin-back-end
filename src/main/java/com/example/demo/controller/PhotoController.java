package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.Photo;
import com.example.demo.pojo.User;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.PhotoService;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import com.example.demo.utils.AssertionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yutao
 * @Date 2020/11/26 20:14
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/photo")
public class PhotoController {
    @Resource
    private PhotoService photoService;
    @Resource
    private TokenService tokenService;
    @Resource
    private UserService userService;

    @ResponseBody
    @PostMapping()
    public Response<Photo> photo(@RequestHeader("Token") String token, @RequestBody CUDRequest<Photo, Integer> request) {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        switch (request.getMethod()) {
            case CUDRequest.CREATE_METHOD: {
                photoService.createPhoto(request.getData());
                if (request.getData().getId() != null) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr("创建照片失败!");
                }
            }
            case CUDRequest.UPDATE_METHOD: {
                if (photoService.updatePhoto(request.getData()) != null) {
                    return Response.createSuc(request.getData());
                } else {
                    throw new ErrorException(ErrorCode.BIZ_PARAM_ILLEGAL, "更新失败!");
                }
            }
            default: {
                return Response.createErr(CUDRequest.METHOD_ERROR);
            }
        }
    }

    @ResponseBody
    @GetMapping()
    public Response<Photo> photo(@RequestHeader("Token") String token,
                                 @RequestParam(value = "id", required = false) String id) {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        Photo photo;
        if (id != null) {
            photo = photoService.getPhoto(id);
        } else {
            Integer userId = tokenService.getUserIdByToken(token);
            User user = userService.getUser(userId);
            photo = photoService.getPhoto(user.getPhotoId());
        }
        if (photo != null) {
            return Response.createSuc(photo);
        } else {
            return Response.createErr("获取照片失败!");
        }
    }

    @ResponseBody
    @GetMapping("/admin")
    public Response<Photo> photoByUserId(@RequestHeader("Token") String token,
                                         @RequestParam(value = "userId", required = false) Integer userId) {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        if (userId == null) {
            userId = tokenService.getUserIdByToken(token);
        }
        if (userId == null) {
            return Response.createErr("获取userId失败!userId为空");
        }
        User user = userService.getUser(userId);
        Photo photo = photoService.getPhoto(user.getPhotoId());
        if (photo != null) {
            return Response.createSuc(photo);
        } else {
            return Response.createErr("获取照片失败!");
        }
    }
}
