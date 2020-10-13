package com.example.demo.service.impl;

import com.example.demo.mapper.UserCourseMapper;
import com.example.demo.pojo.UserCourse;
import com.example.demo.service.UserCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chen
 */
@Service
public class UserCourseServiceImpl implements UserCourseService {
    @Resource
    private UserCourseMapper userCourseMapper;

    /**
     * 创建用户课程签到记录次数信息
     *
     * @param userCourse 要创建的课程签到记录次数信息
     * @return 该课程签到记录次数信息
     */
    @Override
    public boolean createUserCourse(UserCourse userCourse) {
        userCourse.setCount(0);
        return userCourseMapper.createUserCourse(userCourse);
    }


    /**
     * 更新该签到次数
     *
     * @param userCourse 要更新的课程签到次数
     * @return 更新好的课程签到次数
     */
    @Override
    public long updateUserCourse(UserCourse userCourse) {
        int n = userCourse.getCount();
        userCourse.setCount(n + 1);
        return userCourseMapper.updateUserCourse(userCourse);
    }

    /**
     * 查询课程签到次数记录是否存在
     *
     * @param id 用户名
     * @return 对应学生该课程签到信息
     */
    @Override
    public UserCourse getUserCourse(Integer id) {
        return userCourseMapper.getUserCourse(id);
    }
}
