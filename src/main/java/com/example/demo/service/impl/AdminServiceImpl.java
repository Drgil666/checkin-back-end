package com.example.demo.service.impl;

import com.example.demo.dao.BcryptDao;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.pojo.Admin;
import com.example.demo.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chentao
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private BcryptDao bcryptDao;

    /**
     * 创建管理员账户
     *
     * @param admin 要更新的管理员
     * @return 是否创建成功
     */
    @Override
    public Boolean createAdmin(Admin admin) {
        admin.setPassword(bcryptDao.encode(admin.getPassword()));
        return adminMapper.createAdmin(admin);
    }

    /**
     * 更新admin
     *
     * @param admin 要更新的admin
     * @return 更新好的admin
     */
    @Override
    public Long updateAdmin(Admin admin) {
        admin.setPassword(bcryptDao.encode(admin.getPassword()));
        return adminMapper.updateAdmin(admin);
    }

    /**
     * 删除admin
     *
     * @param id 要删除的admin的Id
     * @return 影响行数
     */
    @Override
    public Long deleteAdmin(Integer id) {
        return adminMapper.deleteAdmin(id);
    }

    /**
     * 根据id获取获取管理员账户信息
     *
     * @param id 签到id
     * @return 对应的管理员账户信息
     */
    @Override
    public Admin getAdmin(Integer id) {
        return adminMapper.getAdmin(id);
    }

    /**
     * 管理员登录
     *
     * @param username 登录的管理员用户名
     * @return 对应的管理员
     */
    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.getAdminByUsername(username);
    }

    /**
     * 用户名是否重复
     *
     * @param username 用户名
     * @return 是否存在
     */
    @Override
    public Boolean adminExist(String username) {
        return adminMapper.adminExist(username) > 0;
    }
}
