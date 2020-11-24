package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author chentao
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/admin/user")
public class AdminController {
    @Resource
    private AdminService adminService;

    @ResponseBody
    @PostMapping()
    public Response<Admin> admin(@RequestBody CUDRequest<Admin, Integer> request) {
        switch (request.getMethod()) {
            case CUDRequest.CREATE_METHOD: {
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
                return Response.createErr("method错误!");
            }
        }
    }

    @ResponseBody
    @PostMapping("/login")
    public Response<Admin> login(@RequestBody Map<String, String> data) {
        String username = data.get("username");
        String password = data.get("password");
        Admin admin = adminService.adminLogin(username, password);
        if (admin != null) {
            return Response.createSuc(admin);
        } else {
            return Response.createErr("登录失败!用户名或者密码错误!");
        }
    }

    @ResponseBody
    @GetMapping()
    public Response<Admin> admin(@RequestParam("id") Integer id) {
        Admin admin = adminService.getAdmin(id);
        if (admin != null) {
            return Response.createSuc(admin);
        } else {
            return Response.createErr("获取管理员信息失败!");
        }
    }
}
