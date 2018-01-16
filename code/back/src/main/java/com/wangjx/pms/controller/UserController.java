package com.wangjx.pms.controller;

import com.wangjx.common.FilterParam;
import com.wangjx.common.util.response.Response;
import com.wangjx.common.util.response.ResponseFactory;
import com.wangjx.pms.pojo.User;
import com.wangjx.pms.service.UserRoleService;
import com.wangjx.pms.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/10/12
 * Time: 11:41
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;
    private UserRoleService userRoleService;

    public UserController(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response insert(@RequestBody User user) {
        userService.insert(user);

        return ResponseFactory.successResponse();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Response modify(@RequestBody User user) {
        userService.modifyInfo(user);

        return ResponseFactory.successResponse();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Response delete(Long id) {
        userService.delete(id);

        return ResponseFactory.successResponse();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Response get(Long id) {
        User user = new User();
        user.setId(id);

        return ResponseFactory.successResponse(userService.getById(user));
    }

    @RequestMapping(value = "/pwd", method = RequestMethod.PUT)
    public Response modifyPassword(@RequestBody User user) {
        userService.modifyPassword(user);

        return ResponseFactory.successResponse();
    }

    @RequestMapping(value = "/many", method = RequestMethod.GET)
    public Response getMany(FilterParam filterParam) {
        return ResponseFactory.successResponse(userService.getByRoles(filterParam, userRoleService.getRoleIds()));
    }
}
