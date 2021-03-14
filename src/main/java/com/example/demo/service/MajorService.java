package com.example.demo.service;

import com.example.demo.pojo.Major;

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
    Boolean createMajor(Major major);

    /**
     * 更新专业
     *
     * @param major 要更新的major
     * @return 影响的行数
     */
    Long updateMajor(Major major);

    /**
     * 获取专业
     *
     * @param id 专业id
     * @return 获取专业id
     */
    Major getMajor(Integer id);

    /**
     * 批量删除专业
     *
     * @param id 专业的id
     * @return 影响的行数
     */
    Long deleteMajor(List<Integer> id);
}
