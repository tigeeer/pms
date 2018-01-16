package com.wangjx.pms.security;

import com.wangjx.common.web.exception.LoginException;
import com.wangjx.pms.model.UserModel;
import com.wangjx.pms.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2016/12/29
 * Time: 15:28
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserService userService;

    public CustomAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = DigestUtils.md5Hex((String) authentication.getCredentials());
        UserModel user = userService.getByUsername(username);

        if (user == null) {
            throw new BadCredentialsException("用户不存在");
        }

        if (!user.getPassword().equals(password)) {
            throw new LoginException("用户名或密码错误");
        }

        List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoleAbbr()));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), grantedAuthorities);
        authenticationToken.setDetails(user);

        return authenticationToken;
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }
}