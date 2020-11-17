package com.example.demo.dao;

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
     * @param admin 要更新的admin username账户名，password密码，nick昵称
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into admin (username,password,nick) values (#{admin.username},#{admin.password},#{admin.nick})")
    boolean createAdmin(@Param("admin") Admin admin);

    /**
     * 更新admin
     *
     * @param admin 要更新的admin
     * @return 更新好的admin
     */
    @Insert("update admin set id=#{admin.id},username=#{admin.username},password=#{admin.password},nick=#{admin.nick} where id=#{admin.id}")
    long updateAdmin(@Param("admin") Admin admin);

    /**
     * 删除admin
     *
     * @param id 要删除的adminid
     */
    @Delete("delete from admin where id=#{id}")
    long deleteAdmin(@Param("id") Integer id);

    /**
     * 根据id获取获取管理员账户信息
     *
     * @param id 签到id
     * @return 对应的管理员账户信息
     */
    @Select("select * from admin where id=#{id}")
    Admin getAdmin(@Param("id") Integer id);
}
