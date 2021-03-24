package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.CheckSet;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.pojo.vo.Response;
import com.example.demo.pojo.vo.ReturnPage;
import com.example.demo.service.CheckSetService;
import com.example.demo.service.TokenService;
import com.example.demo.utils.AssertionUtil;
import com.example.demo.utils.ListPageUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "大签到")
public class CheckSetController {
    @Resource
    private CheckSetService checkSetService;
    @Resource
    private TokenService tokenService;

    @ResponseBody
    @PostMapping()
    public Response<CheckSet> checkSet(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                       @ApiParam(value = "包含大签到信息，操作信息") @RequestBody CUDRequest<CheckSet, Integer> request) {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        Integer userId = tokenService.getUserIdByToken(token);
        request.getData().setUserId(userId);
        switch (request.getMethod()) {
            case CUDRequest.CREATE_METHOD: {
                checkSetService.createCheckSet(request.getData());
                AssertionUtil.notNull(request.getData().getId(), ErrorCode.INNER_PARAM_ILLEGAL, "添加签到记录失败!");
                return Response.createSuc(request.getData());
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
                return Response.createErr(CUDRequest.METHOD_ERROR);
            }
        }
    }

    @ResponseBody
    @GetMapping()
    @ApiOperation(value = "根据CheckSetId获取CheckSet")
    public Response<CheckSet> getCheckSet(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                          @ApiParam(value = "大签到id") @RequestParam("checkSetId") Integer checkSetId) {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        CheckSet checkset = checkSetService.getCheckSet(checkSetId);
        if (checkset != null) {
            return Response.createSuc(checkset);
        } else {
            return Response.createErr("获取失败!");
        }
    }

    @ResponseBody
    @GetMapping("/admin/list")
    @ApiOperation(value = "教师获取自己发起的签到")
    public Response<ReturnPage<CheckSet>> getCheckSetByNick(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                                            @ApiParam(value = "昵称") @RequestParam(value = "nick", required = false, defaultValue = "") String nick,
                                                            @ApiParam(value = "当前页面") @RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
                                                            @ApiParam(value = "页面大小") @RequestParam(value = "pageSize", required = false, defaultValue = "2") Integer pageSize,
                                                            @ApiParam(value = "排序方式") @RequestParam(value = "sorter", required = false) String sorter) throws Exception {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        ListPageUtil.paging(current, pageSize, sorter);
        List<CheckSet> checkSetList = checkSetService.getCheckSetListByNickAdmin(nick);
        PageInfo<CheckSet> pageInfo = new PageInfo<>(checkSetList);
        ReturnPage<CheckSet> returnPage = ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }

    @ResponseBody
    @GetMapping("/teacher/list")
    @ApiOperation(value = "通过用户id获取签到信息")
    public Response<ReturnPage<CheckSet>> getCheckSetByUserId(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                                              @ApiParam(value = "用户id") @RequestParam(value = "userId", required = false) Integer userId,
                                                              @ApiParam(value = "昵称") @RequestParam(value = "nick", required = false, defaultValue = "") String nick,
                                                              @ApiParam(value = "当前页面") @RequestParam(value = "current", required = false) Integer current,
                                                              @ApiParam(value = "页面大小") @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                              @ApiParam(value = "排序方式") @RequestParam(value = "sorter", required = false) String sorter) throws Exception {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        if (userId == null) {
            userId = tokenService.getUserIdByToken(token);
        }
        ListPageUtil.paging(current, pageSize, sorter);
        List<CheckSet> checkSetList = checkSetService.getCheckSetListByTeacher(userId, nick);
        PageInfo<CheckSet> pageInfo = new PageInfo<>(checkSetList);
        ReturnPage<CheckSet> returnPage = ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }

    @ResponseBody
    @GetMapping("/stu/list")
    @ApiOperation(value = "学生获得自己完成的大签到")
    public Response<ReturnPage<CheckSet>> getCheckSetBySign(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                                            @ApiParam(value = "当前页面") @RequestParam(value = "current", required = false) Integer current,
                                                            @ApiParam(value = "页面大小") @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                            @ApiParam(value = "排序方式") @RequestParam(value = "sorter", required = false) String sorter) throws Exception {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        Integer stuId = tokenService.getUserIdByToken(token);
        ListPageUtil.paging(current, pageSize, sorter);
        List<CheckSet> checkSetList = checkSetService.getCheckListByStu(stuId);
        PageInfo<CheckSet> pageInfo = new PageInfo<>(checkSetList);
        ReturnPage<CheckSet> returnPage = ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }
}
