package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.exception.ErrorCode;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.vo.MailUserVO;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.AdminService;
import com.example.demo.service.BcryptService;
import com.example.demo.service.MailService;
import com.example.demo.service.TokenService;
import com.example.demo.utils.AssertionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Base64;

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
    private AdminService adminService;
    @Resource
    private BcryptService bcryptService;

    @ResponseBody
    @PostMapping()
    public Response<String> createVerification(@ApiParam(value = "加密验证参数") @RequestBody MailUserVO mailVO) {
        String mail = mailVO.getMail();
        Admin admin = adminService.getAdminByMail(mail);
        AssertionUtil.notNull(admin, ErrorCode.BIZ_PARAM_ILLEGAL, "该用户不存在!");
        String code = tokenService.createVerificationCode(admin.getId());
        MailUserVO mailUserVO = new MailUserVO();
        mailUserVO.setAdminId(admin.getId());
        mailUserVO.setUsername(admin.getUsername());
        String json = JSON.toJSONString(mailUserVO);
        Base64.Encoder encoder = Base64.getEncoder();
        json = encoder.encodeToString(json.getBytes());
        mailService.sendSimpleMail(mail, "ZjgsuCheckIn密码找回", "用户Token是" + json + ",验证码是" + code);
        return Response.createSuc(code);
    }

    @ResponseBody
    @GetMapping()
    public Response<String> verify(@ApiParam(value = "加密验证参数") @RequestParam("Token") String token,
                                   @ApiParam(value = "code") @RequestParam("code") String code) {
        Base64.Decoder decoder = Base64.getDecoder();
        token = new String(decoder.decode(token.getBytes()));
        MailUserVO mailUserVO = JSON.parseObject(token, MailUserVO.class);
        AssertionUtil.notNull(mailUserVO, ErrorCode.BIZ_PARAM_ILLEGAL, "请求不合法!");
        if (tokenService.checkVerificationCode(mailUserVO, code)) {
            return Response.createSuc(null);
        } else {
            return Response.createErr("验证码错误!");
        }
    }
}
