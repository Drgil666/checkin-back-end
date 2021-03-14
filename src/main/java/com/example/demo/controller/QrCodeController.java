package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.QrCodeService;
import com.example.demo.service.TokenService;
import com.example.demo.utils.ApiJsonObject;
import com.example.demo.utils.ApiJsonProperty;
import com.example.demo.utils.AssertionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "二维码")
public class QrCodeController {
    @Resource
    private QrCodeService qrCodeService;
    @Resource
    private TokenService tokenService;

    @ResponseBody
    @PostMapping()
    @ApiOperation(value = "根据信息创建二维码")
    public Response<String> createQr(@ApiParam(value = "加密验证参数")@RequestHeader("Token") String token,
                                     @ApiJsonObject(name = "map", value = {
                                             @ApiJsonProperty(key = "username", description = "用户名"),
                                             @ApiJsonProperty(key = "checkInId", description = "小签到id"),
                                             @ApiJsonProperty(key = "date", description = "创建时间")
                                     }) @RequestBody HashMap<String, Object> map) {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        String qrCode = qrCodeService.createQr(map);
        if (qrCode != null) {
            return Response.createSuc(qrCode);
        } else {
            return Response.createErr("二维码创建失败!");
        }
    }
}
