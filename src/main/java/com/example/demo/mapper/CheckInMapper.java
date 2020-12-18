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
     * @param checkIn 要更新的checkin
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into checkin (start_time,end_time,status,type,visible,set_id) values (#{checkin.startTime},#{checkin.endTime},#{checkin.status},#{checkin.type},#{checkin.visible},#{checkin.setId})")
    Boolean createCheckIn(@Param("checkin") CheckIn checkIn);

    /**
     * 更新签到
     *
     * @param checkIn 要更新的checkin
     * @return 更新好的CheckIn
     */
    @Insert("update checkin set id=#{checkin.id},start_time=#{checkin.startTime},end_time=#{checkin.endTime},status=#{checkin.status},type=#{checkin.type},visible=#{checkin.visible},set_id=#{checkin.setId} where id=#{checkin.id}")
    Long updateCheckIn(@Param("checkin") CheckIn checkIn);

    /**
     * 根据id获取获取签到
     *
     * @param id 签到id
     * @return 对应的签到
     */
    @Select("select * from checkin where id=#{id}")
    CheckIn getCheckIn(@Param("id") Integer id);

    /**
     * 根据setId获取checkIn列表
     *
     * @param setId 对应的checkSet的id
     * @return 对应的checkIn列表
     */
    @Select("select * from checkin where set_id=#{setId}")
    List<CheckIn> getCheckInListBySetId(@Param("setId") Integer setId);

    /**
     * 学生获取CheckIn列表
     *
     * @param setId  checkSetId
     * @param userId 用户id
     * @return 对应的checkIn列表
     */
    @Select("select DISTINCT checkin.* from checkin,signin " +
            "where checkin.set_id=#{setId} and signin.stu_id=#{userId} and signin.check_id=checkin.id")
    List<CheckIn> getCheckInListByStu(@Param("setId") Integer setId, @Param("userId") Integer userId);

    /**
     * 批量删除checkin
     *
     * @param id 要删除的checkinId
     * @return 变化的行数
     */
    Long deleteCheckIn(@Param("id") List<Integer> id);

    /**
     * 某个用户是否已完成某场签到
     *
     * @param userId  用户id
     * @param checkId 签到id
     * @return 是否完成签到
     */
    @Select("select count(*) from signin where signin.stu_id=#{userId} and signin.check_id=#{checkId} LIMIT 1")
    Long isSign(@Param("userId") Integer userId, @Param("checkId") Integer checkId);
}
