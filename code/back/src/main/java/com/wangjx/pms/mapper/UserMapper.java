package com.wangjx.pms.mapper;

import com.wangjx.common.FilterParam;
import com.wangjx.pms.model.UserModel;
import com.wangjx.pms.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/10/12
 * Time: 16:01
 */
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO users(username, password, nickname, mobile, email, role, createTime, createUser)\n" +
            "VALUE(#{u.username}, #{u.password}, #{u.nickname}, #{u.mobile}, #{email}, #{u.role}, #{u.createTime},\n" +
            "#{u.createUser})")
    void insert(@Param("u") User user);

    @Update("UPDATE users SET password = #{u.password} WHERE id = #{u.id}")
    void modifyPassword(@Param("u") User user);

    @Update("UPDATE users SET nickname = #{u.nickname}, avatar = #{u.avatar}, mobile = #{u.mobile}, email = #{u.email}\n" +
            "WHERE id = #{u.id}")
    void modifyInfo(@Param("u") User user);

    @Update("UPDATE users SET deleted = TRUE WHERE id = #{id}")
    void delete(@Param("id") Long id);

    @Select("SELECT * FROM users WHERE id = #{id}")
    UserModel findById(@Param("id") Long id);

    @Select("SELECT u.*, ur.abbr, ur.name FROM users u\n" +
            "LEFT JOIN user_roles ur ON u.role = ur.id\n" +
            "WHERE username = #{username}")
    UserModel findByUsername(@Param("username") String username);

    @Select({"<script>" +
            "SELECT *\n" +
            "FROM users\n" +
            "WHERE deleted = FALSE\n" +
            "<choose>" +
            "   <when test=\"fp.keyword == null\">" +
            "       role in\n" +
            "       <foreach item=\"item\" index=\"index\" collection=\"roles\"" +
            "           open=\"(\" separator=\",\" close=\")\">" +
            "           #{item}\n" +
            "       </foreach>" +
            "   </when>" +
            "   <otherwise>" +
            "       u.username LIKE #{fp.keyword}" +
            "   </otherwise>" +
            "</choose>" +
            "LIMIT #{fp.offset}, #{fp.size}" +
            "</script>"})
    List<UserModel> findByRoles(@Param("fp") FilterParam filterParam, @Param("roles") Long[] roles);
}
