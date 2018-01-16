package com.wangjx.pms.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangjx.common.util.response.ResponseFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2016/12/29
 * Time: 18:23
 */
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication auth
    ) throws IOException, ServletException {
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(
                    ResponseFactory.successResponse(AuthenticationFactory.getUser())));
            response.getWriter().flush();
        } else {
            super.onAuthenticationSuccess(request, response, auth);
        }
    }

}
