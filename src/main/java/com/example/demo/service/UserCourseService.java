package com.example.demo.service;

import com.example.demo.pojo.UserCourse;
import org.apache.ibatis.annotations.Param;

public interface UserCourseService {
    /**
     * 为学生添加课程
     *
     * @param usercourse 要更新的uesrcourse ,stu_id,course_id,要加入的学生id和课程id
     * @return 是否创建成功
     */
    boolean createUserCourse(UserCourse usercourse);
    /**
     * 更添加签到次数
     *
     * @param usercourse 要更新的usercourse count 要更新的签到次数
     * @return 更新好的Usercourse
     */
    long updateUsercourse(UserCourse usercourse);
    /**
     * 获取用户课程信息
     *
     * @param id 用户id
     * @return 用户课程信息
     */
    UserCourse getUserCourse(Integer id);
}
