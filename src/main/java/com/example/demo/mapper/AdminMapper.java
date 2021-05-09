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
    @Insert("insert into admin (username,password,nick,type,open_id)" +
            " values (#{admin.username},#{admin.password},#{admin.nick},#{admin.type},#{admin.openId})")
    Boolean createAdmin(@Param("admin") Admin admin);

    /**
     * 更新admin
     *
     * @param admin 要更新的admin
     * @return 更新好的admin
     */
    @Insert("update admin set id=#{admin.id}," +
            "username=#{admin.username},password=#{admin.password}," +
            "nick=#{admin.nick},type=#{admin.type},open_id=#{admin.openId} where id=#{admin.id}")
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
    Admin getAdminById(@Param("id") Integer id);

    /**
     * 根据openId获取管理员
     *
     * @param openId openId
     * @return 管理员账户信息
     */
    @Select("select * from admin where open_id=#{openId}")
    Admin getAdminByOpenId(@Param("openId") String openId);

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
    Integer adminExistByUsername(@Param("username") String username);

    /**
     * openId是否已被绑定
     *
     * @param openId openId
     * @return 是否被绑定
     */
    @Select("select count(*) from admin where open_id=#{openId} LIMIT 1")
    Integer adminExistByOpenId(@Param("openId") String openId);

    /**
     * 根据用户邮箱查找管理员
     *
     * @param mail 邮箱
     * @return 管理员信息
     */
    @Select("select admin.* from admin,user where admin.open_id=user.username and user.mail=#{mail}")
    Admin getAdminByMail(@Param("mail") String mail);
}
