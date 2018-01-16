package com.wangjx.pms.service;

import com.wangjx.common.FilterParam;
import com.wangjx.common.exception.CommonException;
import com.wangjx.pms.mapper.ProjectMapper;
import com.wangjx.pms.model.ProjectModel;
import com.wangjx.pms.pojo.Project;
import com.wangjx.pms.security.AuthenticationFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/11/6
 * Time: 15:13
 */
@Service
public class ProjectService {

    private ProjectMapper projectMapper;
    private ProjectPeriodService projectPeriodService;

    public ProjectService(ProjectMapper projectMapper, ProjectPeriodService projectPeriodService) {
        this.projectMapper = projectMapper;
        this.projectPeriodService = projectPeriodService;
    }

    public void insert(Project project) {
        project.setUserId(AuthenticationFactory.getUserId());
        project.setCreateTime(Timestamp.from(Instant.now()));

        projectMapper.insert(project);
    }

    public void update(Project project) {
        projectMapper.update(project);
    }

    public void delete(Long id) {
        if (projectPeriodService.getByProjectId(id).size() > 0) {
            throw new CommonException("Delete fail.");
        }

        projectMapper.delete(id);
    }

    public Project getById(Long id) {
        return projectMapper.findById(id);
    }

    public List<ProjectModel> getMany(FilterParam filterParam, Long userId) {
        return projectMapper.findMany(filterParam, userId);
    }

    public int count(FilterParam filterParam, Long userId) {
        return projectMapper.count(filterParam, userId);
    }
}
