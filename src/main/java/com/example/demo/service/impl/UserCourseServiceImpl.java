package com.example.demo.service.impl;

import com.example.demo.mapper.UserCourseMapper;
import com.example.demo.pojo.UserCourse;
import com.example.demo.service.UserCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserCourseServiceImpl implements UserCourseService {
    @Resource
    private UserCourseMapper userCourseMapper;

    /**
     * 创建用户课程签到记录次数信息
     *
     * @param usercourse 要创建的课程签到记录次数信息
     * @return 该课程签到记录次数信息
     */
    @Override
    public boolean createUserCourse(UserCourse usercourse) {
        usercourse.setCount(0);
        return userCourseMapper.createUserCourse(usercourse);
    }


    /**
     * 更新该签到次数
     *
     * @param usercourse 要更新的课程签到次数
     * @return 更新好的课程签到次数
     */
    @Override
    public long updateUsercourse(UserCourse usercourse) {
        int n = usercourse.getCount();
        usercourse.setCount(n + 1);
        return userCourseMapper.updateUsercourse(usercourse);
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
