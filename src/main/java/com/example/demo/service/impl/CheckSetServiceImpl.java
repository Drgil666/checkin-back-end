package com.example.demo.service.impl;

import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.CheckSetMapper;
import com.example.demo.pojo.CheckSet;
import com.example.demo.service.CheckSetService;
import com.example.demo.utils.AssertionUtil;
import org.jetbrains.annotations.NotNull;
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
    public Boolean createCheckSet(@NotNull CheckSet checkSet) {
        return checkSetMapper.createCheckSet(checkSet);
    }

    /**
     * 更新签到
     *
     * @param checkSet 要更新的checkSet
     * @return 更新好的CheckSet
     */
    @Override
    public Long updateCheckSet(@NotNull CheckSet checkSet) {
        AssertionUtil.notNull(checkSet.getId(), ErrorCode.BIZ_PARAM_ILLEGAL, "checkSet的Id不能为空!");
        return checkSetMapper.updateCheckSet(checkSet);
    }

    /**
     * 根据id获取获取签到
     *
     * @param checkSetId 签到id
     * @return 对应的签到
     */
    @Override
    public CheckSet getCheckSet(@NotNull Integer checkSetId) {
        AssertionUtil.notNull(checkSetId, ErrorCode.BIZ_PARAM_ILLEGAL, "checkSetId不能为空!");
        return checkSetMapper.getCheckSet(checkSetId);
    }

    /**
     * 批量删除checkset
     *
     * @param id 要删除的checksetId
     * @return 变化的行数
     */
    @Override
    public Long deleteCheckSet(@NotNull List<Integer> id) {
        AssertionUtil.notNull(id, ErrorCode.BIZ_PARAM_ILLEGAL, "id不能为空!");
        return checkSetMapper.deleteCheckSet(id);
    }

    /**
     * 学生获取CheckSet列表
     *
     * @param stuId 学生id
     * @return CheckSet列表
     */
    @Override
    public List<CheckSet> getCheckListByStu(@NotNull Integer stuId) {
        AssertionUtil.notNull(stuId, ErrorCode.BIZ_PARAM_ILLEGAL, "stuId不能为空!");
        return checkSetMapper.getCheckListByStu(stuId);
    }

    /**
     * 教师通过用户id获取签到列表
     *
     * @param userId 要查找的用户id
     * @return 对应的checkSet列表
     */
    @Override
    public List<CheckSet> getCheckSetListByTeacher(@NotNull Integer userId, String nick) {
        return checkSetMapper.getCheckSetListByTeacher(userId, nick);
    }

    /**
     * 管理员端通过签到名获取签到列表
     * nick 签到名
     */
    @Override
    public List<CheckSet> getCheckSetListByNickAdmin(String nick) {
        return checkSetMapper.getCheckSetListByNickAdmin(nick);
    }
}
