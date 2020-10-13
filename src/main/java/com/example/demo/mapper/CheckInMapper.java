package com.example.demo.mapper;

import com.example.demo.pojo.CheckIn;
import com.example.demo.pojo.UserCourse;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CheckInMapper {
    /**
     * 记录一场签到
     *
     * @param checkin 要更新的checkin ,user_id,course_id,start_time,end_time,status要加入的用户id,课程id,签到时间,签退时间和签到类型
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into checkin (id,user_id,course_id,start_time,end_time,status) values (#{checkin.id},#{checkin.userId},#{checkin.courseId},#{checkin.startTime},#{checkin.endTime},#{checkin.status})")
    boolean createCheckIn(@Param("checkin") CheckIn checkin);

    /**
     * 更新签到记录
     *
     * @param checkin 要更新的checkin
     * @return 更新好的CheckIn
     */
    @Insert("update checkin set id=#{checkin.id},user_id=#{checkin.userId},course_id=#{checkin.courseId},start_time=#{checkin.startTime},end_time=#{checkin.endTime},status=#{checkin.status}")
    long updateCheckIn(@Param("checkin") CheckIn checkin);
    /**
     * 获取用户发起的一场签到记录信息
     *
     * @param id 签到id
     * @return 用户发起的一场签到记录信息
     */
    @Select("select * from checkin where id=#{id}")
    CheckIn getCheckIn(@Param("id") Integer id);
}
