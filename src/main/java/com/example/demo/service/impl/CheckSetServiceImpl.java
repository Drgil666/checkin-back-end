package com.example.demo.service.impl;

import com.example.demo.mapper.CheckSetMapper;
import com.example.demo.pojo.CheckSet;
import com.example.demo.service.CheckSetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chentao
 */
@Service
public class CheckSetServiceImpl implements CheckSetService {
    @Resource
    private CheckSetMapper checkSetMapper;

    /**
     * 创建checkSet
     *
     * @param checkSet 要创建的checkSet
     * @return 是否创建成功
     */
    @Override
    public Boolean createCheckSet(CheckSet checkSet) {
        return checkSetMapper.createCheckSet(checkSet);
    }

    /**
     * 更新签到
     *
     * @param checkSet 要更新的checkSet
     * @return 更新好的CheckSet
     */
    @Override
    public Long updateCheckSet(CheckSet checkSet) {
        return checkSetMapper.updateCheckSet(checkSet);
    }

    /**
     * 根据id获取获取签到
     *
     * @param id 签到id
     * @return 对应的签到
     */
    @Override
    public CheckSet getCheckSet(Integer id) {
        return checkSetMapper.getCheckSet(id);
    }

    /**
     * 批量删除checkset
     *
     * @param id 要删除的checksetId
     * @return 变化的行数
     */
    @Override
    public Long deleteCheckSet(List<Integer> id) {
        return checkSetMapper.deleteCheckSet(id);
    }

    /**
     * 学生获取CheckSet列表
     *
     * @param stuId 学生id
     * @return CheckSet列表
     */
    @Override
    public List<CheckSet> getCheckListByStu(Integer stuId) {
        return checkSetMapper.getCheckListByStu(stuId);
    }

    /**
     * 教师通过用户id获取签到列表
     *
     * @param userId 要查找的用户id
     * @return 对应的checkSet列表
     */
    @Override
    public List<CheckSet> getCheckSetListByTeacher(Integer userId, String nick) {
        return checkSetMapper.getCheckSetListByTeacher(userId, nick);
    }
}
