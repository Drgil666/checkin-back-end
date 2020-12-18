package com.example.demo.service;

import com.example.demo.pojo.CheckSet;

import java.util.List;

/**
 * @author chentao
 */
public interface CheckSetService {
    /**
     * 创建checkSet
     *
     * @param checkSet 要创建的checkSet
     * @return 是否创建成功
     */
    Boolean createCheckSet(CheckSet checkSet);

    /**
     * 更新签到
     *
     * @param checkSet 要更新的checkSet
     * @return 更新好的CheckSet
     */
    Long updateCheckSet(CheckSet checkSet);

    /**
     * 根据id获取获取签到
     *
     * @param id 签到id
     * @return 对应的签到
     */
    CheckSet getCheckSet(Integer id);

    /**
     * 批量删除checkset
     *
     * @param id 要删除的checksetId
     * @return 变化的行数
     */
    Long deleteCheckSet(List<Integer> id);

    /**
     * 学生获取CheckSet列表
     *
     * @param stuId 学生id
     * @return CheckSet列表
     */
    List<CheckSet> getCheckListByStu(Integer stuId);

    /**
     * 教师通过用户id和签到名获取签到列表
     *
     * @param userId 要查找的用户id
     * @param nick   签到名
     * @return 对应的checkSet列表
     */
    List<CheckSet> getCheckSetListByTeacher(Integer userId, String nick);
}
