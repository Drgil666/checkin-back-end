package com.example.demo.service.impl;

import com.example.demo.mapper.CheckInMapper;
import com.example.demo.pojo.CheckIn;
import com.example.demo.service.CheckInService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chentao
 */
@Service
public class CheckInServiceImpl implements CheckInService {
    @Resource
    private CheckInMapper checkInMapper;

    /**
     * 创建一场签到
     *
     * @param checkIn 要创建的签到
     * @return 带有id的签到
     */
    @Override
    public boolean createCheckIn(CheckIn checkIn) {
        return checkInMapper.createCheckIn(checkIn);
    }

    /**
     * 更新签到
     *
     * @param checkIn 要更新的checkin
     * @return 更新好的CheckIn
     */
    @Override
    public long updateCheckIn(CheckIn checkIn) {
        return checkInMapper.updateCheckIn(checkIn);
    }

    /**
     * 获取用户发起的一场签到记录信息
     *
     * @param id checkInId
     * @return 对应的checkin
     */
    @Override
    public CheckIn getCheckIn(Integer id) {
        return checkInMapper.getCheckIn(id);
    }

    /**
     * 某个用户是否已完成某场签到
     *
     * @param userId  用户id
     * @param checkId 签到id
     * @return 是否完成签到
     */
    @Override
    public Boolean isSign(Integer userId, Integer checkId) {
        return checkInMapper.isSign(userId, checkId) > 0;
    }

    /**
     * 根据setId获取checkin列表
     *
     * @param setId 查找的setId
     * @return 对应的checkin列表
     */
    @Override
    public List<CheckIn> getCheckInListBySetId(Integer setId) {
        return checkInMapper.getCheckInListBySetId(setId);
    }

    /**
     * 学生获取CheckIn列表
     *
     * @param setId  checkSetId
     * @param userId 用户id
     * @return 对应的checkIn列表
     */
    @Override
    public List<CheckIn> getCheckInListByStu(Integer setId, Integer userId) {
        return checkInMapper.getCheckInListByStu(setId, userId);
    }

    /**
     * 批量删除checkin
     *
     * @param id 要删除的checkinId
     * @return 变化的行数
     */
    @Override
    public long deleteCheckIn(List<Integer> id) {
        return checkInMapper.deleteCheckIn(id);
    }
}
