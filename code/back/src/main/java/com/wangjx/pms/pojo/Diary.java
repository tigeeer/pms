package com.wangjx.pms.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/11/7
 * Time: 10:30
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Diary {

    private Long id;
    private Long userId;
    private Long projectPeriodId;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProjectPeriodId() {
        return projectPeriodId;
    }

    public void setProjectPeriodId(Long projectPeriodId) {
        this.projectPeriodId = projectPeriodId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
