package com.example.demo.controller;

import com.example.demo.pojo.Response;
import com.example.demo.pojo.vo.Pair;
import com.example.demo.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
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
    public Response<Map<String, Object>> login(@RequestBody Map<String, String> map) throws Exception {
        String username = map.get("username");
        String password = map.get("password");
        Pair<Boolean, Integer> result = loginService.login(username, password);
        if (result.getFirst()) {
            Map<String, Object> P = new HashMap<>(10);
            P.put("id", result.getSecond());
            P.put("logTime", new Date());
            return Response.createSuc(P);
        }
        return Response.createErr("用户名或密码错误!");
    }
}
