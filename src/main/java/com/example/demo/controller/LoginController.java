package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.pojo.vo.LoginVO;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.HttpService;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.example.demo.service.impl.TokenServiceImpl.TYPE_USER;

/**
 * @author Gilbert
 * @date 2020/10/13 16:14
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping()
public class LoginController {
    @Resource
    private UserService userService;
    @Resource
    private TokenService tokenService;
    @Resource
    private HttpService httpService;

    @ResponseBody
    @PostMapping("/login")
    public Response<String> login(@RequestBody LoginVO data) {
        String username = data.getUsername();
        if (username == null) {
            return Response.createErr("登录失败!");
        }
        Integer userId = userService.isExist(username);
        if (userId != null) {
            String token = tokenService.createToken(username, TYPE_USER);
            return Response.createSuc(token);
        } else {
            User user = new User();
            user.setUsername(username);
            userService.createUser(user);
            String token = tokenService.createToken(username, TYPE_USER);
            return Response.createSuc(token);
        }
    }

    @ResponseBody
    @GetMapping("/openId")
    public Object getOpenId(@RequestParam("js_code") String jsCode) {
        return httpService.getOpenId(jsCode);
    }
}
