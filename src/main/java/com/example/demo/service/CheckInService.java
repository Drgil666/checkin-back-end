package com.example.demo.service;

import com.example.demo.pojo.CheckIn;
import com.example.demo.pojo.UserCourse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CheckInService {
    /**
     * 记录一场签到
     *
     * @param checkin 要更新的checkin ,user_id,course_id,start_time,end_time,status要加入的用户id,课程id,签到时间,签退时间和签到类型
     * @return 是否创建成功
     */

     boolean createCheckIn(CheckIn checkin);

    /**
     * 更新签到记录
     *
     * @param checkin 要更新的checkin
     * @return 更新好的CheckIn
     */

    long updateCheckIn(CheckIn checkin);
    /**
     * 获取用户发起的一场签到记录信息
     *
     * @param id 签到id
     * @return 用户发起的一场签到记录信息
     */
    CheckIn getCheckIn(Integer id);
}
