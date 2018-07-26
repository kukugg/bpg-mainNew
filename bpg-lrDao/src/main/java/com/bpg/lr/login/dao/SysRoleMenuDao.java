package com.bpg.lr.login.dao;

import java.util.List;

import com.bpg.lr.baseDao.BaseDao;
import com.bpg.lr.login.model.SysRoleMenu;


public interface SysRoleMenuDao extends BaseDao<SysRoleMenu>{
    
    
    // 根据roleid 删除角色关系
    int deleteByRoleId(Long roleId);
    
    //批量插入
    void insertByBatch(List<SysRoleMenu> sysRoleMenus);
    
    //根据角色id寻找菜单id
    List<Long> getSysMenuIdsByRoleId(Long roleId);
    
    
}