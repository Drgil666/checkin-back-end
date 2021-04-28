package com.example.demo.service;

import com.example.demo.pojo.Academy;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2021/3/14 20:08
 */
public interface AcademyService {
    /**
     * 创建学院
     *
     * @param academy 要创建的学院
     * @return 是否创建成功
     */
    Boolean createAcademy(@NotNull Academy academy);

    /**
     * 更新学院
     *
     * @param academy 要更新的学院
     * @return 影响的行数
     */
    Long updateAcademy(@NotNull Academy academy);

    /**
     * 查找学院
     *
     * @param id 学院id
     * @return 对应的学院
     */
    Academy getAcademy(@NotNull Integer id);

    /**
     * 批量删除学院
     *
     * @param id id的列表
     * @return 影响行数
     */
    Long deleteAcademy(@NotNull List<Integer> id);

    /**
     * 根据学院名和学校id查找学院列表
     *
     * @param schoolId 学校id
     * @param keyword  学院名
     * @return 学院列表
     */
    List<Academy> getAcademyListByKeyword(@NotNull Integer schoolId, @NotNull String keyword);

    /**
     * 根据专业id获取学院
     *
     * @param id 专业id
     * @return 学院
     */
    Academy getAcademyByMajorId(@NotNull Integer id);
}
