package com.example.demo.service.impl;

import com.example.demo.dao.CheckSetMapper;
import com.example.demo.pojo.CheckSet;
import com.example.demo.service.CheckSetService;
import org.apache.ibatis.annotations.Param;
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
     * 创建checkset
     *
     * @param checkset 要创建的checkset
     * @return 是否创建成功
     */
    @Override
    public boolean createCheckSet(CheckSet checkset) {
        return checkSetMapper.createCheckSet(checkset);
    }

    /**
     * 更新签到
     *
     * @param checkset 要更新的checkset
     * @return 更新好的CheckSet
     */
    @Override
    public long updateCheckSet(CheckSet checkset) {
        return checkSetMapper.updateCheckSet(checkset);
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
     * 根据用户id获取签到列表
     *
     * @param userId 要查找的用户id
     * @return 对应的checkset列表
     */
    @Override
    public List<CheckSet> getCheckSetList(Integer userId) {
        return checkSetMapper.getCheckSetList(userId);
    }

    /**
     * 根据nick查找checkset
     *
     * @param nick 要查找你的昵称
     * @return 对应的checkin
     */
    @Override
    public List<CheckSet> getCheckSetNick(String nick) {
        return checkSetMapper.getCheckSetNick(nick);
    }

    /**
     * 批量删除checkset
     *
     * @param id 要删除的checksetId
     * @return 变化的行数
     */
    @Override
    public long deleteCheckSet(@Param("id") List<Integer> id) {
        return checkSetMapper.deleteCheckSet(id);
    }
}
