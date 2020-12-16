package com.example.demo.service;

import com.example.demo.pojo.CheckSet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chentao
 */
public interface CheckSetService {
    /**
     * 创建checkset
     *
     * @param checkset 要创建的checkset
     * @return 是否创建成功
     */

    boolean createCheckSet(CheckSet checkset);

    /**
     * 更新签到
     *
     * @param checkset 要更新的checkset
     * @return 更新好的CheckSet
     */

    long updateCheckSet(CheckSet checkset);

    /**
     * 根据id获取获取签到
     *
     * @param id 签到id
     * @return 对应的签到
     */

    CheckSet getCheckSet(Integer id);

    /**
     * 根据用户id获取签到列表
     *
     * @param userId 要查找的用户id
     * @return 对应的checkset列表
     */

    List<CheckSet> getCheckSetList(Integer userId);

    /**
     * 根据nick查找checkset
     *
     * @param nick 要查找你的昵称
     * @return 对应的checkset
     */

    List<CheckSet> getCheckSetNick(String nick);

    /**
     * 批量删除checkset
     *
     * @param id 要删除的checksetId
     * @return 变化的行数
     */
    long deleteCheckSet(@Param("id") List<Integer> id);
}
