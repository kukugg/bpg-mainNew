package com.bpg.lr.service.login;


import java.util.List;
import java.util.Map;
import com.bpg.lr.login.model.SysRole;
import com.bpg.lr.service.baseService.BaseService;
import com.github.pagehelper.PageInfo;


/**
 * @Title:SysRoleService
 * @Description: 角色管理
 * @Author hjp	
 * @Date 2018年6月15日
 */

public interface SysRoleService extends BaseService<SysRole>{
	

	
	/**
	 * @Title: getAllSysRole   
	 * @Description: 获得所有角色  
	 * @param @return   
	 * @return List<SysRole>    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月27日
	 */
	public List<SysRole> getAllSysRole() ;
	
	/**
	 * @Title: getAllRolePage   
	 * @Description:  分页查询角色列表 
	 * @param @param params
	 * @param @return   
	 * @return PageInfo<SysRole>    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月27日
	 */
	public PageInfo<SysRole> getAllRolePage(Map<String, Object> params) ;
	
	/**
	 * @Title: save   
	 * @Description:  保存角色
	 * @param @param sysRole   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月27日
	 */
	public void save(SysRole sysRole) ;
	
	
	/**
	 * @Title: saveOrUpdate   
	 * @Description: 批量保存或者修改角色菜单关系  
	 * @param @param roleId
	 * @param @param menuIdList   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月27日
	 */
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) ;
	
	/**
	 * @Title: getSysRoleById   
	 * @Description:   根据id寻找角色
	 * @param @param roleId
	 * @param @return   
	 * @return SysRole    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月27日
	 */
	public SysRole getSysRoleById(Long roleId) ;
	/**
	 * @Title: getSysMenuIdsByRoleId   
	 * @Description: 根据角色id寻找菜单id  
	 * @param @param roleId
	 * @param @return   
	 * @return List<Long>    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月27日
	 */
	public List<Long> getSysMenuIdsByRoleId(Long roleId) ;
	/**
	 * @Title: update   
	 * @Description:  修改 
	 * @param @param sysRole   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月27日
	 */
	public void update(SysRole sysRole) ;
	
	/**
	 * @Title: deleteBatch   
	 * @Description:  删除 
	 * @param @param roleIds   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月28日
	 */
	public void deleteBatch(Long[] roleIds) ;
	
	

}
