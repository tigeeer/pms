package com.wangjx.pms.service;

import com.wangjx.common.exception.CommonException;
import com.wangjx.pms.constant.ProjectPeriodStatus;
import com.wangjx.pms.mapper.ProjectPeriodMapper;
import com.wangjx.pms.model.ProjectPeriodModel;
import com.wangjx.pms.pojo.ProjectPeriod;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/11/17
 * Time: 16:35
 */
@Service
public class ProjectPeriodService {

    private ProjectPeriodMapper projectPeriodMapper;
    private DiaryService diaryService;

    public ProjectPeriodService(ProjectPeriodMapper projectPeriodMapper, DiaryService diaryService) {
        this.projectPeriodMapper = projectPeriodMapper;
        this.diaryService = diaryService;
    }

    public void insert(ProjectPeriod projectPeriod) {
        projectPeriod.setStatus(ProjectPeriodStatus.NOT_STARTED.getId());
        projectPeriod.setCreateTime(Timestamp.from(Instant.now()));
        projectPeriodMapper.insert(projectPeriod);
    }

    public void modify(ProjectPeriod projectPeriod) {
        projectPeriodMapper.modify(projectPeriod);
    }

    public void delete(Long id) {
        if (diaryService.getByProjectPeriodId(id).size() > 0) {
            throw new CommonException("Delete Fail.");
        }

        projectPeriodMapper.delete(id);
    }

    public void start(Long id) {
        projectPeriodMapper.start(id, ProjectPeriodStatus.UNDERWAY.getId(), Timestamp.from(Instant.now()));
    }

    public void end(Long id) {
        projectPeriodMapper.end(id, ProjectPeriodStatus.OVER.getId(), Timestamp.from(Instant.now()));
    }

    public List<ProjectPeriodModel> getByProjectId(Long projectId) {
        return projectPeriodMapper.findByProjectId(projectId);
    }
}
