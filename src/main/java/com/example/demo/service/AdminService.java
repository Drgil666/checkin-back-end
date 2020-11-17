package com.example.demo.service;

import com.example.demo.pojo.Admin;

/**
 * @author chentao
 */

public interface AdminService {
    /**
     * 创建管理员账户
     *
     * @param admin 要更新的admin username账户名，password密码，nick昵称
     * @return 是否创建成功
     */
    boolean createAdmin(Admin admin);

    /**
     * 更新admin
     *
     * @param admin 要更新的admin
     * @return 更新好的admin
     */
    long updateAdmin(Admin admin);

    /**
     * 删除admin
     *
     * @param id 要删除的adminid
     * @return 影响的行数
     */
    long deleteAdmin(Integer id);

    /**
     * 获取管理员信息
     *
     * @param id 查找的管理员id
     * @return 获取到的管理员信息
     */
    Admin getAdmin(Integer id);
}
