package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.CheckSet;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.CheckSetService;
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

    @ResponseBody
    @PostMapping()
    public Response<CheckSet> checkSet(@RequestBody CUDRequest<CheckSet, Integer> request) {
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
            default: {
                return Response.createErr("method错误!");
            }
        }
    }


    @ResponseBody
    @GetMapping()
    public Response<CheckSet> getCheckSet(@RequestParam("checkSetId") Integer checkSetId) {
        CheckSet checkset = checkSetService.getCheckSet(checkSetId);
        if (checkset != null) {
            return Response.createSuc(checkset);
        } else {
            return Response.createErr("获取失败!");
        }
    }

    @ResponseBody
    @GetMapping("/findByNick")
    public Response<List<CheckSet>> getCheckSetByNick(@RequestParam("nick") String nick) {
        List<CheckSet> checkSetList = checkSetService.getCheckSetNick(nick);
        if (checkSetList != null) {
            return Response.createSuc(checkSetList);
        } else {
            return Response.createErr("获取失败!");
        }
    }

    @ResponseBody
    @GetMapping("/findByUserId")
    public Response<List<CheckSet>> getcheckSetByUserId(@RequestParam("userId") Integer userId) {
        List<CheckSet> checkSetList = checkSetService.getCheckSetList(userId);
        if (checkSetList != null) {
            return Response.createSuc(checkSetList);
        } else {
            return Response.createErr("获取失败!");
        }
    }
}
