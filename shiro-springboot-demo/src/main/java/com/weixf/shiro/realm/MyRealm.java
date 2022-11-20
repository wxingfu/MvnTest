package com.weixf.shiro.realm;

import com.weixf.shiro.entity.SysUser;
import com.weixf.shiro.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Resource
    private SysUserService sysUserService;

    /**
     * 自定义授权方法
     *
     * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("自定义授权方法");
        //1获取用户身份信息
        String principal = principals.getPrimaryPrincipal().toString();
        //2调用业务层获取用户的角色信息（数据库）
        List<String> roles = sysUserService.getUserRoleInfo(principal);
        System.out.println("当前用户角色信息 = " + roles);
        //2.5调用业务层获取用户的权限信息（数据库）
        List<String> permissions = sysUserService.getUserPermissionInfo(roles);
        System.out.println("当前用户权限信息 = " + permissions);
        //3创建对象，封装当前登录用户的角色、权限信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        //4返回信息
        return info;
    }

    /**
     * 自定义认证方法
     *
     * @param token the authentication token containing the user's principal and credentials.
     * @return AuthenticationInfo
     * @throws AuthenticationException AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1获取用户身份信息
        String username = token.getPrincipal().toString();
        //2调用业务层获取用户信息（数据库）
        SysUser sysUser = sysUserService.getSysUserByName(username);
        //3非空判断，将数据封装返回
        if (sysUser != null) {
            AuthenticationInfo info = new SimpleAuthenticationInfo(token.getPrincipal(), sysUser.getPwd(),
                    ByteSource.Util.bytes("salt"), token.getPrincipal().toString());
            return info;
        }
        return null;
    }
}
