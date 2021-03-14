package com.example.demo.service.impl;

import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.MajorMapper;
import com.example.demo.pojo.Major;
import com.example.demo.service.MajorService;
import com.example.demo.utils.AssertionUtil;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2021/3/14 20:12
 */
public class MajorServiceImpl implements MajorService {
    private MajorMapper majorMapper;

    /**
     * 创建专业
     *
     * @param major 要创建的专业
     * @return 是否创建成功
     */
    @Override
    public Boolean createMajor(Major major) {
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
    public Long updateMajor(Major major) {
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
    public Major getMajor(Integer id) {
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
    public Long deleteMajor(List<Integer> id) {
        AssertionUtil.notNull(id, ErrorCode.BIZ_PARAM_ILLEGAL, "id不能为空!");
        return majorMapper.deleteMajor(id);
    }
}
