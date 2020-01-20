/**
 * 
 */
package com.company.project.configurer.shiro;


import com.company.project.configurer.shiro.cache.MySimpleByteSource;
import com.company.project.module.sys.model.SysUser;
import com.company.project.module.sys.service.SysMenuService;
import com.company.project.module.sys.service.SysRoleService;
import com.company.project.module.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**  
* Copyright (C),2017, Guangzhou sys info. Co., Ltd.
* @ClassName: ShiroRealm
* @Description: shiro 认证 + 授权   重写 
* @author lao  
* @date 2017年12月28日上午10:17:54  
* @version 1.00 
*/
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;


    /* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	/**
	 * 授权Realm
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String account = (String) principals.getPrimaryPrincipal();
        SysUser pojo = new SysUser();
        pojo.setUserName(account);
        String userId = sysUserService.findByName(pojo.getUserName()).getId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		/**根据用户ID查询角色（role），放入到Authorization里.*/
		info.setRoles(sysRoleService.findRoleByUserId(userId));
		/**根据用户ID查询权限（permission），放入到Authorization里.*/
		info.setStringPermissions(sysMenuService.findPermissionByUserId(userId));
        return info; 
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	/**
	 * 登录认证Realm
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        // 通过用户名到数据库查询用户信息
        SysUser user = this.sysUserService.findByName(username);

        if (user == null) {
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        if (SysUser.STATUS_LOCK.equals(user.getStatus())) {
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }
		//**密码加盐**交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
		//return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), ByteSource.Util.bytes("3.14159"), getName());

		return new SimpleAuthenticationInfo(user, user.getPassword(),new MySimpleByteSource("3.14159"), getName());
    }

	
    /**
     * 清空当前用户权限信息
     */
	public  void clearCachedAuthorizationInfo() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
	/**
	 * 指定principalCollection 清除
	 */
	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
	/**
	 * 重写方法,清除当前用户的的 授权缓存
	 * @param principals
	 */
	//@Override
	//public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
	//	super.clearCachedAuthorizationInfo(principals);
	//}

	/**
	 * 重写方法，清除当前用户的 认证缓存
	 * @param principals
	 */
	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	/**
	 * 自定义方法：清除所有 授权缓存
	 */
	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	/**
	 * 自定义方法：清除所有 认证缓存
	 */
	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	/**
	 * 自定义方法：清除所有的  认证缓存  和 授权缓存
	 */
	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}
	
}
