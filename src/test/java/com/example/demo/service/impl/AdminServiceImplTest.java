package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.pojo.Admin;
import com.example.demo.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * @author chentao
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class AdminServiceImplTest {
    @Resource
    private AdminService adminService;

    @Test
    public void createAdmin() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("123456");
        admin.setNick("ct");
        adminService.createAdmin(admin);
        System.out.println(admin.getId());
    }

    @Test
    public void updateAdmin() {
        Admin admin = adminService.getAdmin(1);
        admin.setPassword("1234567ct");
        System.out.println(adminService.updateAdmin(admin));
    }

    @Test
    public void deleteAdmin() {
        adminService.deleteAdmin(2);
    }

    @Test
    public void adminLogin() {
        String username = "admin";
        String password = "12345677";
        Admin admin = adminService.getAdminByUsername(username);
        if (admin != null) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }
    }
}
