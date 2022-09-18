package com.weixf.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weixf.security.entity.SysRole;
import com.weixf.security.mapper.SysRoleMapper;
import com.weixf.security.service.SysRoleService;
import org.springframework.stereotype.Service;


@Service("sysRoleService")
public class SysRoleServiceImpl
        extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {

}