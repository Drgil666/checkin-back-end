package com.example.demo.service.impl;

import com.example.demo.dao.AdminMapper;
import com.example.demo.pojo.Admin;
import com.example.demo.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    /**
     * 创建管理员账户
     *
     * @param admin 要更新的admin username账户名，password密码，nick昵称
     * @return 是否创建成功
     */
    @Override
    public boolean createAdmin(Admin admin) {
        return adminMapper.createAdmin(admin);
    }

    /**
     * 更新admin
     *
     * @param admin 要更新的admin
     * @return 更新好的admin
     */
    @Override
    public long updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }

    /**
     * 删除admin
     *
     * @param id 要删除的adminid
     */
    @Override
    public long deleteAdmin(Integer id) {
        return adminMapper.deleteAdmin(id);
    }

    /**
     * 获取管理员信息
     */
    @Override
    public Admin getAdmin(Integer id) {
        return adminMapper.getAdmin(id);
    }

    /**
     * 管理员登录
     *
     * @param username 登录的管理员用户名
     * @param password 登录的管理员密码
     * @return 是否登录成功
     */
    @Override
    public Admin adminLogin(String username, String password) {
        return adminMapper.adminLogin(username, password);
    }
}
