package com.bpg.lr.service.login;

import java.util.List;
import java.util.Map;


import com.bpg.lr.login.model.SysDept;
import com.bpg.lr.service.baseService.BaseService;

/**
 * @Title:SysDeptService
 * @Description:	
 * @Author hjp	
 * @Date 2018年7月10日
 */
public interface SysDeptService extends BaseService<SysDept> {
	
	/**
	 * @Title: getAllDeptList   
	 * @Description: 部门treetable  
	 * @param    
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月13日
	 */
	public List<SysDept> getAllDeptList(Map<String, Object> params) ;
	
	/**
	 * @Title: selectDepteTree   
	 * @Description: 部门树加载 递归  
	 * @param @return   
	 * @return R    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月13日
	 */
	public List<SysDept> selectDepteTree(Map<String, Object> params) ;
	
	/**
	 * @Title: save   
	 * @Description:  保存部门 
	 * @param @param sysDept   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月13日
	 */
	public void save(SysDept sysDept) ;
	
	/**
	 * @Title: selectDepteTree   
	 * @Description: 根据部门id查询部门信息 
	 * @param @return   
	 * @return R    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月13日
	 */
	public List<SysDept> findDeptByDeptId(Map<String, Object> params) ;
	
	/**
	 * @Title: update   
	 * @Description:  修改部门 
	 * @param @param sysDept   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月13日
	 */
	public void update(SysDept sysDept) ;
	
	/**
	 * @Title: delete   
	 * @Description: 删除部门 状态重置为-1  
	 * @param @param deptId   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月14日
	 */
	public void delete(Long deptId) ;
	

}
