package com.example.demo.service.impl;

import com.example.demo.mapper.SignMapper;
import com.example.demo.pojo.Sign;
import com.example.demo.service.SignService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    /**
     * 创建学生签到记录
     *
     * @param sign 要更新的sign,stu_id,sign_time,photo_id,check_id要加入的用户id,签到时间,签到照片id和签到id
     * @return 是否创建成功
     */
    @Override
    public boolean createSign(Sign sign){return signMapper.createSign(sign);}

    /**
     * 更新签到
     *
     * @param sign 要更新的sign
     * @return 更新好的sign
     */
    @Override
    public long updateSign(Sign sign){return signMapper.updateSign(sign);}

    /**
     * 根据id获取获取签到
     *
     * @param id 签到id
     * @return 对应的签到
     */
    @Override
    public Sign getSign(Integer id){return signMapper.getSign(id);}

    /**
     * 根据学生id获取签到列表
     *
     *
     * @return 对应的sign列表
     */
    @Override
    public  ArrayList<Sign> getSignList(Integer stuId){
        return signMapper.getSignList(stuId);
    }

    /**
     * 删除sign
     *
     * @param id 要删除的signid
     */
    @Override
    public long deleteSign(Integer id) {
        return signMapper.deleteSign(id);
    }

    /**
     * 导出表格
     *
     * @param checkId 导出signin的checkinId
     * @return 表格文件
     */
    @Override
    public List<Sign> signInFor(Integer checkId) {
        return signMapper.signInFor(checkId);
    }
}
