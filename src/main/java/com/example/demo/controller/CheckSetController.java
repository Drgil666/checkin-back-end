package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.CheckSet;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.CheckSetService;
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
@RequestMapping("/api/checkSet")
public class CheckSetController {
    @Resource
    private CheckSetService checkSetService;
    @Resource
    private TokenService tokenService;
    @ResponseBody
    @PostMapping()
    public Response<CheckSet> checkSet(@RequestHeader("Token") String token,@RequestBody CUDRequest<CheckSet, Integer> request) {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        switch (request.getMethod()) {
            case CUDRequest.CREATE_METHOD: {
                checkSetService.createCheckSet(request.getData());
                if (request.getData().getId() != null) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr("添加签到记录失败!");
                }
            }
            case CUDRequest.UPDATE_METHOD: {
                if (checkSetService.updateCheckSet(request.getData()) == 1) {
                    return Response.createSuc(request.getData());
                } else {
                    throw new ErrorException(ErrorCode.BIZ_PARAM_ILLEGAL, "更新失败!");
                }
            }
            case CUDRequest.DELETE_METHOD: {
                if (checkSetService.deleteCheckSet(request.getKey()) > 0) {
                    return Response.createSuc(null);
                } else {
                    Response.createErr("删除失败!");
                }
            }
            default: {
                return Response.createErr("method错误!");
            }
        }
    }


    @ResponseBody
    @GetMapping()
    public Response<CheckSet> getCheckSet(@RequestHeader("Token") String token,@RequestParam("checkSetId") Integer checkSetId) {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        CheckSet checkset = checkSetService.getCheckSet(checkSetId);
        if (checkset != null) {
            return Response.createSuc(checkset);
        } else {
            return Response.createErr("获取失败!");
        }
    }

    @ResponseBody
    @GetMapping("/findByNick")
    public Response<List<CheckSet>> getCheckSetByNick(@RequestHeader("Token") String token,@RequestParam("nick") String nick) {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        List<CheckSet> checkSetList = checkSetService.getCheckSetNick(nick);
        if (checkSetList != null) {
            return Response.createSuc(checkSetList);
        } else {
            return Response.createErr("获取失败!");
        }
    }

    @ResponseBody
    @GetMapping("/findByUserId")
    public Response<List<CheckSet>> getcheckSetByUserId(@RequestHeader("Token") String token) {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        Integer userId = tokenService.getUserIdByToken(token);
        List<CheckSet> checkSetList = checkSetService.getCheckSetList(userId);
        if(userId==null){
            return Response.createErr("获取userId失败!userId为空");
        }
        else {
            if (checkSetList != null) {
                return Response.createSuc(checkSetList);
            } else {
                return Response.createErr("获取失败!");
            }
        }
    }
}
