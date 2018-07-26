package com.bpg.lr.login.dao;

import java.util.List;

import com.bpg.lr.baseDao.BaseDao;
import com.bpg.lr.login.model.SysUserRole;


public interface SysUserRoleDao extends BaseDao<SysUserRole>{

    
    //根据用户id删除角色关系
    int deleteRoleyUserId(Long userId);
    
    //批量插入
    void insertByBatch(List<SysUserRole> sysUserRoles);

}