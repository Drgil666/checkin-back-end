package com.example.demo.mapper;

import com.example.demo.pojo.Major;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author DrGilbert
 */
@Mapper
public interface MajorMapper {
    /**
     * 创建专业
     *
     * @param major 要创建的专业
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into major (nick, academy_id) " +
            "values (#{major.nick},#{major.academyId})")
    Boolean createMajor(@Param("major") Major major);

    /**
     * 更新专业
     *
     * @param major 要更新的major
     * @return 影响的行数
     */
    @Update("update major set nick=#{major.nick}," +
            "academy_id=#{major.academyId} where id=#{major.id}")
    Long updateMajor(@Param("major") Major major);

    /**
     * 获取专业
     *
     * @param id 专业id
     * @return 获取专业id
     */
    @Select("select * from major where id=#{id};")
    Major getMajor(@Param("id") Integer id);

    /**
     * 批量删除专业
     *
     * @param id 专业的id
     * @return 影响的行数
     */
    Long deleteMajor(@Param("id") List<Integer> id);

    /**
     * 根据学院id获取专业列表
     *
     * @param id      学院id
     * @param keyword 专业名
     * @return 专业列表
     */
    @Select("select * from major where academy_id=#{id} and nick like CONCAT('%',#{keyword},'%')")
    List<Major> getMajorListByAcademyIdAndKeyWord(@Param("id") Integer id, @Param("keyword") String keyword);

}
