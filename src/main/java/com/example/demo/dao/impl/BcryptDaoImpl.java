package com.example.demo.dao.impl;

import com.example.demo.dao.BcryptDao;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * @author Gilbert
 * @date 2020/12/2 8:34
 */
@Component("BcryptDaoImpl")
public class BcryptDaoImpl implements BcryptDao {

    /**
     * 密码加密
     *
     * @param password 要加密的密码
     * @return 加密好的密码
     */
    @Override
    public String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(11));
    }

    /**
     * 比对密码是否正确
     *
     * @param password 明文
     * @param hash     密文
     * @return 是否匹配
     */
    @Override
    public Boolean checkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
