package com.example.demo.dao;

import com.example.demo.pojo.Sign;
import org.apache.ibatis.annotations.*;

/**
 * @author chentao
 */
@Mapper
public interface SignMapper {
    /**
     * 创建学生签到记录
     *
     * @param sign 要更新的sign,stu_id,sign_time,photo_id,check_id要加入的用户id,签到时间,签到照片id和签到id
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into signin (stu_id,sign_time,photo_id,check_id) values (#{sign.stuId},#{sign.signTime},#{sign.photoId},#{sign.checkId})")
    boolean createSign(@Param("sign") Sign sign);
    /**
     * 更新签到
     *
     * @param sign 要更新的sign
     * @return 更新好的sign
     */
    @Insert("update signin set id=#{sign.id},stu_id=#{sign.stuId},sign_time=#{sign.signTime},photo_id=#{sign.photoId},check_id=#{sign.checkId}")
    long updateSign(@Param("sign") Sign sign);

    /**
     * 根据id获取获取签到
     *
     * @param id 签到id
     * @return 对应的签到
     */
    @Select("select * from signin where id=#{id}")
    Sign getSign(@Param("id") Integer id);
}
