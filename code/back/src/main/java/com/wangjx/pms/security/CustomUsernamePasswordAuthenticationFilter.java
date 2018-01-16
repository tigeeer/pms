package com.wangjx.pms.security;

import com.wangjx.common.web.exception.LoginException;
import com.wangjx.common.web.util.CookieUtil;
import com.wangjx.pms.service.AuthenticationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2016/12/29
 * Time: 16:36
 */
@Component
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Log logger = LogFactory.getLog(CustomUsernamePasswordAuthenticationFilter.class);

    private AuthenticationService authenticationService;

    public CustomUsernamePasswordAuthenticationFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return null;
    }

    @Override
    protected String obtainUsername(HttpServletRequest request){
        return null;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String username, password, imageCode;

        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            try {
                String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                JSONObject parmas = new JSONObject(body);
                username = parmas.getString("username");
                password = parmas.getString("password");
                imageCode = parmas.getString("imageCode");
            } catch (IOException | JSONException e) {
                throw new LoginException("用户名或密码错误");
            }
        } else {
            username = request.getParameter("username");
            password = request.getParameter("password");
            imageCode = request.getParameter("imageCode");
        }

        if (imageCode == null) {
            throw new LoginException("验证码错误");
        }

        Cookie cookie = CookieUtil.get(request.getCookies(), AuthenticationFactory.COOKIE_NAME);

        if (cookie == null) {
            throw new LoginException("验证码错误");
        }

        if (!authenticationService.verifyImageCode(imageCode, cookie.getValue())) {
            throw new LoginException("验证码错误");
        }

        if (username == null || password == null) {
            throw new LoginException("用户名或密码错误");
        }

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
