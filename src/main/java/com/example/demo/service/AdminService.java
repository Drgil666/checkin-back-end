package com.example.demo.service;

import com.example.demo.pojo.Admin;

/**
 * @author chentao
 */

public interface AdminService {
    /**
     * 创建管理员账户
     *
     * @param admin 要更新的管理员
     * @return 是否创建成功
     */
    Boolean createAdmin(Admin admin);

    /**
     * 更新admin
     *
     * @param admin 要更新的admin
     * @return 更新好的admin
     */
    Long updateAdmin(Admin admin);

    /**
     * 删除admin
     *
     * @param id 要删除的admin的Id
     * @return 影响行数
     */
    Long deleteAdmin(Integer id);

    /**
     * 根据id获取获取管理员账户信息
     *
     * @param id 签到id
     * @return 对应的管理员账户信息
     */
    Admin getAdmin(Integer id);

    /**
     * 管理员登录
     *
     * @param username 登录的管理员用户名
     * @return 对应的管理员
     */
    Admin getAdminByUsername(String username);

    /**
     * 用户名是否重复
     *
     * @param username 用户名
     * @return 是否存在
     */
    Integer adminExist(String username);
}
