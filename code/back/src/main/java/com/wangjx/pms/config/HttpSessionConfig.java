package com.wangjx.pms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/5/18
 * Time: 14:55
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {
}
