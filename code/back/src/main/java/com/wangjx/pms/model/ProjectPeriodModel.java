package com.wangjx.pms.model;

import com.wangjx.pms.pojo.ProjectPeriod;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/11/17
 * Time: 17:20
 */
public class ProjectPeriodModel extends ProjectPeriod {

    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
