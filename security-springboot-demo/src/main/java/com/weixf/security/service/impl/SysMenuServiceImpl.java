package com.weixf.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weixf.security.entity.SysMenu;
import com.weixf.security.mapper.SysMenuMapper;
import com.weixf.security.service.SysMenuService;
import org.springframework.stereotype.Service;


@Service("sysMenuService")
public class SysMenuServiceImpl
        extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {

}