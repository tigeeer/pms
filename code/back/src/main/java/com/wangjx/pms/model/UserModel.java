package com.wangjx.pms.model;

import com.wangjx.pms.pojo.User;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/11/8
 * Time: 15:58
 */
public class UserModel extends User {

    private String roleAbbr;
    private String roleName;

    public String getRoleAbbr() {
        return roleAbbr;
    }

    public void setRoleAbbr(String roleAbbr) {
        this.roleAbbr = roleAbbr;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
