package com.bpg.lr.login.dao;

import java.util.List;
import java.util.Map;

import com.bpg.lr.baseDao.BaseDao;
import com.bpg.lr.login.model.SysRole;
import com.bpg.lr.login.model.SysUser;



public interface SysRoleDao extends BaseDao<SysRole>{
   
    
    //根据用户获得角色集合
    List<SysRole> findSysRoleByUserInfo(SysUser userInfo);
    
    //获得所有角色
    List<SysRole> getAllSysRole(Map<String, Object> params);
    
    //批量删除角色
    void deleteBatchIds(Long[] roleIds);
    
    //批量删除角色用户关系
    void deleteBatchRoleAndUser(Long[] roleIds);
    
    //批量删除角色菜单关系
    void deleteBatchRoleAndMenu(Long[] roleIds);
    
}