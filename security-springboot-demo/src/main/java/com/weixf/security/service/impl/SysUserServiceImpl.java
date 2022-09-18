package com.weixf.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weixf.security.entity.SysMenu;
import com.weixf.security.entity.SysRole;
import com.weixf.security.entity.SysUser;
import com.weixf.security.mapper.SysUserMapper;
import com.weixf.security.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统用户业务实现
 */
@Service("sysUserService")
public class SysUserServiceImpl
        extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

    /**
     * 根据用户名查询实体
     */
    @Override
    public SysUser selectUserByName(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername, username);
        return this.baseMapper.selectOne(queryWrapper);
    }

    /**
     * 通过用户ID查询角色集合
     */
    @Override
    public List<SysRole> selectSysRoleByUserId(Long userId) {
        return this.baseMapper.selectSysRoleByUserId(userId);
    }

    /**
     * 根据用户ID查询权限集合
     */
    @Override
    public List<SysMenu> selectSysMenuByUserId(Long userId) {
        return this.baseMapper.selectSysMenuByUserId(userId);
    }
}