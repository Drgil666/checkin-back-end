package com.example.demo.mapper;

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
     * @param checkin 要更新的checkin ,start_time,end_time,status,setid要加入的签到时间,签退时间和签到类型
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into checkin (start_time,end_time,status,type,visible,set_id) values (#{checkin.startTime},#{checkin.endTime},#{checkin.status},#{checkin.type},#{checkin.visible},#{checkin.setId})")
    boolean createCheckIn(@Param("checkin") CheckIn checkin);

    /**
     * 更新签到
     *
     * @param checkin 要更新的checkin
     * @return 更新好的CheckIn
     */
    @Insert("update checkin set id=#{checkin.id},start_time=#{checkin.startTime},end_time=#{checkin.endTime},status=#{checkin.status},type=#{checkin.type},visible=#{checkin.visible},set_id=#{checkin.setId} where id=#{checkin.id}")
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
     * 根据setId获取checkin列表
     *
     * @param setId 对应的checkset的id
     * @return 对应的checkin列表
     */
    @Select("select * from checkin where set_id=#{setId}")
    List<CheckIn> getCheckInListBySetId(@Param("setId") Integer setId);

    /**
     * 批量删除checkin
     *
     * @param id 要删除的checkinId
     * @return 变化的行数
     */
    long deleteCheckIn(@Param("id") List<Integer> id);

}
