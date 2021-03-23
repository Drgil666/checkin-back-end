package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.Academy;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.pojo.vo.Response;
import com.example.demo.pojo.vo.ReturnPage;
import com.example.demo.service.AcademyService;
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
 * @date 2021/3/23 19:39
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(tags = "学院操作")
public class AcademyController {
    @Resource
    private TokenService tokenService;
    @Resource
    private AcademyService academyService;

    @ResponseBody
    @PostMapping("/academy")
    @ApiOperation(value = "创建/更新/删除Academy")
    public Response<Academy> academy(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                     @ApiParam(value = "包含学院信息，参数信息") @RequestBody CUDRequest<Academy, Integer> request) {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        switch (request.getMethod()) {
            case CUDRequest.CREATE_METHOD: {
                academyService.createAcademy(request.getData());
                if (request.getData().getId() != null) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr("创建学校失败!");
                }
            }
            case CUDRequest.UPDATE_METHOD: {
                if (academyService.updateAcademy(request.getData()) == 1) {
                    return Response.createSuc(request.getData());
                } else {
                    throw new ErrorException(ErrorCode.BIZ_PARAM_ILLEGAL, "更新失败!");
                }
            }
            case CUDRequest.DELETE_METHOD: {
                if (academyService.deleteAcademy(request.getKey()) > 0) {
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
    @GetMapping("/academy")
    @ApiOperation(value = "获取Academy")
    public Response<Academy> school(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                    @ApiParam(value = "academy的Id") @RequestParam("id") Integer id) {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        AssertionUtil.notNull(id, ErrorCode.BIZ_PARAM_ILLEGAL, "id为空!");
        Academy academy = academyService.getAcademy(id);
        if (academy != null) {
            return Response.createSuc(academy);
        } else {
            return Response.createErr("获取学校失败!");
        }
    }

    @ResponseBody
    @GetMapping("/academy/list")
    @ApiOperation(value = "根据学院名获取Academy列表")
    public Response<ReturnPage<Academy>> getAcademyListByKeyword(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                                                 @ApiParam(value = "学院名称") @RequestParam("keyword") String keyword,
                                                                 @ApiParam(value = "当前页") @RequestParam("current") Integer current,
                                                                 @ApiParam(value = "页大小") @RequestParam("pageSize") Integer pageSize,
                                                                 @ApiParam(value = "排序规则") @RequestParam("sorter") String sorter) throws Exception {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        AssertionUtil.notNull(keyword, ErrorCode.INNER_PARAM_ILLEGAL, "keyword为空!");
        ListPageUtil.paging(current, pageSize, sorter);
        List<Academy> academyList = academyService.getAcademyListByKeyword(keyword);
        PageInfo<Academy> pageInfo = new PageInfo<>(academyList);
        ReturnPage<Academy> returnPage = ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }

    @ResponseBody
    @GetMapping("/academy/list")
    @ApiOperation(value = "根据专业id获取学院")
    public Response<Academy> getAcademyByMajorId(@RequestHeader("Token") String token,
                                                 @RequestParam("id") Integer id) {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        AssertionUtil.notNull(id, ErrorCode.BIZ_PARAM_ILLEGAL, "id为空!");
        Academy academy = academyService.getAcademyByMajorId(id);
        if (academy != null) {
            return Response.createSuc(academy);
        } else {
            return Response.createErr("获取学院失败!");
        }
    }

    @ResponseBody
    @GetMapping("/academy/school/list")
    @ApiOperation(value = "根据学校id获取Academy列表")
    public Response<ReturnPage<Academy>> getAcademyListBySchoolId(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                                                  @ApiParam(value = "学院id") @RequestParam("id") Integer id,
                                                                  @ApiParam(value = "当前页") @RequestParam("current") Integer current,
                                                                  @ApiParam(value = "页大小") @RequestParam("pageSize") Integer pageSize,
                                                                  @ApiParam(value = "排序规则") @RequestParam("sorter") String sorter) throws Exception {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        AssertionUtil.notNull(id, ErrorCode.INNER_PARAM_ILLEGAL, "id为空!");
        ListPageUtil.paging(current, pageSize, sorter);
        List<Academy> academyList = academyService.getAcademyListBySchoolId(id);
        PageInfo<Academy> pageInfo = new PageInfo<>(academyList);
        ReturnPage<Academy> returnPage = ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }
}
