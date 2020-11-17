package com.example.demo.dao;


import com.example.demo.pojo.CheckSet;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author chentao
 */
@Mapper
public interface CheckSetMapper {
    /**
     * 创建checkset
     *
     * @param checkset 要创建的checkset
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into checkset (nick,user_id,visible) values (#{checkset.nick},#{checkset.userId},#{checkset.visible})")
    boolean createCheckSet(@Param("checkset") CheckSet checkset);

    /**
     * 更新签到
     *
     * @param checkset 要更新的checkset
     * @return 更新好的CheckSet
     */
    @Insert("update checkset set id=#{checkset.id},nick=#{checkset.nick},user_id=#{checkset.userId},visible=#{checkset.visible} where id=#{checkset.id}")
    long updateCheckSet(@Param("checkset") CheckSet checkset);

    /**
     * 根据id获取获取签到
     *
     * @param id 签到id
     * @return 对应的签到
     */
    @Select("select * from checkset where id=#{id}")
    CheckSet getCheckSet(@Param("id") Integer id);

    /**
     * 根据用户id获取签到列表
     *
     * @param userId 要查找的用户id
     * @return 对应的checkset列表
     */
    @Select("select * from checkSet where user_id=#{userId}")
    List<CheckSet> getCheckSetList(@Param("userId") Integer userId);

    /**
     * 根据nick查找checkset
     *
     * @param nick 要查找你的昵称
     * @return 对应的checkin
     */
    @Select("select * from checkset where nick=#{nick}")
    List<CheckSet> getCheckSetNick(@Param("nick") String nick);

    /**
     * 批量删除checkset
     *
     * @param id 要删除的checksetId
     * @return 变化的行数
     */
    long deleteCheckSet(@Param("id") List<Integer> id);
}
