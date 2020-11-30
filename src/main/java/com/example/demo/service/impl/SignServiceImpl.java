package com.example.demo.service.impl;

import com.example.demo.mapper.SignMapper;
import com.example.demo.pojo.CheckIn;
import com.example.demo.pojo.CheckSet;
import com.example.demo.pojo.Sign;
import com.example.demo.pojo.vo.SignVO;
import com.example.demo.service.CheckInService;
import com.example.demo.service.SignService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chentao
 */
@Service
public class SignServiceImpl implements SignService {
    @Resource
    private SignMapper signMapper;
    @Resource
    private CheckInService checkInService;

    /**
     * 创建学生签到记录
     * sign 要更新的sign,stu_id,sign_time,photo_id,check_id要加入的用户id,签到时间,签到照片id和签到id
     *
     * @param sign 要更新的sign,stu_id,sign_time,photo_id,check_id要加入的用户id,签到时间,签到照片id和签到id
     * @return 是否创建成功
     */
    @Override
    public boolean createSign(Sign sign) {
        return signMapper.createSign(sign);
    }

    /**
     * 更新签到
     *
     * @param sign 要更新的sign
     * @return 更新好的sign
     */
    @Override
    public long updateSign(Sign sign) {
        return signMapper.updateSign(sign);
    }

    /**
     * 根据id获取获取签到
     *
     * @param id 签到id
     * @return 对应的签到
     */
    @Override
    public Sign getSign(Integer id) {
        return signMapper.getSign(id);
    }

    /**
     * 根据学生id获取签到列表
     *
     * @return 对应的sign列表
     */
    @Override
    public List<Sign> getSignList(Integer stuId) {
        return signMapper.getSignList(stuId);
    }

    /**
     * 根据signList中的checkId查找checkin
     *
     * @param signList 学生签到列表
     * @return 查找的checkinList
     */
    @Override
    public List<CheckSet> getCheckSetBySign(List<Sign> signList) {
        List<Integer> idList = new ArrayList<>();
        List<CheckIn> checkInList = new ArrayList<>();
        for (Sign value : signList) {
            checkInList.add(checkInService.getCheckIn(value.getCheckId()));
        }
        for (CheckIn value : checkInList) {
            idList.add(value.getSetId());
        }
        return signMapper.getCheckSetBySign(idList);
    }

    /**
     * 批量删除sign
     * <p>
     * id 删除的signid
     *
     * @return 变化的行数
     */
    @Override
    public long deleteSign(List<Integer> id) {
        return signMapper.deleteSign(id);
    }

    /**
     * 获取签到信息
     *
     * @param checkId 获取签到信息的checkinId
     * @return 学生名
     */
    @Override
    public List<SignVO> getSignByCheckId(Integer checkId) {
        return signMapper.getSignByCheckId(checkId);
    }

    /**
     * 根据一个checkin和userid获取对应的signIn吧
     *
     * @param checkId,userid 获取签到信息的checkinId
     * @return 学生名
     */
    @Override
    public List<Sign> getSignByCheckIdAndUserId(Integer checkId, Integer userId) {
        return signMapper.getSignByCheckIdAndUserId(checkId, userId);
    }
}
