package com.weixf.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weixf.security.entity.SysRoleMenu;
import com.weixf.security.mapper.SysRoleMenuMapper;
import com.weixf.security.service.SysRoleMenuService;
import org.springframework.stereotype.Service;


@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl
        extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu>
        implements SysRoleMenuService {

}