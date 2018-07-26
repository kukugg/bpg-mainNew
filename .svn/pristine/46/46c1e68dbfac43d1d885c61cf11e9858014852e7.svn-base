package com.bpg.lr.serviceImpl.login;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bpg.lr.login.dao.SysRoleDao;
import com.bpg.lr.login.dao.SysRoleMenuDao;
import com.bpg.lr.login.model.SysRole;
import com.bpg.lr.login.model.SysRoleMenu;
import com.bpg.lr.service.login.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Title:SysRoleServiceImpl
 * @Description:	
 * @Author hjp	
 * @Date 2018年7月10日
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
	
	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Override
	public void deleteByPrimaryKey(Long id) {
	}

	@Override
	public int insert(SysRole record) {
		return 0;
	}

	@Override
	public int insertSelective(SysRole record) {
		return 0;
	}

	@Override
	public SysRole selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(SysRole record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(SysRole record) {
		return 0;
	}

	@Override
	public List<SysRole> getAll() {
		return null;
	}

	//获得所有角色  
	@Override
	public List<SysRole> getAllSysRole() {
		return sysRoleDao.getAllSysRole(null);
	}

	//分页查询角色列表 
	@Override
	public PageInfo<SysRole> getAllRolePage(Map<String, Object> params) {
		int pageNum = 1;
		int pageSize = 10;
		if (params.get("page") != null && params.get("page") != "") {
			pageNum = Integer.parseInt(params.get("page")+"");
		}
		if (params.get("limit") != null && params.get("limit") != "") {
			pageSize = Integer.parseInt(params.get("limit")+"");
		}
		PageHelper.startPage(pageNum, pageSize);
		List<SysRole> allSysRole = sysRoleDao.getAllSysRole(params);
		PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(allSysRole);
		return pageInfo;
	}

	//保存
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void save(SysRole sysRole) {
		sysRole.setCreateTime(new Date());
		sysRoleDao.insertSelective(sysRole);
		if (sysRole.getMenuIdList() != null && sysRole.getMenuIdList().size() > 0) {
			saveOrUpdate(sysRole.getRoleId(), sysRole.getMenuIdList());
		}
	}

	//批量保存或者修改角色菜单关系  
	@Override
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
		//删除角色菜单关系
		if (roleId != null) {
			sysRoleMenuDao.deleteByRoleId(roleId);
		} 
		
		if (menuIdList.size() == 0) {
			return ;
		}
		
		//保存角色与菜单关系
		List<SysRoleMenu> sysRoleMenus = new ArrayList<SysRoleMenu>();
		
		for (Long menuId : menuIdList) {
			SysRoleMenu sysRoleMenu = new SysRoleMenu();
			sysRoleMenu.setMenuId(menuId);
			sysRoleMenu.setRoleId(roleId);
			sysRoleMenus.add(sysRoleMenu);
		}
		
		sysRoleMenuDao.insertByBatch(sysRoleMenus);
	}

	//根据id寻找角色
	@Override
	public SysRole getSysRoleById(Long roleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		return sysRoleDao.selectByPrimaryKey(params);
	}

	
	//根据角色id寻找菜单id  
	@Override
	public List<Long> getSysMenuIdsByRoleId(Long roleId) {
		return sysRoleMenuDao.getSysMenuIdsByRoleId(roleId);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(SysRole sysRole) {
		sysRoleDao.updateByPrimaryKeySelective(sysRole);
		if (sysRole.getMenuIdList() != null) {
			saveOrUpdate(sysRole.getRoleId(), sysRole.getMenuIdList());
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteBatch(Long[] roleIds) {
		//删除角色
		sysRoleDao.deleteBatchIds(roleIds);
		
		//删除角色用户关系
		sysRoleDao.deleteBatchRoleAndUser(roleIds);
		
		//删除角色菜单关系
		
		sysRoleDao.deleteBatchRoleAndMenu(roleIds);
	}

}
