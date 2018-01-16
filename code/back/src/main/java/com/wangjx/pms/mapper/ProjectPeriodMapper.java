package com.wangjx.pms.mapper;

import com.wangjx.pms.model.ProjectPeriodModel;
import com.wangjx.pms.pojo.ProjectPeriod;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/11/17
 * Time: 16:31
 */
@Mapper
public interface ProjectPeriodMapper {

    @Insert("INSERT INTO project_period(projectId, name, description, status, createTime)\n" +
            "VALUE(#{pp.projectId}, #{pp.name}, #{pp.description}, #{pp.status}, #{pp.createTime})")
    void insert(@Param("pp") ProjectPeriod projectPeriod);

    @Delete("DELETE FROM project_period WHERE id = #{id}")
    void delete(@Param("id") Long id);

    @Update("UPDATE project_period SET name = #{pp.name}, description = #{pp.description} WHERE id = #{pp.id}")
    void modify(@Param("pp") ProjectPeriod projectPeriod);

    @Update("UPDATE project_period SET status = #{status}, startTime = #{startTime} WHERE id = #{id}")
    void start(@Param("id") Long id, @Param("status") Integer status, @Param("startTime") Timestamp startTime);

    @Update("UPDATE project_period SET status = #{status}, endTime = #{endTime} WHERE id = #{id}")
    void end(@Param("id") Long id, @Param("status") Integer status, @Param("endTime") Timestamp endTime);

    @Select("SELECT pp.*, p.name AS projectName FROM project_period pp\n" +
            "LEFT JOIN projects p ON pp.projectId = p.id\n" +
            "WHERE pp.projectId = #{projectId}\n" +
            "ORDER BY createTime DESC")
    List<ProjectPeriodModel> findByProjectId(@Param("projectId") Long projectId);
}
