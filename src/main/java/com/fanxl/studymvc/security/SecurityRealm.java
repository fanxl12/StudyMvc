package com.fanxl.studymvc.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户身份验证,授权 Realm 组件
 * 
 * @author StarZou
 * @since 2014年6月11日 上午11:35:28
 **/
@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {

//    @Resource
//    private UserService userService;

//    @Resource
//    private RoleService roleService;
//
//    @Resource
//    private PermissionService permissionService;

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = String.valueOf(principals.getPrimaryPrincipal());

//        final User user = userService.selectByUsername(username);
//        final List<Role> roleInfos = roleService.selectRolesByUserId(user.getId());
//        for (Role role : roleInfos) {
//            // 添加角色
//            System.err.println(role);
//            authorizationInfo.addRole(role.getRoleSign());
//
//            final List<Permission> permissions = permissionService.selectPermissionsByRoleId(role.getId());
//            for (Permission permission : permissions) {
//                // 添加权限
//                System.err.println(permission);
//                authorizationInfo.addStringPermission(permission.getPermissionSign());
//            }
//        }
        return authorizationInfo;
    }

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        if (!username.equals("admin")){
            throw new AuthenticationException("用户名错误");
        }
        if (!password.equals("123")){
            throw new AuthenticationException("密码错误");
        }
        // 通过数据库进行验证
//        final User authentication = userService.authentication(new User(username, password));
//        if (authentication == null) {
//            throw new AuthenticationException("用户名或密码错误.");
//        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        return authenticationInfo;
    }

}
