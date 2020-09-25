package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.*;

/**
 * @author Gilbert
 * @date 2020/9/24 15:31
 */
@Mapper
public interface UserMapper {
    /**
     * 创建用户
     *
     * @param user 要注册的用户
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into user (username,password,stu_no,mail,status,photo_id,nick,school,academy,major,friend_id,avatar) values  (#{user.username},#{user.password},#{user.stuNo},#{user.mail},#{user.status.code},#{user.photoId},#{user.nick},#{user.school},#{user.academy},#{user.major},#{user.friendId},#{user.avatar})")
    boolean createUser(@Param("user") User user);

    /**
     * 更新用户
     *
     * @param user 要更新的User
     * @return 更新好的User
     */
    @Update("update user set username=#{user.username},password=#{user.password},stu_no=#{user.stuNo},mail=#{user.mail},status=#{user.status.code},photo_id=#{user.photoId},nick=#{user.nick},school=#{user.school},academy=#{user.academy},major=#{user.major},friend_id=#{user.friendId},avatar=#{user.avatar} where id=#{user.id}")
    long updateUser(@Param("user") User user);

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    @Select("select * from user where id=#{id}")
    User getUser(@Param("id") Integer id);
}
