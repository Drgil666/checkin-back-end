package com.example.demo.service;

import com.example.demo.pojo.UserCourse;

/**
 * @author chen
 */
public interface UserCourseService {
    /**
     * 为学生添加课程
     *
     * @param userCourse 要更新的userCourse ,stu_id,course_id,要加入的学生id和课程id
     * @return 是否创建成功
     */
    boolean createUserCourse(UserCourse userCourse);

    /**
     * 更添加签到次数
     *
     * @param userCourse 要更新的userCourse count 要更新的签到次数
     * @return 更新好的UserCourse
     */
    long updateUserCourse(UserCourse userCourse);

    /**
     * 获取用户课程信息
     *
     * @param id 用户id
     * @return 用户课程信息
     */
    UserCourse getUserCourse(Integer id);
}
