package com.example.demo.controller;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.Sign;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.pojo.vo.Response;
import com.example.demo.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author chentao
 */
@Controller
@Slf4j
@RequestMapping("/api/Sign")
public class SignController {
    @Resource
    private SignService signService;

    @ResponseBody
    @PostMapping() public Response<Sign> sign(@RequestBody CUDRequest<Sign, Integer> request) {
        switch (request.getMethod()) {
            case CUDRequest.CREATE_METHOD: {
                signService.createSign(request.getData());
                if (request.getData().getId() != null) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr("添加签到记录失败!");
                }
            }
            case CUDRequest.UPDATE_METHOD: {
                if (signService.updateSign(request.getData()) == 1) {
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
    @GetMapping("/SignExcelDownloads")
    public void downloadAllClassmate(HttpServletResponse response, @RequestParam("checkId") Integer checkId) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        List<Sign> classmateList = signService.signInFor(checkId);

        String fileName = "signinf" + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = {"Id", "学生id", "签到时间", "照片id"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //在表中存放查询到的数据放入对应的列
        for (Sign sign : classmateList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(sign.getId());
            row1.createCell(1).setCellValue(sign.getStuId());
            row1.createCell(2).setCellValue(simpleDateFormat.format(sign.getSignTime()));
            row1.createCell(3).setCellValue(sign.getPhotoId());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

}
