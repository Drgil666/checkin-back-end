package com.example.demo.mapper;


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
     * @param sign 要更新的sign
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into signin (stu_id,sign_time,photo_id,check_id,nick,stu_no,avatar_url) values " +
            "(#{sign.stuId},#{sign.signTime},#{sign.photoId},#{sign.checkId},#{sign.nick},#{sign.stuNo},#{sign.avatarUrl})")
    Boolean createSign(@Param("sign") Sign sign);

    /**
     * 更新签到
     *
     * @param sign 要更新的sign
     * @return 更新好的sign
     */
    @Insert("update signin set id=#{sign.id},stu_id=#{sign.stuId},sign_time=#{sign.signTime}," +
            "photo_id=#{sign.photoId},check_id=#{sign.checkId},nick=#{sign.nick},stu_no=#{sign.stuNo},avatar_url=#{sign.avatarUrl} where id=#{sign.id}")
    Long updateSign(@Param("sign") Sign sign);

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
     * 批量删除sign
     *
     * @param id 要删除的signId
     * @return 影响的行数
     */
    long deleteSign(@Param("id") List<Integer> id);

    /**
     * 根据CheckId获取签到信息
     *
     * @param checkId 获取签到信息的checkinId
     * @return 学生名
     */
    @Select("select signin.* from signin where signin.check_id=#{checkId}")
    List<Sign> getSignByCheckId(@Param("checkId") Integer checkId);

    /**
     * 根据checkinId和userId获取对应的signIn
     *
     * @param checkId 获取签到信息的checkInId
     * @param userId  用户id
     * @return 学生名
     */
    @Select("select signin.* from signin where check_id=#{checkId} and stu_id=#{userId}")
    Sign getSignByCheckIdAndUserId(@Param("checkId") Integer checkId, @Param("userId") Integer userId);
}
