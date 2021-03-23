package com.example.demo.mapper;


import com.example.demo.pojo.CheckSet;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author chentao
 */
@Mapper
public interface CheckSetMapper {
    /**
     * 创建checkSet
     *
     * @param checkSet 要创建的checkSet
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into checkset (nick,user_id,visible) values (#{checkset.nick},#{checkset.userId},#{checkset.visible})")
    Boolean createCheckSet(@Param("checkset") CheckSet checkSet);

    /**
     * 更新签到
     *
     * @param checkSet 要更新的checkSet
     * @return 更新好的CheckSet
     */
    @Insert("update checkset set nick=#{checkset.nick},user_id=#{checkset.userId},visible=#{checkset.visible} where id=#{checkset.id}")
    Long updateCheckSet(@Param("checkset") CheckSet checkSet);

    /**
     * 根据id获取获取签到
     *
     * @param id 签到id
     * @return 对应的签到
     */
    @Select("select * from checkset where id=#{id}")
    CheckSet getCheckSet(@Param("id") Integer id);


    /**
     * 批量删除checkSet
     *
     * @param id 要删除的checkSetId
     * @return 变化的行数
     */
    Long deleteCheckSet(@Param("id") List<Integer> id);

    /**
     * 学生获取CheckSet列表
     *
     * @param stuId 学生id
     * @return CheckSet列表
     */
    @Select("select DISTINCT checkset.* from checkset,checkin,signin " +
            "where checkset.id=checkin.set_id and checkin.id=signin.check_id and signin.stu_id=#{stuId}")
    List<CheckSet> getCheckListByStu(@Param("stuId") Integer stuId);

    /**
     * 教师通过用户id和签到名获取签到列表
     *
     * @param userId 要查找的用户id
     * @param nick   签到名
     * @return 对应的checkSet列表
     */
    @Select("select * from checkset where user_id=#{userId} and nick like CONCAT('%',#{nick},'%')")
    List<CheckSet> getCheckSetListByTeacher(@Param("userId") Integer userId, @Param("nick") String nick);

    /**
     * 管理员端通过签到名获取签到列表
     *
     * @param nick 签到名
     * @return 对应的checkSet列表
     */
    @Select("select * from checkset where nick like CONCAT('%',#{nick},'%')")
    List<CheckSet> getCheckSetListByNickAdmin(@Param("nick") String nick);
}
