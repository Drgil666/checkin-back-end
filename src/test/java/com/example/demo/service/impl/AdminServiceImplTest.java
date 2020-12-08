package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.pojo.Admin;
import com.example.demo.service.AdminService;
import org.junit.jupiter.api.Test;
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
    void createAdmin() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("123456");
        admin.setNick("test");
        adminService.createAdmin(admin);
        System.out.println(admin.getId());
    }

    @Test
    void updateAdmin() {
        Admin admin = adminService.getAdmin(1);
        admin.setPassword("1234567");
        System.out.println(adminService.updateAdmin(admin));
    }

    @Test
    void deleteAdmin() {
        adminService.deleteAdmin(2);
    }

    @Test
    void adminLogin() {
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
