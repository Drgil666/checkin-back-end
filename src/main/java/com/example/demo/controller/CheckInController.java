package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.CheckIn;
import com.example.demo.pojo.Response;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.service.CheckInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author chentao
 */
@Controller
@Slf4j
@RequestMapping("/api/checkin")
public class CheckInController {
    @Resource
    private CheckInService checkInService;

    @ResponseBody
    @PostMapping()
    public Response<CheckIn> checkin(@RequestBody CUDRequest<CheckIn, Integer> request) {
        switch (request.getMethod()) {
            case CUDRequest.CREATE_METHOD: {
                checkInService.createCheckIn(request.getData());
                if (request.getData().getId() != null) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr("添加签到记录失败!");
                }
            }
            case CUDRequest.UPDATE_METHOD: {
                if (checkInService.updateCheckIn(request.getData()) == 1) {
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

}
