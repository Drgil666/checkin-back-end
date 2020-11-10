package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.vo.Response;
import com.example.demo.pojo.Sign;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chentao
 */
@Controller
@Slf4j
@RequestMapping("/api/signin")
public class  SignController {
    @Resource
    private SignService signService;

    @ResponseBody
    @PostMapping() public Response<Sign> sign(@RequestBody CUDRequest<Sign, Integer> request) {
        switch (request.getMethod()) {
            case CUDRequest.CREATE_METHOD: {
                signService.createSign(request.getData());
                if (request.getData().getId() != null) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr("添加签到记录失败!");
                }
            }
            case CUDRequest.UPDATE_METHOD: {
                if (signService.updateSign(request.getData()) == 1) {
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
    @GetMapping("/List")
    public Response<List<Sign>>findBystuId(@RequestParam("stuId") Integer stuId)
    {
        List<Sign> signList=signService.getSignList(stuId);
        if(signList!=null)
        {
            return Response.createSuc(signList);
        }
        else
        {
            return Response.createErr("获取失败!");
        }
    }
    @ResponseBody
    @GetMapping()
    public Response<Sign> sign(@RequestParam("id") Integer id) {
        Sign sign = signService.getSign(id);
        if (sign != null) {
            return Response.createSuc(sign);
        } else {
            return Response.createErr("获取sign信息失败!");
        }
    }
}
