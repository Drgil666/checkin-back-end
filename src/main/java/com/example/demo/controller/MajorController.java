package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.Major;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.pojo.vo.Response;
import com.example.demo.pojo.vo.ReturnPage;
import com.example.demo.service.MajorService;
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
 * @author DrGilbert
 * @date 2021/3/23 20:02
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(tags = "专业操作")
public class MajorController {
    @Resource
    private TokenService tokenService;
    @Resource
    private MajorService majorService;

    @ResponseBody
    @PostMapping("/major")
    @ApiOperation(value = "创建/更新/删除Academy")
    public Response<Major> major(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                 @ApiParam(value = "包含专业信息，参数信息") @RequestBody CUDRequest<Major, Integer> request) {
        if (!tokenService.loginCheck(token)) {
            return Response.createTokenAuthorizedErr();
        }
        switch (request.getMethod()) {
            case CUDRequest.CREATE_METHOD: {
                majorService.createMajor(request.getData());
                if (request.getData().getId() != null) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr("创建学校失败!");
                }
            }
            case CUDRequest.UPDATE_METHOD: {
                if (majorService.updateMajor(request.getData()) == 1) {
                    return Response.createSuc(request.getData());
                } else {
                    throw new ErrorException(ErrorCode.BIZ_PARAM_ILLEGAL, "更新失败!");
                }
            }
            case CUDRequest.DELETE_METHOD: {
                if (majorService.deleteMajor(request.getKey()) > 0) {
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
    @GetMapping("/major")
    @ApiOperation(value = "获取major")
    public Response<Major> school(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                  @ApiParam(value = "major的Id") @RequestParam("id") Integer id) {
        if (!tokenService.loginCheck(token)) {
            return Response.createTokenAuthorizedErr();
        }
        AssertionUtil.notNull(id, ErrorCode.BIZ_PARAM_ILLEGAL, "id为空!");
        Major major = majorService.getMajor(id);
        if (major != null) {
            return Response.createSuc(major);
        } else {
            return Response.createErr("获取学校失败!");
        }
    }

    @ResponseBody
    @GetMapping("/major/academy/list")
    @ApiOperation(value = "根据学院id获取Major列表")
    public Response<ReturnPage<Major>> getAcademyListByAcademyId(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                                                 @ApiParam(value = "学院id") @RequestParam("id") Integer id,
                                                                 @ApiParam(value = "学院名") @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
                                                                 @ApiParam(value = "当前页") @RequestParam(required = false, value = "current") Integer current,
                                                                 @ApiParam(value = "页大小") @RequestParam(required = false, value = "pageSize") Integer pageSize,
                                                                 @ApiParam(value = "排序规则") @RequestParam(required = false, value = "sorter") String sorter) throws Exception {
        if (!tokenService.loginCheck(token)) {
            return Response.createTokenAuthorizedErr();
        }
        AssertionUtil.notNull(id, ErrorCode.INNER_PARAM_ILLEGAL, "keyword为空!");
        ListPageUtil.paging(current, pageSize, sorter);
        List<Major> academyList = majorService.getMajorListByAcademyIdAndKeyWord(id, keyword);
        PageInfo<Major> pageInfo = new PageInfo<>(academyList);
        ReturnPage<Major> returnPage = ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }

}
