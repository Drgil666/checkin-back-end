package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.CheckIn;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.CheckInService;
import com.example.demo.service.TokenService;
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
@CrossOrigin(origins = "*")
@RequestMapping("/api/checkin")
public class CheckInController {
    @Resource
    private CheckInService checkInService;
    @Resource
    private TokenService tokenService;

    @ResponseBody
    @PostMapping()
    public Response<CheckIn> checkin(@RequestHeader("Token") String token, @RequestBody CUDRequest<CheckIn, Integer> request) {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
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
            case CUDRequest.DELETE_METHOD: {
                if (checkInService.deleteCheckIn(request.getKey()) > 0) {
                    return Response.createSuc(null);
                } else {
                    return Response.createErr("删除失败!");
                }
            }
            default: {
                return Response.createErr("method错误!");
            }
        }
    }

    @ResponseBody
    @GetMapping("/List")
    public Response<List<CheckIn>> getcheckInBySetId(@RequestHeader("Token") String token, @RequestParam("setId") Integer setId) {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        List<CheckIn> checkInList = checkInService.getCheckInListBySetId(setId);
        if (checkInList != null) {
            return Response.createSuc(checkInList);
        } else {
            return Response.createErr("获取失败!");
        }
    }
    @ResponseBody
    @GetMapping("/ifSign")
    public Response<Object> ifSign(@RequestHeader("Token") String token, Integer checkId) {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        boolean ifsign=checkInService.ifSign(checkId,token);
        if (ifsign ) {
            return Response.createSuc("签到成功!");
        } else {
            return Response.createErr("签到失败!已经签到过!");
        }
    }

    @ResponseBody
    @GetMapping()
    public Response<CheckIn> getcheckIn(@RequestHeader("Token") String token, @RequestParam("checkId") Integer checkInId) {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        CheckIn checkIn = checkInService.getCheckIn(checkInId);
        if (checkIn != null) {
            return Response.createSuc(checkIn);
        } else {
            return Response.createErr("获取失败!");
        }
    }

}
