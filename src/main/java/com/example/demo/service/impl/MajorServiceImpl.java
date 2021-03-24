package com.example.demo.service.impl;

import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.MajorMapper;
import com.example.demo.pojo.Major;
import com.example.demo.service.MajorService;
import com.example.demo.utils.AssertionUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2021/3/14 20:12
 */
@Service
public class MajorServiceImpl implements MajorService {
    @Resource
    private MajorMapper majorMapper;

    /**
     * 创建专业
     *
     * @param major 要创建的专业
     * @return 是否创建成功
     */
    @Override
    public Boolean createMajor(@NotNull Major major) {
        AssertionUtil.notNull(major, ErrorCode.BIZ_PARAM_ILLEGAL, "major不能为空!");
        return majorMapper.createMajor(major);
    }

    /**
     * 更新专业
     *
     * @param major 要更新的major
     * @return 影响的行数
     */
    @Override
    public Long updateMajor(@NotNull Major major) {
        AssertionUtil.notNull(major.getId(), ErrorCode.BIZ_PARAM_ILLEGAL, "major的id不能为空!");
        return majorMapper.updateMajor(major);
    }

    /**
     * 获取专业
     *
     * @param id 专业id
     * @return 获取专业id
     */
    @Override
    public Major getMajor(@NotNull Integer id) {
        AssertionUtil.notNull(id, ErrorCode.BIZ_PARAM_ILLEGAL, "id不能为空!");
        return majorMapper.getMajor(id);
    }

    /**
     * 批量删除专业
     *
     * @param id 专业的id
     * @return 影响的行数
     */
    @Override
    public Long deleteMajor(@NotNull List<Integer> id) {
        AssertionUtil.notNull(id, ErrorCode.BIZ_PARAM_ILLEGAL, "id不能为空!");
        return majorMapper.deleteMajor(id);
    }

    /**
     * 根据学院id获取专业列表
     *
     * @param id 学院id
     * @return 专业列表
     */
    @Override
    public List<Major> getMajorListByAcademyIdAndKeyWord(@NotNull Integer id, @NotNull String keyword) {
        return majorMapper.getMajorListByAcademyIdAndKeyWord(id, keyword);
    }
}
