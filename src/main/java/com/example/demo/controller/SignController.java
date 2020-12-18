package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.Sign;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.pojo.vo.Response;
import com.example.demo.pojo.vo.SignVO;
import com.example.demo.service.SignService;
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
@RequestMapping("/api/sign")
public class SignController {
    @Resource
    private SignService signService;
    @Resource
    private TokenService tokenService;

    @ResponseBody
    @PostMapping()
    public Response<Sign> sign(@RequestHeader("Token") String token, @RequestBody CUDRequest<Sign, Integer> request) {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        Integer stuId = tokenService.getUserIdByToken(token);
        switch (request.getMethod()) {
            case CUDRequest.CREATE_METHOD: {
                request.getData().setStuId(stuId);
                signService.createSign(request.getData());
                if (request.getData().getId() != null) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr("添加签到记录失败!");
                }
            }
            case CUDRequest.UPDATE_METHOD: {
                request.getData().setStuId(stuId);
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
                return Response.createErr("method错误!");
            }
        }
    }

    @ResponseBody
    @GetMapping("/checkId")
    public Response<ReturnPage<SignVO>> getSignByCheckId(@RequestHeader("Token") String token,
                                                         @RequestParam("checkId") Integer checkId,
                                                         @RequestParam(value = "current", required = false) Integer current,
                                                         @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                         @RequestParam(value = "sorter", required = false) String sorter) throws Exception {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        ListPageUtil.paging(current, pageSize, sorter);
        List<SignVO> signVOList = signService.getSignByCheckId(checkId);
        PageInfo<SignVO> pageInfo=new PageInfo<>(signVOList);
        ReturnPage<SignVO> returnPage=ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }

    @ResponseBody
    @GetMapping("/checkId/userId")
    public Response<ReturnPage<SignVO>> getSignByCheckIdAndUserId(@RequestHeader("Token") String token,
                                                            @RequestParam("checkId") Integer checkId,
                                                            @RequestParam(value = "current", required = false) Integer current,
                                                            @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                            @RequestParam(value = "sorter", required = false) String sorter) throws Exception {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        ListPageUtil.paging(current, pageSize, sorter);
        List<SignVO> signVOList = signService.getSignByCheckId(checkId);
        PageInfo<SignVO> pageInfo=new PageInfo<>(signVOList);
        ReturnPage<SignVO> returnPage=ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }
}
