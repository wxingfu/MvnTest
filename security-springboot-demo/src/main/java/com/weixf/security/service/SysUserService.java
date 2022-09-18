package com.weixf.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weixf.security.entity.SysMenu;
import com.weixf.security.entity.SysRole;
import com.weixf.security.entity.SysUser;

import java.util.List;

/**
 * 系统用户业务接口
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名查询实体
     */
    SysUser selectUserByName(String username);

    /**
     * 根据用户ID查询角色集合
     */
    List<SysRole> selectSysRoleByUserId(Long userId);

    /**
     * 根据用户ID查询权限集合
     */
    List<SysMenu> selectSysMenuByUserId(Long userId);

}