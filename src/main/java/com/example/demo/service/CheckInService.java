package com.example.demo.service;

import com.example.demo.pojo.CheckIn;
import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * @author chentao
 */
public interface CheckInService {
    /**
     * 创建签到
     *
     * @param checkIn 要更新的checkin
     * @return 是否创建成功
     */
    boolean createCheckIn(@NotNull CheckIn checkIn);

    /**
     * 更新签到
     *
     * @param checkIn 要更新的checkin
     * @return 更新好的CheckIn
     */
    long updateCheckIn(@NotNull CheckIn checkIn);

    /**
     * 某个用户是否已完成某场签到
     *
     * @param userId  用户id
     * @param checkId 签到id
     * @return 是否完成签到
     */
    Boolean isSign(@NotNull Integer userId, @NotNull Integer checkId);

    /**
     * 获取用户发起的一场签到记录信息
     *
     * @param id checkInId
     * @return 对应的checkin
     */
    CheckIn getCheckIn(@NotNull Integer id);


    /**
     * 根据setId获取checkin列表
     *
     * @param setId 查找的setId
     * @return 对应的checkin列表
     */

    List<CheckIn> getCheckInListBySetId(@NotNull Integer setId);

    /**
     * 学生获取CheckIn列表
     *
     * @param setId  checkSetId
     * @param userId 用户id
     * @return 对应的checkIn列表
     */
    List<CheckIn> getCheckInListByStu(Integer setId, Integer userId);

    /**
     * 批量删除checkin
     *
     * @param id 要删除的checkinId
     * @return 变化的行数
     */
    long deleteCheckIn(List<Integer> id);
}
