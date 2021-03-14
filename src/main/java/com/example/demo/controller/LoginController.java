package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.HttpService;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import com.example.demo.utils.ApiJsonObject;
import com.example.demo.utils.ApiJsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "登录")
public class LoginController {
    @Resource
    private UserService userService;
    @Resource
    private TokenService tokenService;
    @Resource
    private HttpService httpService;

    @ResponseBody
    @PostMapping("/login")
    @ApiOperation(value = "判断/创建用户,并登录")
    public Response<String> login(@ApiJsonObject(name = "data1", value = {
            @ApiJsonProperty(key = "username", description = "用户名"),
            @ApiJsonProperty(key = "checkInId", description = "小签到id"),
            @ApiJsonProperty(key = "date", description = "创建时间")
    }) @RequestBody Map<String, String> data) {
        String username = data.get("username");
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
    @ApiOperation(value = "获取OpenId")
    public Object getOpenId(@ApiParam(value = "对应参数")@RequestParam("js_code") String jsCode) {
        return httpService.getOpenId(jsCode);
    }
}
