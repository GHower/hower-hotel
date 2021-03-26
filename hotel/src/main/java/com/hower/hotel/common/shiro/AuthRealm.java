package com.hower.hotel.common.shiro;

import com.hower.hotel.model.entity.Role;
import com.hower.hotel.model.entity.StaffInfo;
import com.hower.hotel.model.entity.SysPermission;
import com.hower.hotel.model.entity.SysToken;
import com.hower.hotel.service.impl.RoleServiceImpl;
import com.hower.hotel.service.impl.StaffInfoServiceImpl;
import com.hower.hotel.service.impl.SysPermissionServiceImpl;
import com.hower.hotel.service.impl.SysTokenServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AuthRealm extends AuthorizingRealm {
//    @Autowired
//    private ShiroService shiroService;
    @Autowired
    private StaffInfoServiceImpl staffInfoService;
    @Autowired
    private SysTokenServiceImpl tokenService;

    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private SysPermissionServiceImpl sysPermissionService;
    /**
     * 授权 获取用户的角色和权限
     *@return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        StaffInfo staffInfo = (StaffInfo) principals.getPrimaryPrincipal();

        //2.添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<Role> byUId = roleService.getByUId(staffInfo.getId());
        for (Role role : byUId) {
            //2.1添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            System.out.println(role);
            List<SysPermission> byRoleId = sysPermissionService.getByRoleId(role.getId());
            for (SysPermission permission : byRoleId) {
                //2.1.1添加权限
                System.out.println(permission);
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }
    /**
     * 认证 判断token的有效性
     *@return org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        //1. 根据accessToken，查询用户信息
        SysToken tokenEntity = tokenService.findByToken(accessToken);
        //2. token失效
        if (tokenEntity == null || tokenEntity.getExpiresTime().isBefore(LocalDateTime.now())) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        StaffInfo user = staffInfoService.getById(tokenEntity.getSId());
        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        //5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        return new SimpleAuthenticationInfo(user, accessToken, this.getName());
    }
}
