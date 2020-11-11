package com.example.demo.controller;

import com.example.demo.pojo.vo.Response;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Gilbert
 * @date 2020/10/13 16:14
 */
@Controller
@RequestMapping()
public class LoginController {
    @Resource
    private UserService userService;

    @ResponseBody
    @PostMapping("/login")
    public Response<Integer> login(@RequestBody Map<String, String> data) {
        String username = data.get("username");
        Integer userId = userService.isExist(username);
        if (userId != null) {
            return Response.createSuc(userId);
        } else {
            return Response.createErr("用户不存在!");
        }
    }
}
