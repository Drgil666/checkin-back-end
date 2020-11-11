package com.example.demo.dao;

import com.example.demo.pojo.CheckIn;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author chentao
 */
@Mapper
public interface CheckInMapper {
    /**
     * 创建签到
     *
     * @param checkin 要更新的checkin ,user_id,course_id,start_time,end_time,status要加入的用户id,课程id,签到时间,签退时间和签到类型
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into checkin (user_id,start_time,end_time,status,type,nick,visible) values (#{checkin.userId},#{checkin.startTime},#{checkin.endTime},#{checkin.status},#{checkin.type},#{checkin.nick},#{checkin.visible})")
    boolean createCheckIn(@Param("checkin") CheckIn checkin);

    /**
     * 更新签到
     *
     * @param checkin 要更新的checkin
     * @return 更新好的CheckIn
     */
    @Insert("update checkin set user_id=#{checkin.userId},start_time=#{checkin.startTime},end_time=#{checkin.endTime},status=#{checkin.status},type=#{checkin.type} where id=#{checkin.id}")
    long updateCheckIn(@Param("checkin") CheckIn checkin);

    /**
     * 根据id获取获取签到
     *
     * @param id 签到id
     * @return 对应的签到
     */
    @Select("select * from checkin where id=#{id}")
    CheckIn getCheckIn(@Param("id") Integer id);

    /**
     * 根据用户id获取签到列表
     *
     * @param userId 要查找的用户id
     * @return 对应的checkin列表
     */
    @Select("select * from checkin where user_id=#{userId}")
    List<CheckIn> getCheckInList(@Param("userId") Integer userId);

    /**
     * 根据nick查找checkin
     *
     * @param nick 要查找你的昵称
     * @return 对应的checkin
     */
    @Select("select * from checkin where nick=#{nick}")
    List<CheckIn> getCheckInNick(@Param("nick") String nick);

    /**
     * 批量删除checkin
     *
     * @param id 要删除的checkinId
     * @return 变化的行数
     */
    long deleteCheckIn(@Param("id") List<Integer> id);
}
