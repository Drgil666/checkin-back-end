package com.example.demo.service;

import com.example.demo.pojo.CheckSet;
import org.jetbrains.annotations.NotNull;

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
    Boolean createCheckSet(@NotNull CheckSet checkSet);

    /**
     * 更新签到
     *
     * @param checkSet 要更新的checkSet
     * @return 更新好的CheckSet
     */
    Long updateCheckSet(@NotNull CheckSet checkSet);

    /**
     * 根据id获取获取签到
     *
     * @param id 签到id
     * @return 对应的签到
     */
    CheckSet getCheckSet(@NotNull Integer id);

    /**
     * 批量删除checkSet
     *
     * @param id 要删除的id
     * @return 变化的行数
     */
    Long deleteCheckSet(@NotNull List<Integer> id);

    /**
     * 学生获取CheckSet列表
     *
     * @param stuId   学生id
     * @param keyword 签到名
     * @return CheckSet列表
     */
    List<CheckSet> getCheckListByStu(@NotNull Integer stuId, String keyword);

    /**
     * 教师通过用户id和签到名获取签到列表
     *
     * @param userId 要查找的用户id
     * @param nick   签到名
     * @return 对应的checkSet列表
     */
    List<CheckSet> getCheckSetListByTeacher(@NotNull Integer userId, String nick);

    /**
     * 管理员端通过签到名获取签到列表
     *
     * @param nick 签到名
     * @return checkSet列表
     */
    List<CheckSet> getCheckSetListByNickAdmin(String nick);

}
