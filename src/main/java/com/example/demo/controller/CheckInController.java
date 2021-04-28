package com.example.demo.controller;

import com.example.demo.annotation.Authorize;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.CheckIn;
import com.example.demo.pojo.vo.CudRequestVO;
import com.example.demo.pojo.vo.Response;
import com.example.demo.pojo.vo.ReturnPage;
import com.example.demo.service.CheckInService;
import com.example.demo.service.TokenService;
import com.example.demo.utils.AuthorizeUtil;
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
@RequestMapping("/api/checkin")
@Api(tags = "小签到")
public class CheckInController {
    @Resource
    private CheckInService checkInService;
    @Resource
    private TokenService tokenService;

    @ResponseBody
    @PostMapping()
    @ApiOperation(value = "创建/更新小签到")
    @Authorize(value = AuthorizeUtil.Character.TYPE_USER)
    public Response<CheckIn> checkin(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                     @ApiParam(value = "包含小签到具体信息，操作信息") @RequestBody CudRequestVO<CheckIn, Integer> request) {

        switch (request.getMethod()) {
            case CudRequestVO.CREATE_METHOD: {
                checkInService.createCheckIn(request.getData());
                if (request.getData().getId() != null) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr("添加签到环节失败!");
                }
            }
            case CudRequestVO.UPDATE_METHOD: {
                if (checkInService.updateCheckIn(request.getData()) == 1) {
                    return Response.createSuc(request.getData());
                } else {
                    throw new ErrorException(ErrorCode.BIZ_PARAM_ILLEGAL, "更新签到环节失败!");
                }
            }
            case CudRequestVO.DELETE_METHOD: {
                if (checkInService.deleteCheckIn(request.getKey()) > 0) {
                    return Response.createSuc(null);
                } else {
                    return Response.createErr("删除失败!");
                }
            }
            default: {
                return Response.createErr(CudRequestVO.METHOD_ERROR);
            }
        }
    }

    @ResponseBody
    @GetMapping("/teacher/list")
    @ApiOperation(value = "根据大签到id获取所有对应的小签到信息")
    @Authorize(value = AuthorizeUtil.Character.TYPE_USER)
    public Response<ReturnPage<CheckIn>> getCheckInBySetId(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                                           @ApiParam(value = "大签到id") @RequestParam("setId") Integer setId,
                                                           @ApiParam(value = "当前页面") @RequestParam(value = "current", required = false) Integer current,
                                                           @ApiParam(value = "页面大小") @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                           @ApiParam(value = "排序方式") @RequestParam(value = "sorter", required = false) String sorter) throws Exception {

        ListPageUtil.paging(current, pageSize, sorter);
        List<CheckIn> checkInList = checkInService.getCheckInListBySetId(setId);
        PageInfo<CheckIn> pageInfo = new PageInfo<>(checkInList);
        ReturnPage<CheckIn> returnPage = ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }

    @ResponseBody
    @GetMapping("/stu/list")
    @ApiOperation(value = "根据小签到id和当前用户id判断学生是否已签到")
    @Authorize(value = AuthorizeUtil.Character.TYPE_USER)
    public Response<ReturnPage<CheckIn>> getCheckInByStu(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                                         @ApiParam(value = "大签到id") @RequestParam("setId") Integer setId,
                                                         @ApiParam(value = "当前页面") @RequestParam(value = "current", required = false) Integer current,
                                                         @ApiParam(value = "页面大小") @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                         @ApiParam(value = "排序方式") @RequestParam(value = "sorter", required = false) String sorter) throws Exception {

        ListPageUtil.paging(current, pageSize, sorter);
        Integer userId = tokenService.getUserIdByToken(token);
        List<CheckIn> checkInList = checkInService.getCheckInListByStu(setId, userId);
        PageInfo<CheckIn> pageInfo = new PageInfo<>(checkInList);
        ReturnPage<CheckIn> returnPage = ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }

    @ResponseBody
    @GetMapping("/isSign")
    @ApiOperation(value = "根据小签到id和当前用户id判断学生是否已签到")
    @Authorize(value = AuthorizeUtil.Character.TYPE_USER)
    public Response<Boolean> isSign(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                    @ApiParam(value = "小签到id") @RequestParam("checkId") Integer checkId) {

        Integer userId = tokenService.getUserIdByToken(token);
        Boolean isSign = checkInService.isSign(userId, checkId);
        return Response.createSuc(isSign);
    }

    @ResponseBody
    @GetMapping()
    @ApiOperation(value = "通过小签到id获取具体签到信息")
    @Authorize(value = AuthorizeUtil.Character.TYPE_USER)
    public Response<CheckIn> getCheckIn(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                        @ApiParam(value = "小签到id") @RequestParam("checkId") Integer checkInId) {

        CheckIn checkIn = checkInService.getCheckIn(checkInId);
        if (checkIn != null) {
            return Response.createSuc(checkIn);
        } else {
            return Response.createErr("获取失败!");
        }
    }
}
