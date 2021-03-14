package com.example.demo.service.impl;

import com.example.demo.dao.BcryptDao;
import com.example.demo.service.BcryptService;
import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Gilbert
 * @date 2020/12/2 8:37
 */
@Service
public class BcryptServiceImpl implements BcryptService {
    @Resource
    private BcryptDao bcryptDao;

    /**
     * 密码加密
     *
     * @param password 要加密的密码
     * @return 加密好的密码
     */
    @Override
    public String encode(@NotNull String password) {
        return bcryptDao.encode(password);
    }

    /**
     * 比对密码是否正确
     *
     * @param password 明文
     * @param hash     密文
     * @return 是否匹配
     */
    @Override
    public Boolean checkPassword(@NotNull String password, @NotNull String hash) {
        return bcryptDao.checkPassword(password, hash);
    }
}
