package com.wxf.menu.auth.springboot.service;

import com.wxf.menu.auth.custom.CommonService;
import com.wxf.menu.auth.entity.SysRole;
import com.wxf.menu.auth.springboot.repository.SysRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleService extends CommonService<SysRole, Integer> {
    @Autowired
    private SysRoleRepository roleRepository;

    public List<SysRole> findByParent(SysRole role) {
        return roleRepository.findByParent(role);
    }

    public SysRole findByName(String name) {
        return roleRepository.findByName(name);
    }

    public List<SysRole> findByParentIsNull() {
        return roleRepository.findByParentIsNull();
    }

    public void deleteByParentId(Integer id) {
        roleRepository.deleteByParentId(id);
    }
}
