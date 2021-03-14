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
     * 获取全体学校列表
     *
     * @return 对应的学校
     */
    @Select("select * from school")
    List<School> getSchoolList();
}
