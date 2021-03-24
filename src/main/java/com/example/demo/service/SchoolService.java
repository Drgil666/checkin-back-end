package com.example.demo.service;

import com.example.demo.pojo.School;
import org.jetbrains.annotations.NotNull;

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
    Boolean createSchool(@NotNull School school);

    /**
     * 更新学校
     *
     * @param school 要更新的学校
     * @return 更新好的学校
     */
    Long updateSchool(@NotNull School school);

    /**
     * 获取学校
     *
     * @param id 学校id
     * @return 对应的学校
     */
    School getSchool(@NotNull Integer id);

    /**
     * 批量删除学校
     *
     * @param id 要删除的id列表
     * @return 影响的行数
     */
    Long deleteSchool(@NotNull List<Integer> id);

    /**
     * 根据名称查找学校
     *
     * @param keyword 学校名
     * @return 学校列表
     */
    List<School> getSchoolListByKeyword(@NotNull String keyword);

    /**
     * 根据学院id查找学校
     *
     * @param id 学院id
     * @return 学校
     */
    School getSchoolByAcademyId(@NotNull Integer id);

    /**
     * 根据专业id查找学校
     *
     * @param id 专业id
     * @return 学校
     */
    School getSchoolByMajorId(@NotNull Integer id);
}
