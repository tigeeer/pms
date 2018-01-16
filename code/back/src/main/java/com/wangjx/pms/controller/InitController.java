package com.wangjx.pms.controller;

import com.wangjx.common.exception.CommonException;
import com.wangjx.common.util.response.Response;
import com.wangjx.common.util.response.ResponseFactory;
import com.wangjx.pms.config.SystemProperties;
import com.wangjx.pms.pojo.User;
import com.wangjx.pms.service.InitService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/10/23
 * Time: 15:56
 */
@RestController
@RequestMapping(value = "/init")
public class InitController {

    private SqlSessionFactory sqlSessionFactory;
    private InitService initService;

    public InitController(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Response getInfo() {
        Map<String, String> map = new HashMap<>();
        map.put(SystemProperties.JAVA_VERSION.getPropertyName(), System.getProperty(SystemProperties.JAVA_VERSION.getPropertyName()));
        map.put(SystemProperties.OS_NAME.getPropertyName(), System.getProperty(SystemProperties.OS_NAME.getPropertyName()));
        map.put(SystemProperties.OS_VERSION.getPropertyName(), System.getProperty(SystemProperties.OS_VERSION.getPropertyName()));

        try {
            DatabaseMetaData meta = sqlSessionFactory.openSession().getConnection().getMetaData();
            map.put(SystemProperties.DATABASE_NAME.getPropertyName(), meta.getDatabaseProductName());
            map.put(SystemProperties.DATABASE_VERSION.getPropertyName(), meta.getDatabaseProductVersion());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseFactory.successResponse(map);
    }

    @RequestMapping(value = "/root", method = RequestMethod.POST)
    public Response init(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CommonException(bindingResult.getFieldError().getDefaultMessage());
        }

        initService.init(user);

        return ResponseFactory.successResponse();
    }

}
