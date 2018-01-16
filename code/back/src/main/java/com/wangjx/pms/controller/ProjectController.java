package com.wangjx.pms.controller;

import com.wangjx.common.FilterParam;
import com.wangjx.common.util.Table;
import com.wangjx.common.util.response.Response;
import com.wangjx.common.util.response.ResponseFactory;
import com.wangjx.pms.model.ProjectModel;
import com.wangjx.pms.pojo.Project;
import com.wangjx.pms.service.ProjectService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/11/1
 * Time: 14:15
 */
@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response insert(@RequestBody Project project) {
        projectService.insert(project);

        return ResponseFactory.successResponse();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Response update(@RequestBody Project project) {
        projectService.update(project);

        return ResponseFactory.successResponse();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Response delete(Long id) {
        projectService.delete(id);

        return ResponseFactory.successResponse();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Response get(Long id) {
        return ResponseFactory.successResponse(projectService.getById(id));
    }

    @RequestMapping(value = "/many", method = RequestMethod.GET)
    public Response getMany(FilterParam filterParam, Long userId) {
        Table<ProjectModel> table = new Table<>();
        table.setList(projectService.getMany(filterParam, userId));
        table.setCount(projectService.count(filterParam, userId));

        return ResponseFactory.successResponse(table);
    }
}
