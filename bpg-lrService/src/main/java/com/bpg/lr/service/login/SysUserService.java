package com.bpg.lr.service.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.bpg.lr.login.model.SysMenu;
import com.bpg.lr.login.model.SysRole;
import com.bpg.lr.login.model.SysUser;
import com.bpg.lr.service.baseService.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Title:loginService
 * @Description:人员管理接口	
 * @Author hjp	
 * @Date 2018年7月4日
 */
public interface SysUserService extends BaseService<SysUser>{
	
	/**
	 * @Title: findByUserName   
	 * @Description:   根据登陆用户名精确查找用户
	 * @param @param userName
	 * @param @return   
	 * @return SysUser    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年7月6日
	 */
	public SysUser findByUserName(String userName); 
	
	/**
	 * 
	 * @Title: findSysRoleByUserInfo   
	 * @Description:   根据用户获得角色
	 * @param @param userInfo
	 * @param @return   
	 * @return List<SysRole>    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月4日
	 */
	public List<SysRole> findSysRoleByUserInfo(SysUser userInfo) ;
	
	/**
	 * @Title: findSysMenuByRole   
	 * @Description: 获得权限菜单  
	 * @param @param sysRoles
	 * @param @return   
	 * @return List<SysMenu>    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月7日
	 */
	public List<SysMenu> findSysMenuByRole(List<SysRole> sysRoles) ;
	
	/**
	 * @Title: getAllUserList   
	 * @Description:   查询所有用户 
	 * @param @param params
	 * @param @return   
	 * @return PageInfo<UserInfo>    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月12日
	 */
	public PageInfo<SysUser> getAllUserList(Map<String, Object> params) ;
	
	/**
	 * @Title: sava   
	 * @Description: 保存用户信息
	 * @param @param userInfo   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月20日
	 */
	public void sava(SysUser userInfo) ;
	
	/**
	 * @Title: saveOrUpdateUserRole   
	 * @Description:  保存用户和角色关系 
	 * @param @param userId
	 * @param @param sysUserRoles   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月22日
	 */
	public void saveOrUpdateUserRole(Long userId,List<Long> ids) ;
	
	/**
	 * @Title: selectByPrimaryKey   
	 * @Description:  根据userid寻找用户 
	 * @param @param userId
	 * @param @return   
	 * @return UserInfo    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月22日
	 */
	public SysUser selectByPrimaryKey(Long userId) ;

	/**
	 * @Title: update   
	 * @Description: 更新  
	 * @param @param userInfo   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月22日
	 */
	public void update(SysUser userInfo) ;
	
	
	
	/**
	 * @Title: delete   
	 * @Description: 用户删除，状态为0  
	 * @param @param ids   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月23日
	 */
	public void delete(List<Long> ids) ;
	
	/**
	 * @Title: updatePassword   
	 * @Description: 更新密码  
	 * @param @param userId
	 * @param @param newPassword   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月25日
	 */
	public void updatePassword(Long userId,String newPassword) ;
	
}
