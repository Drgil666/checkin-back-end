package com.example.demo.dao;


import com.example.demo.pojo.CheckSet;
import com.example.demo.pojo.Sign;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
    @Insert("update signin set id=#{sign.id},stu_id=#{sign.stuId},sign_time=#{sign.signTime},photo_id=#{sign.photoId},check_id=#{sign.checkId} where id=#{sign.id}")
    long updateSign(@Param("sign") Sign sign);

    /**
     * 根据id获取获取签到
     *
     * @param id 签到id
     * @return 对应的签到
     */
    @Select("select * from signin where id=#{id}")
    Sign getSign(@Param("id") Integer id);

    /**
     * 根据学生id获取签到列表
     *
     * @param stuId 要查找的学生id
     * @return 对应的sign列表
     */
    @Select("select * from signin where stu_id=#{stuId}")
    List<Sign> getSignList(@Param("stuId") Integer stuId);

    /**
     * 根据signlist中的checkid查找checkinlist再查找checksetlist
     *
     * @param id 获取到的checksetlist数组
     * @return 查找的checksetlist
     */

    List<CheckSet> getCheckSetBySign(@Param("id") List<Integer> id);

    /**
     * 批量删除sign
     *
     * @param id 要删除的signid
     */

    long deleteSign(@Param("id") List<Integer> id);

    /**
     * 导出表格
     *
     * @param checkId 导出signin的checkinId
     * @return 表格文件
     */
    @Select("select * from signin where check_id=#{checkId}")
    List<Sign> signInFor(@Param("checkId") Integer checkId);
}
