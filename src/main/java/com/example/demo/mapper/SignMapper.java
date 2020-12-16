package com.example.demo.mapper;


import com.example.demo.pojo.CheckSet;
import com.example.demo.pojo.Sign;
import com.example.demo.pojo.vo.SignVO;
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
     * 根据signList中的checkId查找checkinList再查找checkSetList
     *
     * @param id 获取到的checkSetList数组
     * @return 查找的checkSetList
     */
    List<CheckSet> getCheckSetBySign(@Param("id") List<Integer> id);

    /**
     * 批量删除sign
     *
     * @param id 要删除的signid
     * @return 影响的行数
     */

    long deleteSign(@Param("id") List<Integer> id);

    /**
     * 获取签到信息
     *
     * @param checkId 获取签到信息的checkinId
     * @return 学生名
     */
    @Select("select signin.*,user.nick as nick,user.stu_no as stu_no from signin inner join user" +
            " on signin.stu_id = user.id where signin.check_id=#{checkId}")
    List<SignVO> getSignByCheckId(@Param("checkId") Integer checkId);

    /**
     * 根据一个checkin和userid获取对应的signIn
     *
     * @param checkId,userid 获取签到信息的checkinId和userid
     * @return 学生名
     */
    @Select("select signin.*,user.nick as nick,user.stu_no from signin inner join user on signin.stu_id=user.id where check_id=#{checkId} and stu_id=#{userId}")
    List<SignVO> getSignByCheckIdAndUserId(@Param("checkId") Integer checkId, @Param("userId") Integer userId);
}
