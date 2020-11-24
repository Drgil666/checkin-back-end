package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.User;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.PhotoService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private PhotoService photoService;

    @ResponseBody
    @PostMapping()
    public Response<User> user(@RequestBody CUDRequest<User, Integer> request) {
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
    public Response<User> user(@RequestParam("userId") Integer userId) {
        User user = userService.getUser(userId);
        if (user != null) {
            return Response.createSuc(user);
        } else {
            return Response.createErr("获取用户失败!");
        }
    }

    @ResponseBody
    @GetMapping("/findByStuNo")
    public Response<User> getUserByStuNo(@RequestParam("stu_no") String stu_no) {
        User user = userService.getUserByStuNo(stu_no);
        if (user != null) {
            return Response.createSuc(user);
        } else {
            return Response.createErr("获取用户失败!");
        }
    }

    @ResponseBody
    @GetMapping("/findByMail")
    public Response<User> getUserByMail(@RequestParam("mail") String mail) {
        User user = userService.getUserByMail(mail);
        if (user != null) {
            return Response.createSuc(user);
        } else {
            return Response.createErr("获取用户失败!");
        }
    }

    @ResponseBody
    @GetMapping("/findByNick")
    public Response<User> getUserByUserName(@RequestParam("username") String username) {
        User user = userService.getUserByUserName(username);
        if (user != null) {
            return Response.createSuc(user);
        } else {
            return Response.createErr("获取用户失败!");
        }
    }

    @ResponseBody
    @GetMapping("/check")
    public String check() {
        return "ok2";
    }

    @ResponseBody
    @GetMapping("/UserExcelDownloads")
    public void downloadAllClassmate(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
        List<User> classmateList = userService.userInFor();
        String fileName = "userinf" + ".xls";
        //设置要导出的文件的名字
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"Id", "姓名", "学校", "邮箱"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //在表中存放查询到的数据放入对应的列
        for (User user : classmateList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(user.getId());
            row1.createCell(1).setCellValue(user.getUsername());
            row1.createCell(2).setCellValue(user.getSchool());
            row1.createCell(3).setCellValue(user.getMail());
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
}
