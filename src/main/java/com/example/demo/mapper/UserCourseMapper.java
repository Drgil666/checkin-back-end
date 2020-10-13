package com.example.demo.mapper;

import com.example.demo.pojo.UserCourse;
import org.apache.ibatis.annotations.*;

/**
 * @author chentao
 */
@Mapper
public interface UserCourseMapper {
    /**
     * 为学生添加课程
     *
     * @param userCourse 要更新的uesrCourse ,stu_id,course_id,要加入的学生id和课程id
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into usercourse (stu_id,course_id,count) values (#{userCourse.stuId},#{userCourse.courseId},#{userCourse.count})")
    boolean createUserCourse(@Param("userCourse") UserCourse userCourse);

    /**
     * 更添加签到次数
     *
     * @param userCourse 要更新的userCourse count 要更新的签到次数
     * @return 更新好的User
     */
    @Insert("update usercourse set count=#{userCourse.count} where stu_id=#{userCourse.stuId} and course_id=#{userCourse.courseId}")
    long updateUserCourse(@Param("userCourse") UserCourse userCourse);

    /**
     * 获取用户课程签到信息
     *
     * @param id 用户id
     * @return 用户课程签到信息
     */
    @Select("select * from usercourse where stu_id=#{id}")
    UserCourse getUserCourse(@Param("id") Integer id);
}
