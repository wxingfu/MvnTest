package com.wxf.menu.auth.springboot.repository;

import com.wxf.menu.auth.custom.CommonRepository;
import com.wxf.menu.auth.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends CommonRepository<SysUser, Integer> {
    SysUser findByUsername(String username);
}
