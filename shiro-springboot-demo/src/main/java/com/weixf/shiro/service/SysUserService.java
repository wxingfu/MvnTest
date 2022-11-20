package com.weixf.shiro.service;

import com.weixf.shiro.entity.SysUser;

import java.util.List;

public interface SysUserService {


    SysUser getSysUserByName(String username);

    //根据用户查询角色信息
    List<String> getUserRoleInfo(String principal);

    //获取用户角色权限信息
    List<String> getUserPermissionInfo(List<String> roles);

}
