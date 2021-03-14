package com.example.demo.service;

import com.example.demo.pojo.School;

import java.util.List;

/**
 * @author DrGilbert
 */
public interface SchoolService {
    /**
     * 创建学校
     *
     * @param school 要创建的学校
     * @return 是否创建成功
     */
    Boolean createSchool(School school);

    /**
     * 更新学校
     *
     * @param school 要更新的学校
     * @return 更新好的学校
     */
    Long updateSchool(School school);

    /**
     * 获取学校
     *
     * @param id 学校id
     * @return 对应的学校
     */
    School getSchool(Integer id);

    /**
     * 批量删除学校
     *
     * @param id 要删除的id列表
     * @return 影响的行数
     */
    Long deleteSchool(List<Integer> id);

    /**
     * 获取全体学校列表
     *
     * @return 对应的学校
     */
    List<School> getSchoolList();
}
