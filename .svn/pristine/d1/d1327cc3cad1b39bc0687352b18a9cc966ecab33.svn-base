package com.bpg.lr.serviceImpl.login;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bpg.lr.login.dao.SysDeptDao;
import com.bpg.lr.login.model.SysDept;
import com.bpg.lr.service.login.SysDeptService;

/**
 * @Title:SysDeptImpl
 * @Description:	
 * @Author hjp	
 * @Date 2018年7月10日
 */
@Service
public class SysDeptImpl implements SysDeptService {
	
	@Autowired
	private SysDeptDao sysDeptDao;

	@Override
	public void deleteByPrimaryKey(Long id) {
	}

	@Override
	public int insert(SysDept record) {
		return 0;
	}

	@Override
	public int insertSelective(SysDept record) {
		return 0;
	}

	@Override
	public SysDept selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(SysDept record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(SysDept record) {
		return 0;
	}

	

	@Override
	public List<SysDept> getAll() {
		return null;
	}

	/**
	 * @Title: getAllDeptList   
	 * @Description: 部门treetable  
	 * @param    
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月13日
	 */
	@Override
	public List<SysDept> getAllDeptList(Map<String, Object> params) {
		List<SysDept> deptList = sysDeptDao.getAllDeptList(params);
		return deptList;
	}

	
	/**
	 * @Title: selectDepteTree   
	 * @Description: 部门树加载 递归  
	 * @param @return   
	 * @return R    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月13日
	 */
	@Override
	public List<SysDept> selectDepteTree(Map<String, Object> params) {
		List<SysDept> deptList = sysDeptDao.getAllDeptList(params);
		return deptList;
	}

	
	/**
	 * @Title: save   
	 * @Description:  保存部门 
	 * @param @param sysDept   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月13日
	 */
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysDept sysDept) {
		sysDeptDao.insert(sysDept);
	}

	/**
	 * @Title: selectDepteTree   
	 * @Description: 根据部门id查询部门信息 
	 * @param @return   
	 * @return R    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月13日
	 */
	@Override
	public List<SysDept> findDeptByDeptId(Map<String, Object> params) {
		List<SysDept> deptList = sysDeptDao.findDeptByDeptId(params);
		return deptList;
	}

	/**
	 * @Title: update   
	 * @Description:  修改部门 
	 * @param @param sysDept   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月13日
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(SysDept sysDept) {
		sysDeptDao.updateByPrimaryKeySelective(sysDept);
	}

	/**
	 * @Title: delete   
	 * @Description: 删除部门 状态重置为-1  
	 * @param @param deptId   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月14日
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(Long deptId) {
		sysDeptDao.updateByDeptId(deptId);
	}

}
