package com.wangjx.pms.constant;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/11/17
 * Time: 16:08
 */
public enum ProjectPeriodStatus {
    NOT_STARTED(0, "未开始"),
    UNDERWAY(1, "进行中"),
    OVER(2, "已结束");

    private Integer id;
    private String text;

    ProjectPeriodStatus(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
