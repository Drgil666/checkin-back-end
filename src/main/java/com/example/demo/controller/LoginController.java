package com.example.demo.controller;

import com.example.demo.pojo.Response;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author Gilbert
 * @date 2020/10/13 16:14
 */
@Controller
public class LoginController {
    @Resource
    private UserService userService;

    @ResponseBody
    @PostMapping("/login")
    public Response<Integer> login(@RequestParam("username") String username) {
        Integer userId = userService.isExist(username);
        if (userId != null) {
            return Response.createSuc(userId);
        } else {
            return Response.createErr("登录失败!");
        }
    }
}
