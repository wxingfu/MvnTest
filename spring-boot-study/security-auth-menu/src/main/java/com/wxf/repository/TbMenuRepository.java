package com.wxf.repository;

import com.wxf.custom.CommonRepository;
import com.wxf.entity.TbMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbMenuRepository extends CommonRepository<TbMenu, Integer> {
    List<TbMenu> findByParentIsNullOrderByIdx();

    TbMenu findByName(String name);
}
