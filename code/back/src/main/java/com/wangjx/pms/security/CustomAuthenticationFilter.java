package com.wangjx.pms.security;

import com.wangjx.pms.pojo.User;
import com.wangjx.pms.constant.UserRole;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/5/19
 * Time: 14:02
 */
@Configuration
public class CustomAuthenticationFilter extends GenericFilterBean implements
        InitializingBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContextHolder.getContext().setAuthentication(
                    createAuthentication());
        }

        chain.doFilter(request, response);
    }

    private Authentication createAuthentication() {
        User user = new User();
        user.setId(3L);
        user.setPassword("e10adc3949ba59abbe56e057f20f883e");
        user.setRole(UserRole.ROOT.getId());
        List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), grantedAuthorities);
        authenticationToken.setDetails(user);

        return authenticationToken;
    }
}
