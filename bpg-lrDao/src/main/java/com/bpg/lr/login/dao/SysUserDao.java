package com.bpg.lr.login.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bpg.lr.baseDao.BaseDao;
import com.bpg.lr.login.model.SysUser;

public interface SysUserDao extends BaseDao<SysUser> {
   
	 //用户名查询用户信息sys登陆用 用 准确查询 
    SysUser findByUserName(@Param("username")  String username);
    
    //查询所有用户列表
    List<SysUser> getAllUserList(@Param("username") String username);
    
    //状态重置为0
    void deleteByBatch(@Param("ids") List<Long> ids); 
    
}