package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

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

    @ResponseBody
    @PostMapping("/login")
    public Response<String> login(@RequestBody Map<String, String> data) {
        String username = data.get("username");
        Integer userId = userService.isExist(username);
        if (userId != 0) {
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
}
