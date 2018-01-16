package com.wangjx.pms.config;

import com.wangjx.pms.security.*;
import com.wangjx.pms.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 12/12/2016
 * Time: 12:54 AM
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomAuthenticationFilter customAuthenticationFilter;
    private CustomAuthenticationProvider customAuthenticationProvider;
    private AuthenticationService authenticationService;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    public WebSecurityConfig(CustomAuthenticationFilter customAuthenticationFilter,
                             CustomAuthenticationProvider customAuthenticationProvider,
                             AuthenticationService authenticationService) {
        this.customAuthenticationFilter = customAuthenticationFilter;
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.authenticationService = authenticationService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().cacheControl().disable()
                .and().rememberMe().alwaysRemember(true)
                .and().csrf().disable()
                .authorizeRequests()
                    .antMatchers("/login", "/init/**", "/user", "/assets/**", "/imageCode").permitAll()
                    .antMatchers("/my", "/user/experts", "/user/companies").authenticated()
//                    .antMatchers("/user", "/user/**")
//                    .hasAnyRole(UserRole.ROOT.getAbbr())
                    .anyRequest().permitAll()
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().logoutSuccessHandler(new CustomLogoutSuccessHandler()).permitAll()
                .and().exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint())
                .and().addFilterBefore(customUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//            .and().addFilterBefore(customUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//            .addFilterBefore(customAuthenticationFilter, AnonymousAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/assets/**");
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint("/login");
    }

    @Bean
    public CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() {
        CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter(authenticationService);
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());

        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() {
        List<AuthenticationProvider> authenticationProviderList = new LinkedList<>();
        authenticationProviderList.add(customAuthenticationProvider);

        return new ProviderManager(authenticationProviderList);
    }
}
