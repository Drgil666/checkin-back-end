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
    @Insert("insert into user (username,stu_no,mail,status,photo_id,nick,school,academy,major) values  (#{user.username},#{user.stuNo},#{user.mail},#{user.status.code},#{user.photoId},#{user.nick},#{user.school},#{user.academy},#{user.major})")
    boolean createUser(@Param("user") User user);

    /**
     * 更新用户
     *
     * @param user 要更新的User
     * @return 更新好的User
     */
    @Update("update user set username=#{user.username},stu_no=#{user.stuNo},mail=#{user.mail},status=#{user.status.code},photo_id=#{user.photoId},nick=#{user.nick},school=#{user.school},academy=#{user.academy},major=#{user.major} where id=#{user.id}")
    long updateUser(@Param("user") User user);

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    @Select("select * from user where id=#{id}")
    User getUser(@Param("id") Integer id);

    /**
     * 查询用户名是否存在
     *
     * @param username 用户名
     * @return 对应id
     */
    @Select("select id from user where username=#{username} limit 1")
    Integer isExist(@Param("username") String username);
}
