package com.wxf.test.shiro.domain;

import lombok.Getter;
import lombok.Setter;
import org.crazycake.shiro.AuthCachePrincipal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体类
 * AuthCachePrincipal:
 * redis和shiro插件包提供的接口
 */
@Entity
@Table(name = "pe_user")
@Getter
@Setter
public class User implements Serializable, AuthCachePrincipal {
    private static final long serialVersionUID = 4297464181093070302L;
    /**
     * ID
     */
    @Id
    private String id;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pe_user_role", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles = new HashSet<Role>();// 用户与角色   多对多

    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
