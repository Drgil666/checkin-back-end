package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.Response;
import com.example.demo.pojo.User;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Gilbert
 * @Date 2020/9/24 16:28
 */
@Controller
@Slf4j
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;

    @ResponseBody
    @PostMapping()
    public Response<User> user(@RequestBody CUDRequest<User, Integer> request) {
        switch (request.getMethod()) {
            case CUDRequest.CREATE_METHOD: {
                if (userService.isExist(request.getData().getUsername()) != null) {
                    userService.createUser(request.getData());
                    if (request.getData().getId() != null) {
                        return Response.createSuc(request.getData());
                    } else {
                        return Response.createErr("创建用户失败!");
                    }
                } else {
                    return Response.createErr("用户已存在!");
                }
            }
            case CUDRequest.UPDATE_METHOD: {
                if (userService.updateUser(request.getData()) == 1) {
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

    @ResponseBody
    @GetMapping()
    public Response<User> user(@RequestParam("userId") Integer userId) {
        User user = userService.getUser(userId);
        if (user != null) {
            return Response.createSuc(user);
        } else {
            return Response.createErr("获取用户失败!");
        }
    }

    @ResponseBody
    @GetMapping("/check")
    public String check() {
        return "ok2";
    }
}
