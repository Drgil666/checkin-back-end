package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.pojo.vo.Response;
import com.example.demo.utils.AssertionUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @author zxl
 * @date 2021/4/57 8:30
 */
@Controller
@RequestMapping("/api/kaptcha")
@Api(tags = "验证码")
public class KaptchaController {
    /**
     * 1、验证码工具
     */
    @Autowired
    DefaultKaptcha defaultKaptcha;

    @ResponseBody
    @GetMapping
    @ApiOperation(value = "获取验证码")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws Exception {
        byte[] captchaChallengeAsJpeg;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("rightCode", createText);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
     * 3、校对验证码
     *
     * @param httpServletRequest request
     * @return 校验结果
     */
    @ResponseBody
    @PostMapping
    @ApiOperation(value = "验证验证码")
    public Response<String> imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest,
                                                             @ApiParam(value = "尝试的验证码") @RequestParam("tryCode") String tryCode) {
        String rightCode = (String) httpServletRequest.getSession().getAttribute("rightCode");
        AssertionUtil.notNull(rightCode, ErrorCode.BIZ_PARAM_ILLEGAL, "验证码获取失败");
        if (!rightCode.equals(tryCode)) {
            return Response.createErr("验证码错误");
        } else {
            return Response.createSuc(null);
        }
    }
}
