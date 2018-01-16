package com.wangjx.pms.controller;

import com.wangjx.common.web.util.CookieUtil;
import com.wangjx.pms.security.AuthenticationFactory;
import com.wangjx.pms.service.AuthenticationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/10/27
 * Time: 14:30
 */
@RestController
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/imageCode", method = RequestMethod.GET)
    public void getImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie cookie = CookieUtil.get(request.getCookies(), AuthenticationFactory.COOKIE_NAME);
        String cookieStr = null;

        if (cookie != null) {
            cookieStr = cookie.getValue();
        }

        authenticationService.getImageCode(cookieStr).write(response.getOutputStream());
    }
}
