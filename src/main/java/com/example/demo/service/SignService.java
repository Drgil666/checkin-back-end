package com.example.demo.service;


import com.example.demo.pojo.CheckSet;
import com.example.demo.pojo.Sign;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author chentao
 */
public interface SignService {
    /**
     * 创建学生签到记录
     *
     * @param sign 要更新的sign,stu_id,sign_time,photo_id,check_id要加入的用户id,签到时间,签到照片id和签到id
     * @return 是否创建成功
     */
    boolean createSign(Sign sign);

    /**
     * 更新签到
     *
     * @param sign 要更新的sign
     * @return 更新好的sign
     */
    long updateSign(Sign sign);

    /**
     * 根据id获取获取签到
     *
     * @param id 签到id
     * @return 对应的签到
     */
    Sign getSign(Integer id);

    /**
     * 根据学生id获取签到列表
     * @param stuId 学生id
     * @return 对应的sign列表
     */
    List<Sign> getSignList(Integer stuId);

    /**
     * 根据signlist中的checkid查找checkin
     *
     * @param signList 要查找的signlist
     * @return 查找的checkinlist
     */

    List<CheckSet> getCheckSetBySign(@Param("id") List<Sign> signList);

    /**
     * 批量删除sign
     *
     * @param id 要删除的signid
     * @return 变化的行数
     */
    long deleteSign(@Param("id") List<Integer> id);

    /**
     * 导出表格
     *
     * @param checkId 导出signin的checkinId
     * @return 表格文件
     */
    List<Sign> signInFor(Integer checkId);
}
