package com.example.demo.service.impl;

import com.example.demo.mapper.CheckInMapper;
import com.example.demo.mapper.UserCourseMapper;
import com.example.demo.pojo.CheckIn;
import com.example.demo.pojo.UserCourse;
import com.example.demo.service.CheckInService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public boolean createCheckIn(CheckIn checkin){
        return checkInMapper.createCheckIn(checkin);
    }
    /**
     * 更新签到
     *
     * @param checkin 要更新的签到
     * @return 更新好的签到
     */
    @Override
    public long updateCheckIn(CheckIn checkin){

        return checkInMapper.updateCheckIn(checkin);
    }
    /**
     * 获取签到信息
     *
     * @param id 签到id
     * @return 签到
     */
    @Override
    public CheckIn getCheckIn(Integer id){
        return checkInMapper.getCheckIn(id);
    }
}
