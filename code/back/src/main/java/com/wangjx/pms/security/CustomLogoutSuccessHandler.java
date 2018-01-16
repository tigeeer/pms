package com.wangjx.pms.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangjx.common.util.response.ResponseFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/6/26
 * Time: 18:11
 */
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(
                ResponseFactory.successResponse()));
        response.getWriter().flush();
    }
}
