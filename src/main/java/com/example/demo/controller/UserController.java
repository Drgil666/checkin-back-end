package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.User;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.pojo.vo.Response;
import com.example.demo.pojo.vo.ReturnPage;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
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
 * @author Gilbert
 * @Date 2020/9/24 16:28
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
@Api(tags = "用户")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private TokenService tokenService;

    @ResponseBody
    @PostMapping()
    @ApiOperation(value = "创建/更新/删除用户")
    public Response<User> user(@ApiParam(value = "加密验证参数")@RequestHeader("Token") String token,
                               @ApiParam(value = "包含用户信息，操作信息")@RequestBody CUDRequest<User, Integer> request) {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        switch (request.getMethod()) {
            case CUDRequest.CREATE_METHOD: {
                if (userService.isExist(request.getData().getUsername()) != null) {
                    userService.createUser(request.getData());
                    if (request.getData().getId() != null) {
                        return Response.createSuc(request.getData());
                    } else {
                        return Response.createErr("创建用户失败!");
                    }
                } else {
                    return Response.createErr("用户已存在或数据有误!");
                }
            }
            case CUDRequest.UPDATE_METHOD: {
                if (userService.updateUser(request.getData()) == 1) {
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
    @ApiOperation(value = "通过用户id获取用户")
    public Response<User> user(@ApiParam(value = "加密验证参数")@RequestHeader("Token") String token,
                               @ApiParam(value = "用户id")@RequestParam(value = "id", required = false) Integer userId) {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        if (userId == null) {
            userId = tokenService.getUserIdByToken(token);
        }
        AssertionUtil.notNull(userId, ErrorCode.BIZ_PARAM_ILLEGAL, "userId获取失败!");
        User user = userService.getUser(userId);
        if (user != null) {
            return Response.createSuc(user);
        } else {
            return Response.createErr("获取用户失败!");
        }
    }

    @ResponseBody
    @GetMapping("/stuNo")
    @ApiOperation(value = "通过学号获取用户信息")
    public Response<User> getUserByStuNo(@ApiParam(value = "加密验证参数")@RequestHeader("Token") String token,
                                         @ApiParam(value = "学号")@RequestParam("stuNo") String stuNo) {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        User user = userService.getUserByStuNo(stuNo);
        if (user != null) {
            return Response.createSuc(user);
        } else {
            return Response.createErr("获取用户失败!");
        }
    }

    @ResponseBody
    @GetMapping("/mail")
    @ApiOperation(value = "通过邮箱获取用户信息")
    public Response<User> getUserByMail(@ApiParam(value = "加密验证参数")@RequestHeader("Token") String token,
                                        @ApiParam(value = "邮箱")@RequestParam("mail") String mail) {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");
        User user = userService.getUserByMail(mail);
        if (user != null) {
            return Response.createSuc(user);
        } else {
            return Response.createErr("获取用户失败!");
        }
    }

    @ResponseBody
    @GetMapping("/nick")
    @ApiOperation(value = "通过账户名获取用户信息")
    public Response<User> getUserByUserName(@ApiParam(value = "加密验证参数")@RequestHeader("Token") String token,
                                            @ApiParam(value = "账户名")@RequestParam("username") String username) {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");

        User user = userService.getUserByUserName(username);
        if (user != null) {
            return Response.createSuc(user);
        } else {
            return Response.createErr("获取用户失败!");
        }
    }

    @ResponseBody
    @GetMapping("/admin/list")
    @ApiOperation(value = "通过昵称获取用户信息")
    public Response<ReturnPage<User>> getCheckSetByNick(@ApiParam(value = "加密验证参数")@RequestHeader("Token") String token,
                                                        @ApiParam(value = "昵称")@RequestParam(value = "nick") String nick,
                                                        @ApiParam(value = "当前页面")@RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
                                                        @ApiParam(value = "页面大小")@RequestParam(value = "pageSize", required = false, defaultValue = "2") Integer pageSize,
                                                        @ApiParam(value = "排序方式")@RequestParam(value = "sorter", required = false) String sorter) throws Exception {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");

        Integer userId = tokenService.getUserIdByToken(token);
        if (userId == null) {
            return Response.createErr("获取userId失败!userId为空");
        }
        ListPageUtil.paging(current, pageSize, sorter);
        List<User> userSetList = userService.getUserByNick(nick);
        PageInfo<User> pageInfo = new PageInfo<>(userSetList);
        ReturnPage<User> returnPage = ListPageUtil.returnPage(pageInfo);
        return Response.createSuc(returnPage);
    }

    @ResponseBody
    @GetMapping("/check")
    public String check() {
        return "ok2";
    }

    @ResponseBody
    @PostMapping("/update/photo")
    @ApiOperation(value = "更新照片")
    public Response<User> updatePhoto(@ApiParam(value = "加密验证参数")@RequestHeader("Token") String token,
                                      @ApiParam(value = "照片id")@RequestBody String photoId) {
        AssertionUtil.isTrue(tokenService.loginCheck(token), ErrorCode.INNER_PARAM_ILLEGAL, "您没有权限!请重新登录!");

        Integer userId = tokenService.getUserIdByToken(token);
        User user = userService.getUser(userId);
        user.setPhotoId(photoId);
        if (userService.updateUser(user) == 1) {
            return Response.createSuc(user);
        } else {
            return Response.createErr("更新照片失败!");
        }
    }
}
