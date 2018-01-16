package com.wangjx.pms.controller;

import com.wangjx.common.util.response.Response;
import com.wangjx.common.util.response.ResponseFactory;
import com.wangjx.pms.pojo.ProjectPeriod;
import com.wangjx.pms.service.ProjectPeriodService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/11/17
 * Time: 16:24
 */
@RestController
@RequestMapping(value = "/project/period")
public class ProjectPeriodController {

    private ProjectPeriodService projectPeriodService;

    public ProjectPeriodController(ProjectPeriodService projectPeriodService) {
        this.projectPeriodService = projectPeriodService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response insert(@RequestBody ProjectPeriod projectPeriod) {
        projectPeriodService.insert(projectPeriod);

        return ResponseFactory.successResponse();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Response modify(@RequestBody ProjectPeriod projectPeriod) {
        projectPeriodService.modify(projectPeriod);

        return ResponseFactory.successResponse();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Response delete(Long id) {
        projectPeriodService.delete(id);

        return ResponseFactory.successResponse();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Response get(Long id) {
        return ResponseFactory.successResponse();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Response getAll(Long projectId) {
        return ResponseFactory.successResponse(projectPeriodService.getByProjectId(projectId));
    }

    @RequestMapping(value = "/start", method = RequestMethod.PUT)
    public Response start(Long id) {
        projectPeriodService.start(id);

        return ResponseFactory.successResponse();
    }

    @RequestMapping(value = "/end", method = RequestMethod.PUT)
    public Response end(Long id) {
        projectPeriodService.end(id);

        return ResponseFactory.successResponse();
    }
}
