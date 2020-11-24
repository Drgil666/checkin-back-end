package com.example.demo.controller;

import com.example.demo.pojo.vo.Response;
import com.example.demo.service.QrCodeService;
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
@RequestMapping("/api/QrCode")
public class QrCodeController {
    @Resource
    private QrCodeService qrCodeService;

    @ResponseBody
    @PostMapping()
    public Response<String> createQr(@RequestBody HashMap<String, Object> map) {

        String qrCode = qrCodeService.createQr(map);
        if (qrCode != null) {
            return Response.createSuc(qrCode);
        } else {
            return Response.createErr("二维码创建失败!");
        }
    }
}
