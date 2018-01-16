package com.wangjx.pms.mapper;

import com.wangjx.common.FilterParam;
import com.wangjx.pms.model.DiaryModel;
import com.wangjx.pms.pojo.Diary;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/11/7
 * Time: 10:34
 */
@Mapper
public interface DiaryMapper {

    @Insert("INSERT INTO diaries(userId, projectId, content, createTime)\n" +
            "VALUE(#{d.userId}, #{d.projectId}, #{d.content}, #{d.createTime})")
    void insert(@Param("d") Diary diary);

    @Select("SELECT * FROM diaries WHERE id = #{id}")
    DiaryModel findById(@Param("id") Long id);

    @Select("SELECT * FROM diaries\n" +
            "WHERE userId = #{userId}\n" +
            "ORDER BY createTime DESC\n" +
            "LIMIT #{fp.offset}, #{fp.size}")
    List<DiaryModel> findByUser(@Param("fp") FilterParam filterParam, @Param("userId") Long userId);

    @Select({"<script>" +
            "SELECT * FROM diaries\n" +
            "WHERE userId = #{userId}\n" +
            "ORDER BY createTime DESC\n" +
            "LIMIT #{fp.offset}, #{fp.size}" +
            "</script>"})
    int count(@Param("fp") FilterParam filterParam, @Param("userId") Long userId);

    @Select("SELECT * FROM diaries WHERE projectPeriodId = #{projectPeriodId}")
    List<DiaryModel> findByProjectPeriodId(@Param("projectPeriodId") Long projectPeriodId);
}
