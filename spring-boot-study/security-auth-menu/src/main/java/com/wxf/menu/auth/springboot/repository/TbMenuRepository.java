package com.wxf.menu.auth.springboot.repository;

import com.wxf.menu.auth.custom.CommonRepository;
import com.wxf.menu.auth.entity.TbMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbMenuRepository extends CommonRepository<TbMenu, Integer> {
    List<TbMenu> findByParentIsNullOrderByIdx();

    TbMenu findByName(String name);
}
