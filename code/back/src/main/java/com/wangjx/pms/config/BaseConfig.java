package com.wangjx.pms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/10/25
 * Time: 11:28
 */
@Configuration
@EnableAutoConfiguration
@PropertySource({"classpath:base-config.properties"})
@ConfigurationProperties(prefix = "bc")
public class BaseConfig {

    @Value(value = "${bc.upload-dir}")
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }
}
