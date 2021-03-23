package com.example.demo.service;

import com.example.demo.pojo.Major;
import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2021/3/14 20:11
 */
public interface MajorService {
    /**
     * 创建专业
     *
     * @param major 要创建的专业
     * @return 是否创建成功
     */
    Boolean createMajor(@NotNull Major major);

    /**
     * 更新专业
     *
     * @param major 要更新的major
     * @return 影响的行数
     */
    Long updateMajor(@NotNull Major major);

    /**
     * 获取专业
     *
     * @param id 专业id
     * @return 获取专业id
     */
    Major getMajor(@NotNull Integer id);

    /**
     * 批量删除专业
     *
     * @param id 专业的id
     * @return 影响的行数
     */
    Long deleteMajor(@NotNull List<Integer> id);

    /**
     * 根据学院id获取专业列表(允许其为空,表示全学院查找)
     *
     * @param id 学院id
     * @return 专业列表
     */
    List<Major> getMajorListByAcademyIdAndKeyWord(Integer id, String keyword);
}
