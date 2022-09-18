package com.weixf.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weixf.security.entity.SysUserRole;
import com.weixf.security.mapper.SysUserRoleMapper;
import com.weixf.security.service.SysUserRoleService;
import org.springframework.stereotype.Service;


@Service("sysUserRoleService")
public class SysUserRoleServiceImpl
        extends ServiceImpl<SysUserRoleMapper, SysUserRole>
        implements SysUserRoleService {

}