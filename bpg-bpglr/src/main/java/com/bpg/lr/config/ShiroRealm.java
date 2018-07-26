package com.bpg.lr.config;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.bpg.lr.login.model.SysMenu;
import com.bpg.lr.login.model.SysRole;
import com.bpg.lr.login.model.SysUser;
import com.bpg.lr.service.login.SysUserService;


/**
 * @Title:ShiroRealm
 * @Description:身份验证核心类	
 * @Author hjp	
 * @Date 2018年6月4日
 */
@Configuration
public class ShiroRealm extends AuthorizingRealm{
	
	
	private static final Logger log = LoggerFactory.getLogger(ShiroRealm.class);

	@Autowired
	private SysUserService userInfoService;

	/**
	 * 认证身份信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		//获取登陆账号
		String username = (String) token.getPrincipal();
		SysUser userInfo = userInfoService.findByUserName(username);
		if (userInfo == null) {
			return null;
		}
		
		if (!"1".equals(userInfo.getStatus())) {
			throw new AuthenticationException();
		}
		
		//获得角色
		List<SysRole> sysRoles = userInfoService.findSysRoleByUserInfo(userInfo);
		
		if (sysRoles != null && sysRoles.size() >0) {
			//获得权限
			userInfo.setRoles(sysRoles);
			List<SysMenu> sysMenus = userInfoService.findSysMenuByRole(sysRoles);
			if (sysMenus != null && sysMenus.size() > 0) {
				userInfo.setSysMenus(sysMenus);
			}
		}
		
		//缓存用户信息
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("user", userInfo);
		
		//密码匹配  密文
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				userInfo, userInfo.getPassword(),ByteSource.Util.bytes(userInfo.getSalt()),
						getName());
	   
		//明文: 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验  
  /*      SimpleAuthenticationInfo authenticationInfo2 = new SimpleAuthenticationInfo(  
        userInfo, //用户名  
        userInfo.getPassword(), //密码  
        getName()  //realm name  
      );
		*/
		
		return authenticationInfo;
	}

	
	/** 
     * 此方法调用  hasRole,hasPermission的时候才会进行回调. 
     * 
     * 权限信息.(授权): 
     * 1、如果用户正常退出，缓存自动清空； 
     * 2、如果用户非正常退出，缓存自动清空； 
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。 
     * （需要手动编程进行实现；放在service进行调用） 
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例， 
     * 调用clearCached方法； 
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。 
     * @param principals 
     * @return 
     */  
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.info("hasPermission-->:权限控制");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		SysUser userInfo  = (SysUser) SecurityUtils.getSubject().getPrincipal();
		//获得角色
		if (userInfo != null) {
			List<SysRole> sysRoles = userInfoService.findSysRoleByUserInfo(userInfo);
			if (sysRoles != null && sysRoles.size() >0) {
				
				userInfo.setRoles(sysRoles);
				for (SysRole sysRole : sysRoles) {
					info.addRole(sysRole.getRoleName());
				}
				
				//获得权限
				List<SysMenu> sysMenus = userInfoService.findSysMenuByRole(sysRoles);
				if (sysMenus != null && sysMenus.size() > 0) {
					userInfo.setSysMenus(sysMenus);
					for (SysMenu sysMenu : sysMenus) {
						info.addStringPermission(sysMenu.getPerms());
					}
				}
			}
			
			return info;
		}
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	

}
