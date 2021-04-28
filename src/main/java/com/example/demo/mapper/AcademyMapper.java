package com.example.demo.mapper;

import com.example.demo.pojo.Academy;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Gilbert
 * @date 2021/3/14 11:47
 */
@Mapper
public interface AcademyMapper {
    /**
     * 创建学院
     *
     * @param academy 要创建的学院
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into academy (nick, school_id) VALUES (#{academy.nick},#{academy.schoolId})")
    Boolean createAcademy(@Param("academy") Academy academy);

    /**
     * 更新学院
     *
     * @param academy 要更新的学院
     * @return 影响的行数
     */
    @Update("update academy set nick=#{academy.nick},school_id=#{academy.schoolId} where id=#{academy.id}")
    Long updateAcademy(@Param("academy") Academy academy);

    /**
     * 查找学院
     *
     * @param id 学院id
     * @return 对应的学院
     */
    @Select("select * from academy where id=#{id}")
    Academy getAcademy(@Param("id") Integer id);

    /**
     * 批量删除学院
     *
     * @param id id的列表
     * @return 影响行数
     */
    Long deleteAcademy(@Param("id") List<Integer> id);

    /**
     * 根据学院名与学校id查找学院列表
     *
     * @param schoolId 学校id
     * @param keyword  学院名
     * @return 学院列表
     */
    @Select("select * from academy where school_id=#{schoolId} and nick like CONCAT('%',#{keyword},'%')")
    List<Academy> getAcademyList(@Param("schoolId") Integer schoolId, @Param("keyword") String keyword);

    /**
     * 根据专业id获取学院
     *
     * @param id 专业id
     * @return 学院
     */
    @Select("select * from academy inner join major on academy.id = major.academy_id")
    Academy getAcademyByMajorId(@Param("id") Integer id);
}
