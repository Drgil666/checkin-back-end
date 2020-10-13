package com.example.demo.mapper;

import com.example.demo.pojo.User;
import com.example.demo.pojo.UserCourse;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserCourseMapper {
    /**
     * 为学生添加课程
     *
     * @param usercourse 要更新的uesrcourse ,stu_id,course_id,要加入的学生id和课程id
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into usercourse (stu_id,course_id,count) values (#{usercourse.stuId},#{usercourse.courseId},#{usercourse.count})")
    boolean createUserCourse(@Param("usercourse") UserCourse usercourse);

    /**
     * 更添加签到次数
     *
     * @param usercourse 要更新的usercourse count 要更新的签到次数
     * @return 更新好的User
     */
    @Insert("update usercourse set count=#{usercourse.count} where stu_id=#{usercourse.stuId} and course_id=#{usercourse.courseId}")
   long updateUsercourse(@Param("usercourse") UserCourse usercourse);
    /**
     * 获取用户课程签到信息
     *
     * @param id 用户id
     * @return 用户课程签到信息
     */
    @Select("select * from usercourse where stu_id=#{id}")
    UserCourse getUserCourse(@Param("id") Integer id);
}
