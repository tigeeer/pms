package com.wangjx.pms.service;

import com.wangjx.pms.mapper.UserRoleMapper;
import com.wangjx.pms.constant.UserRole;
import com.wangjx.pms.security.AuthenticationFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/11/8
 * Time: 17:26
 */
@Service
public class UserRoleService {

    private UserRoleMapper userRoleMapper;

    public UserRoleService(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    public void insert(UserRole[] userRoles) {
        userRoleMapper.insert(userRoles);
    }

    public List<Map> getRoles() {
        return Arrays.stream(UserRole.values())
                .filter(userRole -> userRole.getId() > AuthenticationFactory.getUserRole())
                .map(userRole -> {
                    Map<String, Object> roleMap = new HashMap<>();
                    roleMap.put("id", userRole.getId());
                    roleMap.put("name", userRole.getName());

                    return roleMap;
                }).collect(Collectors.toList());
    }

    public Long[] getRoleIds() {
        return getRoles().stream().map(item -> (Long) item.get("id")).toArray(Long[]::new);
    }
}
