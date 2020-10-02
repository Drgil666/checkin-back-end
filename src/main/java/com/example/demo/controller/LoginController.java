package com.example.demo.controller;

import com.example.demo.pojo.Response;
import com.example.demo.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author Gilbert
 * @date 2020/10/1 21:26
 */
@Controller
@Slf4j
@RequestMapping("/login")
public class LoginController {
    @Resource
    private LoginService loginService;

    @ResponseBody
    @PostMapping()
    public Response<Date> login(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        if (loginService.login(username, password)) {
            return Response.createSuc(new Date());
        }
        return Response.createErr("用户名或密码错误!");
    }
}
