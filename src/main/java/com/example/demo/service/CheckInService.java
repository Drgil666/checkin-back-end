package com.example.demo.service;

import com.example.demo.pojo.CheckIn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chentao
 */
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
     *判断用户是否签到过
     * @param check_id,要判断的checkin的id，token用户登录的token
     * @return 是否签到过
     */
    boolean ifSign(Integer check_id,String token);
    /**
     * 获取用户发起的一场签到记录信息
     *
     * @param id checkInId
     * @return 对应的checkin
     */
    CheckIn getCheckIn(Integer id);


    /**
     * 根据setId获取checkin列表
     *
     * @param setId 查找的setId
     * @return 对应的checkin列表
     */

    List<CheckIn> getCheckInListBySetId(Integer setId);

    /**
     * 批量删除checkin
     *
     * @param id 要删除的checkinId
     * @return 变化的行数
     */
    long deleteCheckIn(@Param("id") List<Integer> id);
}
