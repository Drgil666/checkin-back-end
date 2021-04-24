package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.Sign;
import com.example.demo.pojo.User;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.pojo.vo.Response;
import com.example.demo.pojo.vo.ReturnPage;
import com.example.demo.service.SignService;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
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
@RequestMapping("/api/sign")
@Api(tags = "签到记录")
public class SignController {
    @Resource
    private SignService signService;
    @Resource
    private TokenService tokenService;
    @Resource
    private UserService userService;

    @ResponseBody
    @PostMapping()
    @ApiOperation(value = "创建/更新/删除签到记录")
    public Response<Sign> sign(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                               @ApiParam(value = "包含签到记录信息，操作信息") @RequestBody CUDRequest<Sign, Integer> request) {
        if (!tokenService.loginCheck(token)) {
            return Response.createTokenAuthorizedErr();
        }
        Integer stuId = tokenService.getUserIdByToken(token);
        switch (request.getMethod()) {
            case CUDRequest.CREATE_METHOD: {
                User user = userService.getUser(stuId);
                request.getData().setStuId(stuId);
                request.getData().setNick(user.getNick());
                request.getData().setStuNo(user.getStuNo());
                signService.createSign(request.getData());
                if (request.getData().getId() != null) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr("添加签到记录失败!");
                }
            }
            case CUDRequest.UPDATE_METHOD: {
                User user = userService.getUser(stuId);
                request.getData().setStuId(stuId);
                request.getData().setNick(user.getNick());
                request.getData().setStuNo(user.getStuNo());
                if (signService.updateSign(request.getData()) == 1) {
                    return Response.createSuc(request.getData());
                } else {
                    throw new ErrorException(ErrorCode.BIZ_PARAM_ILLEGAL, "更新失败!");
                }
            }
            case CUDRequest.DELETE_METHOD: {
                if (signService.deleteSign(request.getKey()) > 0) {
                    return Response.createSuc(null);
                } else {
                    return Response.createErr("删除失败!");
                }
            }
            default: {
                return Response.createErr(CUDRequest.METHOD_ERROR);
            }
        }
    }

    @ResponseBody
    @GetMapping()
    @ApiOperation(value = "根据签到记录id查找对应的签到记录")
    public Response<Sign> sign(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                               @ApiParam(value = "签到记录信息id") @RequestParam("id") Integer id) {
        if (!tokenService.loginCheck(token)) {
            return Response.createTokenAuthorizedErr();
        }
        Sign sign = signService.getSign(id);
        if (sign != null) {
            return Response.createSuc(sign);
        } else {
            return Response.createErr("签到不存在!");
        }
    }

    @ResponseBody
    @GetMapping("/checkId")
    @ApiOperation(value = "通过小签到id查找对应的签到记录")
    public Response<ReturnPage<Sign>> getSignByCheckId(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                                       @ApiParam(value = "小签到id") @RequestParam("checkId") Integer checkId,
                                                       @ApiParam(value = "当前页面") @RequestParam(value = "current", required = false) Integer current,
                                                       @ApiParam(value = "页面大小") @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                       @ApiParam(value = "排序方式") @RequestParam(value = "sorter", required = false) String sorter) throws Exception {
        if (!tokenService.loginCheck(token)) {
            return Response.createTokenAuthorizedErr();
        }
        ListPageUtil.paging(current, pageSize, sorter);
        List<Sign> signVOList = signService.getSignByCheckId(checkId);
        PageInfo<Sign> pageInfo = new PageInfo<>(signVOList);
        ReturnPage<Sign> returnPage = ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }

    @ResponseBody
    @GetMapping("/checkId/userId")
    @ApiOperation(value = "通过小签到id和用户id查找签到记录")
    public Response<Sign> getSignByCheckIdAndUserId(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                                    @ApiParam(value = "小签到id") @RequestParam("checkId") Integer checkId) {
        if (!tokenService.loginCheck(token)) {
            return Response.createTokenAuthorizedErr();
        }
        Integer userId = tokenService.getUserIdByToken(token);
        Sign sign = signService.getSignByCheckIdAndUserId(checkId, userId);
        if (sign != null) {
            return Response.createSuc(sign);
        } else {
            return Response.createErr("获取签到信息失败!");
        }
    }

    @ResponseBody
    @GetMapping("/userId")
    @ApiOperation(value = "通过用户id查找签到记录")
    public Response<ReturnPage<Sign>> getSignByUserId(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                                      @ApiParam(value = "用户id") @RequestParam("userId") Integer userId,
                                                      @ApiParam(value = "当前页面") @RequestParam(value = "current", required = false) Integer current,
                                                      @ApiParam(value = "页面大小") @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                      @ApiParam(value = "排序方式") @RequestParam(value = "sorter", required = false) String sorter) throws Exception {
        if (!tokenService.loginCheck(token)) {
            return Response.createTokenAuthorizedErr();
        }
        if (userId == null) {
            userId = tokenService.getUserIdByToken(token);
        }
        if (userId == null) {
            return Response.createErr("获取userId失败!userId为空");
        }
        ListPageUtil.paging(current, pageSize, sorter);
        List<Sign> signList = signService.getSignList(userId);
        PageInfo<Sign> pageInfo = new PageInfo<>(signList);
        ReturnPage<Sign> returnPage = ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }
}
