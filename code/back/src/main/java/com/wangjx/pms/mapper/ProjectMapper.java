package com.wangjx.pms.mapper;

import com.wangjx.common.FilterParam;
import com.wangjx.pms.model.ProjectModel;
import com.wangjx.pms.pojo.Project;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/11/6
 * Time: 15:14
 */
@Mapper
public interface ProjectMapper {

    @Insert("INSERT INTO projects(userId, name, description, createTime)\n" +
            "VALUE(#{p.userId}, #{p.name}, #{p.description}, #{p.createTime})")
    void insert(@Param("p") Project project);

    @Delete("DELETE FROM projects WHERE id = #{id}")
    void delete(@Param("id") Long id);

    @Update("UPDATE projects SET name = #{p.name}, description = #{p.description} WHERE id = #{p.id}")
    void update(@Param("p") Project project);

    @Select("SELECT * FROM projects p WHERE p.id = #{id}")
    ProjectModel findById(@Param("id") Long id);

    @Select({"<script>" +
            "SELECT * FROM projects p\n" +
            "<where>" +
            "   <if test=\"userId != null\">" +
            "       p.userId = #{userId}" +
            "   </if>" +
            "</where>" +
            "ORDER BY p.createTime DESC\n" +
            "LIMIT #{fp.offset}, #{fp.size}" +
            "</script>"})
    List<ProjectModel> findMany(@Param("fp") FilterParam filterParam, @Param("userId") Long userId);

    @Select({"<script>" +
            "SELECT * FROM projects p\n" +
            "<where>" +
            "   <if test=\"userId != null\">" +
            "       p.userId = #{userId}" +
            "   </if>" +
            "</where>" +
            "</script>"})
    int count(@Param("fp") FilterParam filterParam, @Param("userId") Long userId);
}
