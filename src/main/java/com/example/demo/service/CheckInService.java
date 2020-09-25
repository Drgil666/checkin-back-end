package com.example.demo.service;

import com.example.demo.pojo.CheckIn;

import java.util.List;

/**
 * @author Gilbert
 * @date 2020/9/25 16:58
 */
public interface CheckInService {
    /**
     * 创建签到
     * @param checkIn 签到
     * @return 带有id的签到
     */
    CheckIn createCheckIn(CheckIn checkIn);

    /**
     * 更新签到
     * @param checkIn 签到
     * @return 影响行数
     */
    CheckIn updateCheckIn(CheckIn checkIn);

    /**
     * 通过用户id和课程id获取签到记录
     * @param userId 用户id
     * @param courseId 课程id
     * @return 签到列表
     */
    List<CheckIn> getCheckInListByUserAndCourse(Integer userId, Integer courseId);

    /**
     * 通过id查找签到记录
     * @param id 签到id
     * @return 签到记录
     */
    CheckIn getCheckInById(Integer id);

    /**
     * 批量删除签到
     * @param id 要删除的列表
     * @return 影响的行数
     */
    long deleteCheckIn(List<Integer> id);
}
