package com.wangjx.pms.service;

import com.wangjx.pms.pojo.User;
import com.wangjx.pms.constant.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/11/8
 * Time: 17:34
 */
@Service
public class InitService {

    private UserService userService;
    private UserRoleService userRoleService;

    public InitService(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Transactional
    public void init(User user) {
        userRoleService.insert(UserRole.values());

        user.setRole(UserRole.ROOT.getId());
        user.setNickname(UserRole.ROOT.getName());
        userService.insert(user);
    }
}
