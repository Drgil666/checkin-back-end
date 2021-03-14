package com.example.demo.service.impl;

import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.CheckInMapper;
import com.example.demo.pojo.CheckIn;
import com.example.demo.service.CheckInService;
import com.example.demo.utils.AssertionUtil;
import com.sun.istack.internal.NotNull;
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
    public boolean createCheckIn(@NotNull CheckIn checkIn) {
        return checkInMapper.createCheckIn(checkIn);
    }

    /**
     * 更新签到
     *
     * @param checkIn 要更新的checkin
     * @return 更新好的CheckIn
     */
    @Override
    public long updateCheckIn(@NotNull CheckIn checkIn) {
        AssertionUtil.notNull(checkIn.getId(), ErrorCode.BIZ_PARAM_ILLEGAL, "checkIn的Id为空!");
        return checkInMapper.updateCheckIn(checkIn);
    }

    /**
     * 获取用户发起的一场签到记录信息
     *
     * @param id checkInId
     * @return 对应的checkin
     */
    @Override
    public CheckIn getCheckIn(@NotNull Integer id) {
        return checkInMapper.getCheckIn(id);
    }

    /**
     * 某个用户是否已完成某场签到
     *
     * @param userId    用户id
     * @param checkInId 签到id
     * @return 是否完成签到
     */
    @Override
    public Boolean isSign(@NotNull Integer userId, @NotNull Integer checkInId) {
        Long count = checkInMapper.isSign(userId, checkInId);
        return count > 0;
    }

    /**
     * 根据setId获取checkin列表
     *
     * @param checkSetId 查找的setId
     * @return 对应的checkin列表
     */
    @Override
    public List<CheckIn> getCheckInListBySetId(@NotNull Integer checkSetId) {
        return checkInMapper.getCheckInListBySetId(checkSetId);
    }

    /**
     * 学生获取CheckIn列表
     *
     * @param checkSetId checkSetId
     * @param userId     用户id
     * @return 对应的checkIn列表
     */
    @Override
    public List<CheckIn> getCheckInListByStu(@NotNull Integer checkSetId, @NotNull Integer userId) {
        return checkInMapper.getCheckInListByStu(checkSetId, userId);
    }

    /**
     * 批量删除checkin
     *
     * @param id 要删除的checkinId
     * @return 变化的行数
     */
    @Override
    public long deleteCheckIn(@NotNull List<Integer> id) {
        return checkInMapper.deleteCheckIn(id);
    }
}
