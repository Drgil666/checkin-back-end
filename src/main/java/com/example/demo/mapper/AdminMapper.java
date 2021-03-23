package com.example.demo.mapper;

import com.example.demo.pojo.Admin;
import org.apache.ibatis.annotations.*;

/**
 * @author chentao
 */
@Mapper
public interface AdminMapper {
    /**
     * 创建管理员账户
     *
     * @param admin 要更新的管理员
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into admin (username,password,nick,type,school_id)" +
            " values (#{admin.username},#{admin.password},#{admin.nick},#{admin.type},#{admin.schoolId})")
    Boolean createAdmin(@Param("admin") Admin admin);

    /**
     * 更新admin
     *
     * @param admin 要更新的admin
     * @return 更新好的admin
     */
    @Insert("update admin set id=#{admin.id}," +
            "username=#{admin.username},password=#{admin.password}," +
            "nick=#{admin.nick},type=#{admin.type},school_id=#{admin.schoolId} where id=#{admin.id}")
    Long updateAdmin(@Param("admin") Admin admin);

    /**
     * 删除admin
     *
     * @param id 要删除的admin的Id
     * @return 影响行数
     */
    @Delete("delete from admin where id=#{id}")
    Long deleteAdmin(@Param("id") Integer id);

    /**
     * 根据id获取获取管理员账户信息
     *
     * @param id 签到id
     * @return 对应的管理员账户信息
     */
    @Select("select * from admin where id=#{id}")
    Admin getAdmin(@Param("id") Integer id);

    /**
     * 管理员登录
     *
     * @param username 登录的管理员用户名
     * @return 对应的管理员
     */
    @Select("select * from admin where username=#{username}")
    Admin getAdminByUsername(@Param("username") String username);

    /**
     * 用户名是否重复
     *
     * @param username 用户名
     * @return 是否存在
     */
    @Select("select count(*) from admin where username=#{username} LIMIT 1")
    Integer adminExist(@Param("username") String username);
}
