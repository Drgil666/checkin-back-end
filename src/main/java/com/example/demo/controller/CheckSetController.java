package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.CheckSet;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.pojo.vo.Response;
import com.example.demo.pojo.vo.ReturnPage;
import com.example.demo.service.CheckSetService;
import com.example.demo.service.TokenService;
import com.example.demo.utils.ListPageUtil;
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
@RequestMapping("/api/checkSet")
public class CheckSetController {
    @Resource
    private CheckSetService checkSetService;
    @Resource
    private TokenService tokenService;

    @ResponseBody
    @PostMapping()
    public Response<CheckSet> checkSet(@RequestHeader("Token") String token, @RequestBody CUDRequest<CheckSet, Integer> request) {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        Integer userId = tokenService.getUserIdByToken(token);
        request.getData().setUserId(userId);
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
    public Response<CheckSet> getCheckSet(@RequestHeader("Token") String token, @RequestParam("checkSetId") Integer checkSetId) {
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
    @GetMapping("/admin/list")
    public Response<ReturnPage<CheckSet>> getCheckSetByNick(@RequestHeader("Token") String token,
                                                              @RequestParam(value = "nick") String nick,
                                                              @RequestParam(value = "current", required = false,defaultValue="1") Integer current,
                                                              @RequestParam(value = "pageSize", required = false,defaultValue="2") Integer pageSize,
                                                              @RequestParam(value = "sorter", required = false) String sorter) throws Exception {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        ListPageUtil.paging(current, pageSize, sorter);
        List<CheckSet> checkSetList = checkSetService.getCheckSetListByNickAdmin(nick);
        PageInfo<CheckSet> pageInfo = new PageInfo<>(checkSetList);
        ReturnPage<CheckSet> returnPage = ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }

    @ResponseBody
    @GetMapping("/teacher/list")
    public Response<ReturnPage<CheckSet>> getCheckSetByUserId(@RequestHeader("Token") String token,
                                                              @RequestParam(value = "nick", required = false) String nick,
                                                              @RequestParam(value = "current", required = false) Integer current,
                                                              @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                              @RequestParam(value = "sorter", required = false) String sorter) throws Exception {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        Integer userId = tokenService.getUserIdByToken(token);
        if (userId == null) {
            return Response.createErr("获取userId失败!userId为空");
        }
        ListPageUtil.paging(current, pageSize, sorter);
        List<CheckSet> checkSetList = checkSetService.getCheckSetListByTeacher(userId, nick);
        PageInfo<CheckSet> pageInfo = new PageInfo<>(checkSetList);
        ReturnPage<CheckSet> returnPage = ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }

    @ResponseBody
    @GetMapping("/stu/list")
    public Response<ReturnPage<CheckSet>> getCheckSetBySign(@RequestHeader("Token") String token,
                                                            @RequestParam(value = "current", required = false) Integer current,
                                                            @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                            @RequestParam(value = "sorter", required = false) String sorter) throws Exception {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        Integer stuId = tokenService.getUserIdByToken(token);
        ListPageUtil.paging(current, pageSize, sorter);
        List<CheckSet> checkSetList = checkSetService.getCheckListByStu(stuId);
        PageInfo<CheckSet> pageInfo = new PageInfo<>(checkSetList);
        ReturnPage<CheckSet> returnPage = ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }
}
