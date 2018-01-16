package com.wangjx.pms.controller;

import com.wangjx.common.FilterParam;
import com.wangjx.pms.constant.UserRole;
import com.wangjx.pms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/10/24
 * Time: 18:26
 */
@Controller
public class TemplateController {

    private UserService userService;

    public TemplateController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/index.h")
    public ModelAndView index() {
        return new ModelAndView("index.html");
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init() {
        return userService.getByRoles(new FilterParam(), new Long[]{UserRole.ROOT.getId()}).size() == 0 ? "init" : "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
