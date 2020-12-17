package com.example.demo.service.impl;

import com.example.demo.mapper.CheckInMapper;
import com.example.demo.pojo.CheckIn;
import com.example.demo.pojo.Sign;
import com.example.demo.service.CheckInService;
import com.example.demo.service.SignService;
import com.example.demo.service.TokenService;
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
    @Resource
    private TokenService tokenService;
    @Resource
    private SignService signService;
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
     *判断用户是否签到过
     * @param check_id,要判断的checkin的id，token用户登录的token
     * @return 是否签到过
     */
    @Override
    public boolean ifSign(Integer check_id,String token){
        Integer stuId=tokenService.getUserIdByToken(token);
        List<Sign> signList=signService.getSignList(stuId);
        for(Sign value : signList){
            if(value.getCheckId().equals(check_id)){
                return false;
            }
        }
        return true;
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
