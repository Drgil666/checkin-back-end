package com.example.demo.service;


import com.example.demo.pojo.CheckSet;
import com.example.demo.pojo.Sign;
import com.example.demo.pojo.vo.SignVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
     *
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
    long deleteSign(List<Integer> id);

    /**
     * 获取签到信息
     *
     * @param checkId 获取签到信息的checkinId
     * @return 学生名
     */
    List<SignVO> getSignByCheckId(Integer checkId);

    /**
     * 根据一个checkin和userid获取对应的signIn吧
     *
     * @param checkId,userid 获取签到信息的checkinId
     * @return 学生名
     */
    List<Sign> getSignByCheckIdAndUserId(Integer checkId, Integer userId);
}
