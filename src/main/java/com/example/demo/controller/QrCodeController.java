package com.example.demo.controller;

import com.example.demo.pojo.vo.Response;
import com.example.demo.service.QrCodeService;
import com.example.demo.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author yutao
 * @Date 2020/10/29 13:20
 */
@CrossOrigin(origins = "*")
@Controller
@Slf4j
@RequestMapping("/api/qrCode")
public class QrCodeController {
    @Resource
    private QrCodeService qrCodeService;
    @Resource
    private TokenService tokenService;

    @ResponseBody
    @PostMapping()
    public Response<String> createQr(@RequestHeader("Token") String token, @RequestBody HashMap<String, Object> map) {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        String qrCode = qrCodeService.createQr(map);
        if (qrCode != null) {
            return Response.createSuc(qrCode);
        } else {
            return Response.createErr("二维码创建失败!");
        }
    }
}
