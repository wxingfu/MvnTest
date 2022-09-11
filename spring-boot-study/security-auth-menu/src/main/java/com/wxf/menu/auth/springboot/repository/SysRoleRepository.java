package com.wxf.menu.auth.springboot.repository;


import com.wxf.menu.auth.custom.CommonRepository;
import com.wxf.menu.auth.entity.SysRole;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SysRoleRepository extends CommonRepository<SysRole, Integer> {
    List<SysRole> findByParent(SysRole role);

    SysRole findByName(String name);

    List<SysRole> findByParentIsNull();

    @Modifying
    @Transactional
    @Query(value = "delete from sys_role where parent_id = ?1", nativeQuery = true)
    void deleteByParentId(Integer id);
}
