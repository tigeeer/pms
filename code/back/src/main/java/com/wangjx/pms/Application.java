package com.wangjx.pms;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/10/12
 * Time: 11:40
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.wangjx.common", "com.wangjx.pms"})
@MapperScan(basePackages = {"com.wangjx.pms.mapper"})
public class Application {

    private final static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
