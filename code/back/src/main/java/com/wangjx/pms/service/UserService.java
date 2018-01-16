package com.wangjx.pms.service;

import com.wangjx.common.FilterParam;
import com.wangjx.common.exception.CommonException;
import com.wangjx.pms.annotation.own.OwnParam;
import com.wangjx.pms.annotation.own.OwnUser;
import com.wangjx.pms.mapper.UserMapper;
import com.wangjx.pms.model.UserModel;
import com.wangjx.pms.pojo.User;
import com.wangjx.pms.security.AuthenticationFactory;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/10/23
 * Time: 16:37
 */
@Service
public class UserService {

    private UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void insert(User user) {
        if (getByUsername(user.getUsername()) != null) {
            throw new CommonException("用户名已存在");
        }

        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        user.setCreateTime(Timestamp.from(Instant.now()));
        user.setCreateUser(AuthenticationFactory.getUserId());

        userMapper.insert(user);
    }

    public void modifyPassword(User user) {
        userMapper.modifyPassword(user);
    }

    public void modifyInfo(User user) {
        userMapper.modifyInfo(user);
    }

    public void delete(Long id) {
        userMapper.delete(id);
    }

    @OwnUser
    public UserModel getById(@OwnParam("id") User id) {
//        return userMapper.findById(id);
        return null;
    }

    public List<UserModel> getByRoles(FilterParam filterParam, Long[] roles) {
        return userMapper.findByRoles(filterParam, roles);
    }

    public UserModel getByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
