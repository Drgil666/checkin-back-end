package com.example.demo.controller;

import com.example.demo.pojo.vo.Response;
import com.example.demo.service.QrCodeService;
import com.example.demo.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    public Response<String> createQr(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                     @ApiParam(value = "二维码相关信息") @RequestBody String vo) {
        if (!tokenService.loginCheck(token)) {
            return Response.createTokenAuthorizedErr();
        }
        String qrCode = qrCodeService.createQr(vo);
        if (qrCode != null) {
            return Response.createSuc(qrCode);
        } else {
            return Response.createErr("二维码创建失败!");
        }
    }
}
