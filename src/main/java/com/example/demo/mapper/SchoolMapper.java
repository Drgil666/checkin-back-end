package com.example.demo.mapper;

import com.example.demo.pojo.School;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Gilbert
 * @date 2021/3/14 11:36
 */
@Mapper
public interface SchoolMapper {
    /**
     * 创建学校
     *
     * @param school 要创建的学校
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into school (nick) VALUES (#{school.nick})")
    Boolean createSchool(@Param("school") School school);

    /**
     * 更新学校
     *
     * @param school 要更新的学校
     * @return 更新好的学校
     */
    @Update("update school set nick=#{school.nick} where id=#{id}")
    Long updateSchool(@Param("school") School school);

    /**
     * 获取学校
     *
     * @param id 学校id
     * @return 对应的学校
     */
    @Select("select * from school where id=#{id}")
    School getSchool(@Param("id") Integer id);

    /**
     * 批量删除学校
     *
     * @param id 要删除的id列表
     * @return 影响的行数
     */
    Long deleteSchool(@Param("id") List<Integer> id);

    /**
     * 根据名称查找学校
     *
     * @param keyword 学校名
     * @return 学校列表
     */
    @Select("select * from school where nick like CONCAT('%',#{keyword},'%')")
    List<School> getSchoolListByKeyword(@Param("keyword") String keyword);

    /**
     * 根据学院id查找学校
     *
     * @param id 学院id
     * @return 学校
     */
    @Select("select * from school inner join academy on academy.school_id=#{id}")
    School getSchoolByAcademyId(@Param("id") Integer id);

    /**
     * 根据专业id查找学校
     *
     * @param id 专业id
     * @return 学校
     */
    @Select("select * from school inner join academy on school.id = academy.school_id " +
            "inner join major on major.academy_id=academy.id")
    School getSchoolByMajorId(@Param("id") Integer id);
}
