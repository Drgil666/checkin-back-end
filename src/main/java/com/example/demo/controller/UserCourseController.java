package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.Response;
import com.example.demo.pojo.UserCourse;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.service.UserCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@Slf4j
@RequestMapping("/api/course/users")
public class UserCourseController {
    @Resource
    private UserCourseService usercourseService;

    @ResponseBody
    @PostMapping()
    public Response<UserCourse> usercourse(@RequestBody CUDRequest<UserCourse, Integer> request) {
        switch (request.getMethod()) {
            case CUDRequest.CREATE_METHOD: {
                //if(usercourseService.isExist(request.getData().getUsername())!=null) {
                usercourseService.createUserCourse(request.getData());
                if (request.getData().getStuId() != null) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr("添加学生失败!");
                }
                //}
                //  else
                //{
                //  return Response.createErr("用户已存在!");
                //}
            }
            case CUDRequest.UPDATE_METHOD: {
                if (usercourseService.updateUserCourse(request.getData()) == 1) {
                    return Response.createSuc(request.getData());
                } else {
                    throw new ErrorException(ErrorCode.BIZ_PARAM_ILLEGAL, "更新失败!");
                }
            }
            default: {
                return Response.createErr("method错误!");
            }
        }
    }

}
