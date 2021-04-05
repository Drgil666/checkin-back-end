package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.RedisUser;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.pojo.vo.LoginVO;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.AdminService;
import com.example.demo.service.BcryptService;
import com.example.demo.service.TokenService;
import com.example.demo.utils.AssertionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author chentao
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
@Api(tags = "管理员操作")
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private TokenService tokenService;
    @Resource
    private BcryptService bcryptService;

    @ResponseBody
    @PostMapping()
    @ApiOperation(value = "创建/更新Admin")
    public Response<Admin> admin(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                 @ApiParam(value = "包含管理员信息，操作信息") @RequestBody CUDRequest<Admin, Integer> request) {
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
    @ApiOperation(value = "管理员登录")
    public Response<String> login(@ApiParam(value = "包含签到信息") @RequestBody LoginVO data) {
        String username = data.getUsername();
        String password = data.getPassword();
        AssertionUtil.notNull(adminService.adminExist(username), ErrorCode.BIZ_PARAM_ILLEGAL, "用户名或者密码错误!");
        Admin admin = adminService.getAdminByUsername(username);
        if (bcryptService.checkPassword(password, admin.getPassword())) {
            String token = tokenService.createUserToken(username, RedisUser.TYPE_ADMIN);
            return Response.createSuc(token);
        } else {
            return Response.createErr("用户名或密码错误!");
        }
    }

    @ResponseBody
    @GetMapping()
    @ApiOperation(value = "通过管理员id获取管理员信息")
    public Response<Admin> admin(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                 @ApiParam(value = "管理员id") @RequestParam(value = "id", required = false) Integer id) {
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
