package com.example.demo.service.impl;

import com.example.demo.dao.CheckInMapper;
import com.example.demo.pojo.CheckIn;
import com.example.demo.service.CheckInService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
     *
     *
     *
     */
    @Override
    public long updateCheckIn(CheckIn checkin) {

        return checkInMapper.updateCheckIn(checkin);
    }

    /**
     * 获取签到信息
     *
     *
     */
    @Override
    public CheckIn getCheckIn(Integer id) {
        return checkInMapper.getCheckIn(id);
    }
    /**
     * 根据用户id获取签到列表
     *
     *
     * @return 对应的checkin列表
     */
    @Override
   public List<CheckIn> getCheckInList(Integer userId){
        return checkInMapper.getCheckInList(userId);
    }
    /**
     * 根据nick查找checkin
     *
     * @return checkin
     */
    @Override
    public List<CheckIn>  getCheckInNick(String nick){
        return checkInMapper.getCheckInNick(nick);
    }
    /**
     * 删除checkin
     *
     *  id 要删除的checkinid
     *
     */
    @Override
   public long deleteCheckIn(Integer id){
       return checkInMapper.deleteCheckIn(id);
   }
}
