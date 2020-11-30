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
     * @param checkin 要创建的签到
     * @return 带有id的签到
     */
    @Override
    public boolean createCheckIn(CheckIn checkin) {
        return checkInMapper.createCheckIn(checkin);
    }

    /**
     * 更新签到
     */
    @Override
    public long updateCheckIn(CheckIn checkin) {
        return checkInMapper.updateCheckIn(checkin);
    }

    /**
     * 获取签到信息
     */
    @Override
    public CheckIn getCheckIn(Integer id) {
        return checkInMapper.getCheckIn(id);
    }

    /**
     * 根据setId获取checkin列表
     *
     * @param setId 对应的checkSetId
     * @return 对应的checkin列表
     */
    @Override
    public List<CheckIn> getCheckInListBySetId(Integer setId) {
        return checkInMapper.getCheckInListBySetId(setId);
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
