package com.example.demo.controller;


import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.pojo.Course;
import com.example.demo.pojo.Response;
import com.example.demo.pojo.vo.CUDRequest;
import com.example.demo.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yutao
 */
@Controller
@Slf4j
@RequestMapping("/api/course")
public class CourseController {
    @Resource
    private CourseService courseService;

    @ResponseBody
    @PostMapping()
    public Response<Course> course(@RequestBody CUDRequest<Course, Integer> request) {
        switch (request.getMethod()) {
            case CUDRequest.CREATE_METHOD: {
                courseService.createCourse(request.getData());
                if (request.getData().getId() != null) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr("创建课程失败!");
                }
            }
            case CUDRequest.UPDATE_METHOD: {
                if (courseService.updateCourse(request.getData()) == 1) {
                    return Response.createSuc(request.getData());
                } else {
                    throw new ErrorException(ErrorCode.BIZ_PARAM_ILLEGAL, "更新失败!");
                }
            }
            case CUDRequest.DELETE_METHOD: {
                if (courseService.deleteCourse(request.getData().getId()) == 1) {
                    return Response.createSuc(request.getData());
                } else {
                    throw new ErrorException(ErrorCode.BIZ_PARAM_ILLEGAL, "删除失败!");
                }
            }
            default: {
                return Response.createErr("method错误!");
            }
        }
    }

    @ResponseBody
    @GetMapping()
    public Response<Course> course(@RequestParam("courseId") Integer id) {
        Course course = courseService.getCourse(id);
        if (course != null) {
            return Response.createSuc(course);
        } else {
            return Response.createErr("获取课程失败!");
        }
    }

    @ResponseBody
    @GetMapping("/find")
    public Response<List<Course>> course(@RequestParam("name") String name) {
        List<Course> course = courseService.getCourseListByName(name);
        if (course != null) {
            return Response.createSuc(course);
        } else {
            return Response.createErr("获取课程失败!");
        }
    }
}
