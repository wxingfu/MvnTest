package com.weixf.menu.auth.springboot.repository;

import com.weixf.menu.auth.custom.CommonRepository;
import com.weixf.menu.auth.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends CommonRepository<SysUser, Integer> {
    SysUser findByUsername(String username);
}
