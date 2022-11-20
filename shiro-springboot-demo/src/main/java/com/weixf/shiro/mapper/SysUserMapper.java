package com.weixf.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weixf.shiro.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("select r.name from sys_role r, sys_user_role ur, sys_user u where r.id = ur.rid and ur.uid = u.id and u.name = #{principal}")
    List<String> getUserRoleInfoMapper(@Param("principal") String principal);


    @Select({"<script>select info from sys_permission where id in ",
            "(select pid from sys_role_permission where rid in (",
            "select id from sys_role where name in ",
            "<foreach collection='roles' item='name' open='(' separator=',' close=')'>",
            "#{name}",
            "</foreach>))</script>"})
    List<String> getUserPermissionInfoMapper(@Param("roles") List<String> roles);
}
