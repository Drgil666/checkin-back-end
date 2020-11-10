package com.example.demo.service;

import com.example.demo.pojo.Sign;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;


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
     * @return 对应的sign列表
     */
    ArrayList<Sign> getSignList(Integer stuId);

    /**
     * 删除sign
     *
     * @param id 要删除的signid
     */
    long deleteSign(Integer id);
}
