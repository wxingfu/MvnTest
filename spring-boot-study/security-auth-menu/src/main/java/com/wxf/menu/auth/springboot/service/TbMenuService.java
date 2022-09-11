package com.wxf.menu.auth.springboot.service;

import com.wxf.menu.auth.custom.CommonService;
import com.wxf.menu.auth.entity.TbMenu;
import com.wxf.menu.auth.security.UserUtils;
import com.wxf.menu.auth.springboot.repository.TbMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbMenuService extends CommonService<TbMenu, Integer> {

    @Autowired
    private TbMenuRepository menuRepository;

    @Autowired
    private UserUtils userUtils;

    /**
     * 获取用户所拥有的权限对应的菜单项
     */
    public List<TbMenu> findAuditMenu() {
        List<TbMenu> menus;
        //判断是否是后门用户
        if (userUtils.hasRole("ROLE_DIY")) {
            //查询所有菜单，子菜单可以通过父级菜单的映射得到
            menus = menuRepository.findByParentIsNullOrderByIdx();
        } else {
            //获取此用户对应的菜单权限
            menus = auditMenu(menuRepository.findByParentIsNullOrderByIdx());
        }
        return menus;
    }

    //根据用户的菜单权限对菜单进行过滤
    private List<TbMenu> auditMenu(List<TbMenu> menus) {
        List<TbMenu> list = new ArrayList<>();
        for (TbMenu menu : menus) {
            String name = menu.getName();
            //判断此用户是否有此菜单权限
            if (userUtils.hasRole(name)) {
                list.add(menu);
                //递归判断子菜单
                if (menu.getChildren() != null && !menu.getChildren().isEmpty()) {
                    menu.setChildren(auditMenu(menu.getChildren()));
                }
            }
        }
        return list;
    }

    public TbMenu findByName(String name) {
        return menuRepository.findByName(name);
    }
}
