package com.example.demo.controller;

import com.example.demo.annotation.Authorize;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.School;
import com.example.demo.pojo.vo.CudRequestVO;
import com.example.demo.pojo.vo.Response;
import com.example.demo.pojo.vo.ReturnPage;
import com.example.demo.service.SchoolService;
import com.example.demo.service.TokenService;
import com.example.demo.utils.AssertionUtil;
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
 * @author DrGilbert
 * @date 2021/3/23 18:55
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(tags = "学校相关操作")
public class SchoolController {
    @Resource
    private SchoolService schoolService;
    @Resource
    private TokenService tokenService;

    @ResponseBody
    @PostMapping("/school")
    @ApiOperation(value = "创建/更新/删除School")
    @Authorize(value = AuthorizeUtil.Character.TYPE_ROOT)
    public Response<School> school(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                   @ApiParam(value = "包含学校信息，参数信息") @RequestBody CudRequestVO<School, Integer> request) {

        switch (request.getMethod()) {
            case CudRequestVO.CREATE_METHOD: {
                schoolService.createSchool(request.getData());
                if (request.getData().getId() != null) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr("创建学校失败!");
                }
            }
            case CudRequestVO.UPDATE_METHOD: {
                if (schoolService.updateSchool(request.getData()) == 1) {
                    return Response.createSuc(request.getData());
                } else {
                    throw new ErrorException(ErrorCode.BIZ_PARAM_ILLEGAL, "更新失败!");
                }
            }
            case CudRequestVO.DELETE_METHOD: {
                if (schoolService.deleteSchool(request.getKey()) > 0) {
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
    @GetMapping("/school")
    @ApiOperation(value = "获取School")
    @Authorize(value = AuthorizeUtil.Character.TYPE_USER)
    public Response<School> school(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                   @ApiParam(value = "school的Id") @RequestParam("id") Integer id) {

        AssertionUtil.notNull(id, ErrorCode.BIZ_PARAM_ILLEGAL, "id为空!");
        School school = schoolService.getSchool(id);
        if (school != null) {
            return Response.createSuc(school);
        } else {
            return Response.createErr("获取学校失败!");
        }
    }

    @ResponseBody
    @GetMapping("/school/list")
    @ApiOperation(value = "根据学校名获取School列表")
    @Authorize(value = AuthorizeUtil.Character.TYPE_USER)
    public Response<ReturnPage<School>> getSchoolListByKeyword(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                                               @ApiParam(value = "学校名称") @RequestParam(required = false, value = "keyword", defaultValue = "") String keyword,
                                                               @ApiParam(value = "当前页") @RequestParam(required = false, value = "current") Integer current,
                                                               @ApiParam(value = "页大小") @RequestParam(required = false, value = "pageSize") Integer pageSize,
                                                               @ApiParam(value = "排序规则") @RequestParam(required = false, value = "sorter") String sorter) throws Exception {

        AssertionUtil.notNull(keyword, ErrorCode.INNER_PARAM_ILLEGAL, "keyword为空!");
        ListPageUtil.paging(current, pageSize, sorter);
        List<School> schoolList = schoolService.getSchoolListByKeyword(keyword);
        PageInfo<School> pageInfo = new PageInfo<>(schoolList);
        ReturnPage<School> returnPage = ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }

    @ResponseBody
    @GetMapping("/academy/school")
    @ApiOperation(value = "根据学院id查找学校")
    @Authorize(value = AuthorizeUtil.Character.TYPE_USER)
    public Response<School> getSchoolByAcademyId(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                                 @ApiParam(value = "学院id") @RequestParam("id") Integer id) {
        AssertionUtil.notNull(id, ErrorCode.BIZ_PARAM_ILLEGAL, "学院id不能为空!");
        School school = schoolService.getSchoolByAcademyId(id);
        if (school != null) {
            return Response.createSuc(school);
        } else {
            return Response.createErr("查找学校失败!");
        }
    }

    @ResponseBody
    @GetMapping("/major/school")
    @ApiOperation(value = "根据专业id查找学校")
    @Authorize(value = AuthorizeUtil.Character.TYPE_USER)
    public Response<School> getSchoolByMajorId(@ApiParam(value = "加密验证参数") @RequestHeader("Token") String token,
                                               @ApiParam(value = "专业id") @RequestParam("id") Integer id) {

        AssertionUtil.notNull(id, ErrorCode.BIZ_PARAM_ILLEGAL, "专业id不能为空!");
        School school = schoolService.getSchoolByMajorId(id);
        if (school != null) {
            return Response.createSuc(school);
        } else {
            return Response.createErr("查找学校失败!");
        }
    }
}
