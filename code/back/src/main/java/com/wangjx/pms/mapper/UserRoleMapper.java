package com.wangjx.pms.mapper;

import com.wangjx.pms.constant.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/11/8
 * Time: 15:29
 */
@Mapper
public interface UserRoleMapper {

    @Insert({"<script>" +
            "INSERT INTO user_roles VALUES" +
            "<foreach item=\"item\" index=\"index\" collection=\"userRoles\"\n" +
            "      open=\"\" separator=\",\" close=\"\">\n" +
            "        (#{item.id}, #{item.abbr}, #{item.name})\n" +
            "  </foreach>" +
            "</script>"})
    void insert(@Param("userRoles") UserRole[] userRoles);
}
