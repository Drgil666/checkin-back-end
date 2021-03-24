package com.example.demo.service.impl;

import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.AcademyMapper;
import com.example.demo.pojo.Academy;
import com.example.demo.service.AcademyService;
import com.example.demo.utils.AssertionUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2021/3/14 20:09
 */
@Service
public class AcademyServiceImpl implements AcademyService {
    @Resource
    private AcademyMapper academyMapper;

    /**
     * 创建学院
     *
     * @param academy 要创建的学院
     * @return 是否创建成功
     */
    @Override
    public Boolean createAcademy(@NotNull Academy academy) {
        AssertionUtil.notNull(academy, ErrorCode.BIZ_PARAM_ILLEGAL, "academy不能为空!");
        return academyMapper.createAcademy(academy);
    }

    /**
     * 更新学院
     *
     * @param academy 要更新的学院
     * @return 影响的行数
     */
    @Override
    public Long updateAcademy(@NotNull Academy academy) {
        AssertionUtil.notNull(academy.getId(), ErrorCode.BIZ_PARAM_ILLEGAL, "academy的id不能为空!");
        return academyMapper.updateAcademy(academy);
    }

    /**
     * 查找学院
     *
     * @param id 学院id
     * @return 对应的学院
     */
    @Override
    public Academy getAcademy(@NotNull Integer id) {
        AssertionUtil.notNull(id, ErrorCode.BIZ_PARAM_ILLEGAL, "id不能为空!");
        return academyMapper.getAcademy(id);
    }

    /**
     * 批量删除学院
     *
     * @param id id的列表
     * @return 影响行数
     */
    @Override
    public Long deleteAcademy(@NotNull List<Integer> id) {
        AssertionUtil.notNull(id, ErrorCode.BIZ_PARAM_ILLEGAL, "id不能为空!");
        return academyMapper.deleteAcademy(id);
    }

    /**
     * 根据学院名查找学院列表
     *
     * @param keyword 学院名
     * @return 学院列表
     */
    @Override
    public List<Academy> getAcademyListByKeyword(@NotNull String keyword) {
        return academyMapper.getAcademyListByKeyword(keyword);
    }

    /**
     * 根据专业id获取学院
     *
     * @param id 专业id
     * @return 学院
     */
    @Override
    public Academy getAcademyByMajorId(@NotNull Integer id) {
        return academyMapper.getAcademyByMajorId(id);
    }

    /**
     * 根据学校id获取学院列表
     *
     * @param id 学校id
     * @return 学院列表
     */
    @Override
    public List<Academy> getAcademyListBySchoolId(@NotNull Integer id) {
        return academyMapper.getAcademyListBySchoolId(id);
    }
}
