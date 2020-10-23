package com.example.demo.service;

import com.example.demo.pojo.Sign;



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
}
