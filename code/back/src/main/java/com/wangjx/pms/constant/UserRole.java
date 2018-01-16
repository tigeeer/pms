package com.wangjx.pms.constant;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/11/8
 * Time: 17:11
 */
public enum UserRole {

    ROOT(1L, "ROOT", "超级管理员"),
    ADMIN(2L, "ADMIN", "管理员"),
    NORMAL(3L, "NORMAL", "普通用户");

    private Long id;
    private String abbr;
    private String name;

    UserRole(Long id, String abbr, String name) {
        this.id = id;
        this.abbr = abbr;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getAbbr() {
        return abbr;
    }

    public String getName() {
        return name;
    }
}
