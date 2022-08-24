package com.wxf.repository;

import com.wxf.custom.CommonRepository;
import com.wxf.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends CommonRepository<SysUser, Integer> {
    SysUser findByUsername(String username);
}
