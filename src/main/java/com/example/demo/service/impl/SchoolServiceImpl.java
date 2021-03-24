package com.example.demo.service.impl;

import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.SchoolMapper;
import com.example.demo.pojo.School;
import com.example.demo.service.SchoolService;
import com.example.demo.utils.AssertionUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 */
@Service
public class SchoolServiceImpl implements SchoolService {
    @Resource
    private SchoolMapper schoolMapper;

    /**
     * 创建学校
     *
     * @param school 要创建的学校
     * @return 是否创建成功
     */
    @Override
    public Boolean createSchool(@NotNull School school) {
        AssertionUtil.notNull(school, ErrorCode.BIZ_PARAM_ILLEGAL, "school不能为空！");
        return schoolMapper.createSchool(school);
    }

    /**
     * 更新学校
     *
     * @param school 要更新的学校
     * @return 更新好的学校
     */
    @Override
    public Long updateSchool(@NotNull School school) {
        AssertionUtil.notNull(school.getId(), ErrorCode.BIZ_PARAM_ILLEGAL, "school的id不能为空！");
        return schoolMapper.updateSchool(school);
    }

    /**
     * 获取学校
     *
     * @param id 学校id
     * @return 对应的学校
     */
    @Override
    public School getSchool(@NotNull Integer id) {
        AssertionUtil.notNull(id, ErrorCode.BIZ_PARAM_ILLEGAL, "id不能为空！");
        return schoolMapper.getSchool(id);
    }

    /**
     * 批量删除学校
     *
     * @param id 要删除的id列表
     * @return 影响的行数
     */
    @Override
    public Long deleteSchool(@NotNull List<Integer> id) {
        AssertionUtil.notNull(id, ErrorCode.BIZ_PARAM_ILLEGAL, "id不能为空!");
        return schoolMapper.deleteSchool(id);
    }

    /**
     * 根据名称查找学校
     *
     * @param keyword 学校名
     * @return 学校列表
     */
    @Override
    public List<School> getSchoolListByKeyword(@NotNull String keyword) {
        return schoolMapper.getSchoolListByKeyword(keyword);
    }

    /**
     * 根据学院id查找学校
     *
     * @param id 学院id
     * @return 学校
     */
    @Override
    public School getSchoolByAcademyId(@NotNull Integer id) {
        return schoolMapper.getSchoolByAcademyId(id);
    }

    /**
     * 根据专业id查找学校
     *
     * @param id 专业id
     * @return 学校
     */
    @Override
    public School getSchoolByMajorId(@NotNull Integer id) {
        return schoolMapper.getSchoolByMajorId(id);
    }

}
