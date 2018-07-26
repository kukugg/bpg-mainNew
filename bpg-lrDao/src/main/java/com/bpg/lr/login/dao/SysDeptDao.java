package com.bpg.lr.login.dao;

import java.util.List;
import java.util.Map;

import com.bpg.lr.baseDao.BaseDao;
import com.bpg.lr.login.model.SysDept;


public interface SysDeptDao extends BaseDao<SysDept>
{
    
    
    //部门列表 treetabl递归查询
    List<SysDept> getAllDeptList(Map<String, Object> params);
    
    //根据id查询部门信息
    List<SysDept> findDeptByDeptId(Map<String, Object> params);
    
    //删除部门，状态重置为-1
    int updateByDeptId(Long deptId);
    
}