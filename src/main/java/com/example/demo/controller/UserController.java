package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.User;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import com.example.demo.utils.ListPageUtil;
import com.example.demo.pojo.vo.ReturnPage;
import com.github.pagehelper.PageInfo;
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
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private TokenService tokenService;

    @ResponseBody
    @PostMapping()
    public Response<User> user(@RequestHeader("Token") String token, @RequestBody CUDRequest<User, Integer> request) {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
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
    public Response<User> user(@RequestHeader("Token") String token,
                               @RequestParam(value = "id",required = false) Integer userId) {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        if(userId==null) {
            userId = tokenService.getUserIdByToken(token);
        }
        if (userId != null) {
            User user = userService.getUser(userId);
            if (user != null) {
                return Response.createSuc(user);
            } else {
                return Response.createErr("获取用户失败!");
            }
        } else {
            return Response.createErr("userId获取失败!");
        }
    }

    @ResponseBody
    @GetMapping("/stuNo")
    public Response<User> getUserByStuNo(@RequestHeader("Token") String token, @RequestParam("stuNo") String stuNo) {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        User user = userService.getUserByStuNo(stuNo);
        if (user != null) {
            return Response.createSuc(user);
        } else {
            return Response.createErr("获取用户失败!");
        }
    }

    @ResponseBody
    @GetMapping("/mail")
    public Response<User> getUserByMail(@RequestHeader("Token") String token, @RequestParam("mail") String mail) {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        User user = userService.getUserByMail(mail);
        if (user != null) {
            return Response.createSuc(user);
        } else {
            return Response.createErr("获取用户失败!");
        }
    }

    @ResponseBody
    @GetMapping("/nick")
    public Response<User> getUserByUserName(@RequestHeader("Token") String token, @RequestParam("username") String username) {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
        User user = userService.getUserByUserName(username);
        if (user != null) {
            return Response.createSuc(user);
        } else {
            return Response.createErr("获取用户失败!");
        }
    }
    @ResponseBody
    @GetMapping("/admin/list")
    public Response<ReturnPage<User>> getCheckSetByNick(@RequestHeader("Token") String token,
                                                            @RequestParam(value = "nick") String nick,
                                                            @RequestParam(value = "current", required = false,defaultValue="1") Integer current,
                                                            @RequestParam(value = "pageSize", required = false,defaultValue="2") Integer pageSize,
                                                            @RequestParam(value = "sorter", required = false) String sorter) throws Exception {
        if (!tokenService.loginCheck(token)) {
            return Response.createErr("您没有权限!请重新登录!");
        }
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
}
