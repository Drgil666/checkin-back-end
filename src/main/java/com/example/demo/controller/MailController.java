package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.MailService;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Gilbert
 * @date 2021/4/16 15:49
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/verify")
@Api(tags = "邮箱验证码相关")
public class MailController {
    @Resource
    private MailService mailService;
    @Resource
    private TokenService tokenService;
    @Resource
    private UserService userService;

    @ResponseBody
    @PostMapping()
    public Response<String> createVerification(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token) {
        if (!tokenService.loginCheck(token)) {
            return Response.createTokenAuthorizedErr();
        }
        Integer userId = tokenService.getUserIdByToken(token);
        User user = userService.getUser(userId);
        String userMail = user.getMail();
        String code = tokenService.createMailToken(token);
        mailService.sendSimpleMail(userMail, "ZjgsuCheckIn密码找回", code);
        return Response.createSuc(code);
    }

    @ResponseBody
    @PostMapping("/verify")
    public Response<String> verify(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                   @ApiParam(value = "code") @RequestBody String code) {
        if (!tokenService.loginCheck(token)) {
            return Response.createTokenAuthorizedErr();
        }
        if (tokenService.checkMailToken(token, code)) {
            return Response.createSuc(null);
        } else {
            return Response.createErr("验证码错误!");
        }
    }
}
