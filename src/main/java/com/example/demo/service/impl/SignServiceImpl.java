package com.example.demo.service.impl;

import com.example.demo.mapper.SignMapper;
import com.example.demo.pojo.Sign;
import com.example.demo.pojo.vo.SignVO;
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
public class SignServiceImpl implements SignService {
    @Resource
    private SignMapper signMapper;
    @Resource
    private SignService signService;
    @Resource
    private CheckInService checkInService;
    @Resource
    private TokenService tokenService;


    /**
     * 创建学生签到记录
     *
     * @param sign 要更新的sign
     * @return 是否创建成功
     */
    @Override
    public Boolean createSign(Sign sign) {
        return signMapper.createSign(sign);
    }

    /**
     * 更新签到
     *
     * @param sign 要更新的sign
     * @return 更新好的sign
     */
    @Override
    public Long updateSign(Sign sign) {
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
     * @param stuId 要查找的学生id
     * @return 对应的sign列表
     */
    @Override
    public List<Sign> getSignList(Integer stuId) {
        return signMapper.getSignList(stuId);
    }

    /**
     * 批量删除sign
     *
     * @param id 要删除的signid
     * @return 影响的行数
     */
    @Override
    public Long deleteSign(List<Integer> id) {
        return signMapper.deleteSign(id);
    }

    /**
     * 根据CheckId获取签到信息
     *
     * @param checkId 获取签到信息的checkinId
     * @return 学生名
     */
    @Override
    public List<SignVO> getSignByCheckId(Integer checkId) {
        return signMapper.getSignByCheckId(checkId);
    }

    /**
     * 根据checkinId和userId获取对应的signIn
     *
     * @param checkId 获取签到信息的checkInId
     * @param userId  用户id
     * @return 学生名
     */
    @Override
    public SignVO getSignByCheckIdAndUserId(Integer checkId, Integer userId) {
        return signMapper.getSignByCheckIdAndUserId(checkId, userId);
    }
}
