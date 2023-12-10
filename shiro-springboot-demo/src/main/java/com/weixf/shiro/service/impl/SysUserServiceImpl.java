package com.weixf.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weixf.shiro.entity.SysUser;
import com.weixf.shiro.mapper.SysUserMapper;
import com.weixf.shiro.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper sysUserMapper;

    public SysUserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public SysUser getSysUserByName(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("name", username);
        return sysUserMapper.selectOne(wrapper);
    }

    // 根据用户查询角色信息
    @Override
    public List<String> getUserRoleInfo(String principal) {
        return sysUserMapper.getUserRoleInfoMapper(principal);
    }

    // 获取用户角色权限信息
    @Override
    public List<String> getUserPermissionInfo(List<String> roles) {
        return sysUserMapper.getUserPermissionInfoMapper(roles);
    }
}
