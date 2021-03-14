package com.example.demo.service.impl;

import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.AcademyMapper;
import com.example.demo.pojo.Academy;
import com.example.demo.service.AcademyService;
import com.example.demo.utils.AssertionUtil;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2021/3/14 20:09
 */
public class AcademyServiceImpl implements AcademyService {
    private AcademyMapper academyMapper;

    /**
     * 创建学院
     *
     * @param academy 要创建的学院
     * @return 是否创建成功
     */
    @Override
    public Boolean createAcademy(Academy academy) {
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
    public Long updateAcademy(Academy academy) {
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
    public Academy getAcademy(Integer id) {
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
    public Long deleteAcademy(List<Integer> id) {
        AssertionUtil.notNull(id, ErrorCode.BIZ_PARAM_ILLEGAL, "id不能为空!");
        return academyMapper.deleteAcademy(id);
    }
}
