package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.vo.MailUserVO;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.AdminService;
import com.example.demo.service.MailService;
import com.example.demo.service.TokenService;
import com.example.demo.utils.AssertionUtil;
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
    private AdminService adminService;

    @ResponseBody
    @PostMapping()
    public Response<String> createVerification(@ApiParam(value = "加密验证参数") @RequestBody MailUserVO mailVO) {
        String mail = mailVO.getMail();
        Admin admin = adminService.getAdminByMail(mail);
        AssertionUtil.notNull(admin, ErrorCode.BIZ_PARAM_ILLEGAL, "该用户不存在!");
        String code = tokenService.createVerificationCode(admin.getId());
        mailService.sendSimpleMail(mail, "ZjgsuCheckIn密码找回", "验证码是" + code);
        return Response.createSuc(code);
    }

    @ResponseBody
    @GetMapping()
    public Response<String> verify(@ApiParam(value = "加密验证参数") @RequestParam("mail") String mail,
                                   @ApiParam(value = "code") @RequestParam("code") String code) {
        AssertionUtil.notNull(mail, ErrorCode.BIZ_PARAM_ILLEGAL, "邮箱不能为空!");
        Admin admin = adminService.getAdminByMail(mail);
        AssertionUtil.notNull(admin, ErrorCode.BIZ_PARAM_ILLEGAL, "请求不合法!");
        if (tokenService.checkVerificationCode(admin.getId(), code)) {
            return Response.createSuc(null);
        } else {
            return Response.createErr("验证码错误!");
        }
    }
}
