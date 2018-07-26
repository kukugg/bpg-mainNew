package com.bpg.lr.service.login;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bpg.lr.login.model.SysMenu;
import com.bpg.lr.service.baseService.BaseService;

/**
 * @Title:SysMenuService
 * @Description:菜单管理接口	
 * @Author hjp	
 * @Date 2018年7月6日
 */
public interface SysMenuService extends BaseService<SysMenu>{
	
	/**
	 * @Title: queryListParentId   
	 * @Description:   根据根菜单提取用户子菜单
	 * @param @param parentId 父菜单ID
	 * @param @param menuIdList 用户菜单ID
	 * @param @return   
	 * @return List<SysMenu>    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月7日
	 */
	public List<SysMenu> querysysMenusList(Long parentId, List<Long> menuIds) ;

	
	/**
	 * @Title: queryListParentId   
	 * @Description:   根据父菜单，查询子菜单
	 * @param @param parentId
	 * @param @return   
	 * @return List<SysMenu>    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月7日
	 */
	public List<SysMenu> queryListParentId(Long parentId) ;
	
	
	/**
	 * @Title: queryNotButtonList   
	 * @Description:   获取所有的菜单列表
	 * @param @return   
	 * @return List<SysMenu>    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月7日
	 */
	public List<SysMenu> findAllMenu() ;
	/**
	 * @Title: findUserMenuList   
	 * @Description:   获取用户菜单列表
	 * @param @param userId
	 * @param @return   
	 * @return List<SysMenu>    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月7日
	 */
	public List<SysMenu> findUserMenuList(Long userId) ;

	/**
	 * @Title: delete   
	 * @Description:  删除菜单 
	 * @param @param menuId   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月7日
	 */
	public void delete(Long menuId) ;
	/**
	 * @Title: queryAllMenuId   
	 * @Description: 查询用户所有菜单id   
	 * @param @param userId
	 * @param @return   
	 * @return List<Long>    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月7日
	 */
	public List<Long> queryAllMenuId(Long userId) ;
	
	/**
	 * @Title: getAllMenuList   
	 * @Description: 获得用户所属菜单列表  
	 * @param @param menuIds
	 * @param @return   
	 * @return List<SysMenu>    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月7日
	 */
	public List<SysMenu> getAllMenuList(List<Long> menuIds) ;
	
	/**
	 * @Title: getMenuTreeList   
	 * @Description:   递归获得子菜单
	 * @param @param menuList
	 * @param @param menuIds
	 * @param @return   
	 * @return List<SysMenu>    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月7日
	 */
	public List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIds) ;
	
	/**
	 * @Title: selectById   
	 * @Description:  根据id查找菜单 
	 * @param @param menuId
	 * @param @return   
	 * @return SysMenu    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月26日
	 */
	public SysMenu selectById(Long menuId) ;
	
	
	/**
	 * @Title: update   
	 * @Description: 更新菜单  
	 * @param @param sysMenu   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月26日
	 */
	@Transactional(rollbackFor = Exception.class)
	public void update(SysMenu sysMenu);
	

}
