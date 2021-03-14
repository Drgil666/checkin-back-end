package com.example.demo.service;


import com.example.demo.pojo.Sign;
import com.sun.istack.internal.NotNull;

import java.util.List;


/**
 * @author chentao
 */
public interface SignService {
    /**
     * 创建学生签到记录
     *
     * @param sign 要更新的sign
     * @return 是否创建成功
     */
    Boolean createSign(@NotNull Sign sign);

    /**
     * 更新签到
     *
     * @param sign 要更新的sign
     * @return 更新好的sign
     */
    Long updateSign(@NotNull Sign sign);

    /**
     * 根据id获取获取签到
     *
     * @param id 签到id
     * @return 对应的签到
     */
    Sign getSign(@NotNull Integer id);

    /**
     * 根据学生id获取签到列表
     *
     * @param stuId 要查找的学生id
     * @return 对应的sign列表
     */
    List<Sign> getSignList(@NotNull Integer stuId);

    /**
     * 批量删除sign
     *
     * @param id 要删除的signid
     * @return 影响的行数
     */
    Long deleteSign(@NotNull List<Integer> id);

    /**
     * 根据CheckId获取签到信息
     *
     * @param checkId 获取签到信息的checkinId
     * @return 学生名
     */
    List<Sign> getSignByCheckId(@NotNull Integer checkId);

    /**
     * 根据checkinId和userId获取对应的signIn
     *
     * @param checkId 获取签到信息的checkInId
     * @param userId  用户id
     * @return 学生名
     */
    Sign getSignByCheckIdAndUserId(@NotNull Integer checkId, @NotNull Integer userId);

}
