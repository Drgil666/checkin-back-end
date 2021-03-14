package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.AdminService;
import com.example.demo.service.BcryptService;
import com.example.demo.service.TokenService;
import com.example.demo.service.impl.TokenServiceImpl;
import com.example.demo.utils.AssertionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static com.example.demo.service.impl.TokenServiceImpl.TYPE_ADMIN;

/**
 * @author chentao
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private TokenService tokenService;
    @Resource
    private BcryptService bcryptService;

    @ResponseBody
    @PostMapping()
    public Response<Admin> admin(@RequestHeader("Token") String token,
                                 @RequestBody CUDRequest<Admin, Integer> request) {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");

        switch (request.getMethod()) {
            case CUDRequest.CREATE_METHOD: {
                if (adminService.adminExist(request.getData().getUsername())) {
                    return Response.createErr("用户名已被注册!");
                }
                adminService.createAdmin(request.getData());
                if (request.getData().getId() != null) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr("创建管理员失败!");
                }
            }
            case CUDRequest.UPDATE_METHOD: {
                if (adminService.updateAdmin(request.getData()) == 1) {
                    return Response.createSuc(request.getData());
                } else {
                    throw new ErrorException(ErrorCode.BIZ_PARAM_ILLEGAL, "更新失败!");
                }
            }
            default: {
                return Response.createErr(CUDRequest.METHOD_ERROR);
            }
        }
    }

    @ResponseBody
    @PostMapping("/login")
    public Response<String> login(@RequestBody Map<String, String> data) {
        String username = data.get(TokenServiceImpl.ATTRIBUTE_USERNAME);
        String password = data.get(TokenServiceImpl.ATTRIBUTE_PASSWORD);
        AssertionUtil.notNull(adminService.adminExist(username), ErrorCode.BIZ_PARAM_ILLEGAL, "用户名或者密码错误!");
        Admin admin = adminService.getAdminByUsername(username);
        if (bcryptService.checkPassword(password, admin.getPassword())) {
            String token = tokenService.createToken(username, TYPE_ADMIN);
            return Response.createSuc(token);
        } else {
            return Response.createErr("用户名或密码错误!");
        }
    }

    @ResponseBody
    @GetMapping()
    public Response<Admin> admin(@RequestHeader("Token") String token,
                                 @RequestParam(value = "id", required = false) Integer id) {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        if (id == null) {
            id = tokenService.getUserIdByToken(token);
        }
        Admin admin = adminService.getAdmin(id);
        if (admin != null) {
            return Response.createSuc(admin);
        } else {
            return Response.createErr("获取管理员信息失败!");
        }
    }
}
