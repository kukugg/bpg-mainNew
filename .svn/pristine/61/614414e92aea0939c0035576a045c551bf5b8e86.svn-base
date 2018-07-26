package com.bpg.lr.login.dao;

import java.util.List;
import java.util.Map;

import com.bpg.lr.baseDao.BaseDao;
import com.bpg.lr.login.model.SysMenu;

public interface SysMenuDao extends BaseDao<SysMenu>{
	
    //根据角色id集合寻找权限列表
    List<SysMenu> findSysMenuByRole(Map<String, Object> params);
    
    //根据用户id寻找权限列表
    List<SysMenu> findSysMenuByUserId(Map<String, Object> params);
    
    //查询用户的所有菜单ID
	List<Long> queryAllMenuId(Long userId);
	
	//根据父菜单，查询子菜单
	List<SysMenu> queryListParentId(Long parentId);
	
	//查找所有菜单。
	List<SysMenu> findAllMenu();
  
}