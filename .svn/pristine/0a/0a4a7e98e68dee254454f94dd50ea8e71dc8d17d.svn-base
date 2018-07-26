package com.bpg.lr.config;
/**
 * @Title:AbstractController
 * @Description:	公共组件，获取登陆信息
 * @Author hjp	
 * @Date 2018年6月7日
 */



import org.apache.shiro.SecurityUtils;

import com.bpg.lr.login.model.SysUser;

public abstract class AbstractController {
	
	

	/**
	 * @Title: getUser   
	 * @Description:   获取当前登陆信息
	 * @param @return   
	 * @return UserInfo    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月7日
	 */
	
	protected SysUser getUser() {
		SysUser userInfo = new SysUser();
		userInfo = (SysUser) SecurityUtils.getSubject().getPrincipal();
		if (userInfo == null) {
			userInfo = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
		}
		return userInfo;
	}
	
	protected Long getUserId() {
		 
		return getUser().getUserId();
	}
	
	protected Long getDeptId() {
		return getUser().getDeptId();
		
	}
	
	protected String getUserName() {
		return getUser().getUsername();
		
	}
	
	/**
	 * @Title: getUserSession   
	 * @Description:  获取session里信息 
	 * @param @return   
	 * @return UserInfo    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月7日
	 */
	protected SysUser getUserSession() {
		return (SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
	}
}
