package com.weixf.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weixf.security.entity.SysMenu;
import com.weixf.security.entity.SysRole;
import com.weixf.security.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 通过用户ID查询角色集合
     */
    List<SysRole> selectSysRoleByUserId(Long userId);

    /**
     * 通过用户ID查询权限集合
     */
    List<SysMenu> selectSysMenuByUserId(Long userId);

}
