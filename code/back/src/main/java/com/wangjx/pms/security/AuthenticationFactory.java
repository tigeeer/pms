package com.wangjx.pms.security;

import com.wangjx.pms.pojo.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/5/18
 * Time: 15:48
 */
public class AuthenticationFactory {

    public static final String COOKIE_NAME = "SESSION";

    public static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal().equals("anonymousUser")) {
            return new User();
        }

        return (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

    public static Long getUserId() {
        return getUser().getId();
    }

    public static Long getUserRole() {
        return getUser().getRole();
    }
}
