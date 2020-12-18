package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.CheckIn;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.CheckInService;
import com.example.demo.service.TokenService;
import com.example.demo.utils.ListPageUtil;
import com.example.demo.utils.ReturnPage;
import com.github.pagehelper.PageInfo;
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
    @GetMapping("/admin/list")
    public Response<ReturnPage<CheckIn>> getCheckInBySetId(@RequestHeader("Token") String token,
                                                           @RequestParam("setId") Integer setId,
                                                           @RequestParam(value = "current", required = false) Integer current,
                                                           @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                           @RequestParam(value = "sorter", required = false) String sorter) throws Exception {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        ListPageUtil.paging(current, pageSize, sorter);
        List<CheckIn> checkInList = checkInService.getCheckInListBySetId(setId);
        PageInfo<CheckIn> pageInfo = new PageInfo<>(checkInList);
        ReturnPage<CheckIn> returnPage = ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }

    @ResponseBody
    @GetMapping("/user/list")
    public Response<ReturnPage<CheckIn>> getCheckInByStu(@RequestHeader("Token") String token,
                                                         @RequestParam("setId") Integer setId,
                                                         @RequestParam(value = "current", required = false) Integer current,
                                                         @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                         @RequestParam(value = "sorter", required = false) String sorter) throws Exception {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        ListPageUtil.paging(current, pageSize, sorter);
        Integer userId = tokenService.getUserIdByToken(token);
        List<CheckIn> checkInList = checkInService.getCheckInListBySetId(setId);
        PageInfo<CheckIn> pageInfo = new PageInfo<>(checkInList);
        ReturnPage<CheckIn> returnPage = ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }

    @ResponseBody
    @GetMapping("/isSign")
    public Response<Boolean> isSign(@RequestHeader("Token") String token, Integer checkId) {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        Integer userId = tokenService.getUserIdByToken(token);
        Boolean isSign = checkInService.isSign(checkId, userId);
        return Response.createSuc(isSign);
    }

    @ResponseBody
    @GetMapping()
    public Response<CheckIn> getCheckIn(@RequestHeader("Token") String token, @RequestParam("checkId") Integer checkInId) {
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
