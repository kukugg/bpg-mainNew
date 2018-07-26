package com.bpg.lr.serviceImpl.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bpg.common.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bpg.lr.login.dao.SysMenuDao;
import com.bpg.lr.login.model.SysMenu;
import com.bpg.lr.service.login.SysMenuService;

/**
 * @Title:SysMenuServiceImpl
 * @Description: 人员管理实现类                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  	
 * @Author hjp	
 * @Date 2018年7月6日
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
	
	@Autowired
	SysMenuDao sysMenuDao;

	@Override
	public void deleteByPrimaryKey(Long id) {
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insert(SysMenu record) {
		return sysMenuDao.insertSelective(record);
	}

	@Override
	public int insertSelective(SysMenu record) {
		return 0;
	}

	@Override
	public SysMenu selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(SysMenu record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(SysMenu record) {
		return 0;
	}

	@Override
	public List<SysMenu> getAll() {
		return null;
	}

	/**
	 * 查询用户菜单接口  
	 */
	@Override
	public List<SysMenu> findUserMenuList(Long userId) {
		List<Long> menuIds = queryAllMenuId(userId);
		return getAllMenuList(menuIds);
	}

	/**
	 * 查询用户菜单id集合
	 */
	@Override
	public List<Long> queryAllMenuId(Long userId) {
		
		return sysMenuDao.queryAllMenuId(userId);
		
	}

	/**
	 * 查询用户菜单详情
	 */
	@Override
	public List<SysMenu> getAllMenuList(List<Long> menuIds) {
		//查询根菜单列表
		List<SysMenu> sysMenus = querysysMenusList(0L, menuIds);
		
		List<SysMenu> menuTreeList = getMenuTreeList(sysMenus,menuIds);
		return menuTreeList;
	}
	
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
	@Override
	public List<SysMenu> querysysMenusList(Long parentId, List<Long> menuIds) {
		List<SysMenu> sysMenus = queryListParentId(parentId);
		if (menuIds == null) {
			return sysMenus;
		}
		
		List<SysMenu> userSysMenus = new ArrayList<>();
		for (SysMenu menu : sysMenus) {
			if (menuIds.contains(menu.getMenuId())) {
				userSysMenus.add(menu);
			}
		}
		return userSysMenus;
		
	};
	
	
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
	@Override
	public List<SysMenu> queryListParentId(Long parentId) {
		return sysMenuDao.queryListParentId(parentId);
	};
	
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
	@Override
	public List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIds) {
		List<SysMenu> subMenuList = new ArrayList<SysMenu>();
		
		for (SysMenu sysMenu : menuList) {
			//获得目录
			if (sysMenu.getType() == Constant.MenuType.CATALOG.getValue()) {
				sysMenu.setList(getMenuTreeList(querysysMenusList(sysMenu.getMenuId(), menuIds), menuIds));
			}
			subMenuList.add(sysMenu);
		}
		
		return subMenuList;
	}

	/**
	 * @Title: queryNotButtonList   
	 * @Description:   获取所有的菜单列表
	 * @param @return   
	 * @return List<SysMenu>    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月7日
	 */
	@Override
	public List<SysMenu> findAllMenu() {
		List<SysMenu> menus = sysMenuDao.findAllMenu();
		return menus ;
	}

	/**
	 * @Title: delete   
	 * @Description:  删除菜单 
	 * @param @param menuId   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月7日
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(Long menuId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("menuId", menuId);
		sysMenuDao.deleteByPrimaryKey(params);
	}

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
	@Override
	public SysMenu selectById(Long menuId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("menuId", menuId);
		return sysMenuDao.selectByPrimaryKey(params);
	}

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
	@Override
	public void update(SysMenu sysMenu) {
		sysMenuDao.updateByPrimaryKeySelective(sysMenu);
	}

	
	
	

}
