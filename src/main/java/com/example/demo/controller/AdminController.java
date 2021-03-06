package com.example.demo.controller;

import com.example.demo.annotation.Authorize;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.vo.CudRequestVO;
import com.example.demo.pojo.vo.LoginVO;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.AdminService;
import com.example.demo.service.BcryptService;
import com.example.demo.service.TokenService;
import com.example.demo.utils.AssertionUtil;
import com.example.demo.utils.AuthorizeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.example.demo.service.impl.TokenServiceImpl.TYPE_ADMIN;

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
    @Authorize(value = AuthorizeUtil.Character.TYPE_USER)
    public Response<Admin> admin(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                 @ApiParam(value = "包含管理员信息，操作信息") @RequestBody CudRequestVO<Admin, Integer> request) {

        switch (request.getMethod()) {
            case CudRequestVO.CREATE_METHOD: {
                if (adminService.adminExistByUsername(request.getData().getUsername())) {
                    return Response.createErr("用户名已被注册!");
                }
                adminService.createAdmin(request.getData());
                if (request.getData().getId() != null) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr("创建管理员失败!");
                }
            }
            case CudRequestVO.UPDATE_METHOD: {
                if (adminService.updateAdmin(request.getData()) == 1) {
                    return Response.createSuc(request.getData());
                } else {
                    throw new ErrorException(ErrorCode.BIZ_PARAM_ILLEGAL, "更新失败!");
                }
            }
            default: {
                return Response.createErr(CudRequestVO.METHOD_ERROR);
            }
        }
    }

    @ResponseBody
    @PostMapping("/login")
    @ApiOperation(value = "管理员登录")
    public Response<String> login(@ApiParam(value = "包含签到信息") @RequestBody LoginVO data) {
        String username = data.getUsername();
        String password = data.getPassword();
        AssertionUtil.isTrue(adminService.adminExistByUsername(username), ErrorCode.BIZ_PARAM_ILLEGAL, "用户名或者密码错误!");
        Admin admin = adminService.getAdminByUsername(username);
        if (bcryptService.checkPassword(password, admin.getPassword())) {
            String token = tokenService.createUserToken(username, TYPE_ADMIN);
            return Response.createSuc(token);
        } else {
            return Response.createErr("用户名或密码错误!");
        }
    }

    @ResponseBody
    @GetMapping()
    @ApiOperation(value = "通过管理员id获取管理员信息")
    @Authorize(value = AuthorizeUtil.Character.TYPE_NORMAL)
    public Response<Admin> admin(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                 @ApiParam(value = "管理员id") @RequestParam(value = "id", required = false) Integer id) {

        if (id == null) {
            id = tokenService.getUserIdByToken(token);
        }
        Admin admin = adminService.getAdminById(id);
        if (admin != null) {
            return Response.createSuc(admin);
        } else {
            return Response.createErr("获取管理员信息失败!");
        }
    }
}
