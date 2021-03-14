package com.example.demo.service;

import com.example.demo.pojo.Academy;

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
    Boolean createAcademy(Academy academy);

    /**
     * 更新学院
     *
     * @param academy 要更新的学院
     * @return 影响的行数
     */
    Long updateAcademy(Academy academy);

    /**
     * 查找学院
     *
     * @param id 学院id
     * @return 对应的学院
     */
    Academy getAcademy(Integer id);

    /**
     * 批量删除学院
     *
     * @param id id的列表
     * @return 影响行数
     */
    Long deleteAcademy(List<Integer> id);
}
