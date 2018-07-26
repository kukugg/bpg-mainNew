package com.bpg.lr.serviceImpl.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.bpg.common.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bpg.lr.login.dao.SysMenuDao;
import com.bpg.lr.login.dao.SysRoleDao;
import com.bpg.lr.login.dao.SysUserDao;
import com.bpg.lr.login.dao.SysUserRoleDao;
import com.bpg.lr.login.model.SysMenu;
import com.bpg.lr.login.model.SysRole;
import com.bpg.lr.login.model.SysUser;
import com.bpg.lr.login.model.SysUserRole;
import com.bpg.lr.service.login.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Title:LoginServiceImpl
 * @Description:人员管理实现类	
 * @Author hjp	
 * @Date 2018年7月4日
 */
@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	SysUserDao sysUserDao;
	
	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Autowired
	private SysMenuDao sysMenuDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Override
	public void deleteByPrimaryKey(Long id) {
		
	}

	@Override
	public int insert(SysUser record) {
		return 0;
	}

	@Override
	public int insertSelective(SysUser record) {
		return 0;
	}

	//根据userid寻找用户 
	@Override
	public SysUser selectByPrimaryKey(Long id) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userId", id);
		SysUser selectByPrimaryKey = sysUserDao.selectByPrimaryKey(params);
		return selectByPrimaryKey;
	}

	@Override
	public int updateByPrimaryKeySelective(SysUser record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(SysUser record) {
		return 0;
	}

	@Override
	public List<SysUser> getAll() {
		return null;
	}

	/**
	 * 根据用户名准确查找用户
	 */
	
	@Override
	public SysUser findByUserName(String userName) {
		SysUser sysUser = sysUserDao.findByUserName(userName);
		return sysUser;
	}

	//根据用户获得角色
	@Override
	public List<SysRole> findSysRoleByUserInfo(SysUser userInfo) {
		List<SysRole> sysRoles = sysRoleDao.findSysRoleByUserInfo(userInfo);
		return sysRoles;
	}

	// 获得权限菜单  
	@Override
	public List<SysMenu> findSysMenuByRole(List<SysRole> sysRoles) {
		List<SysMenu> sysMenus = new ArrayList<SysMenu>();
		if (sysRoles != null) {
			List<Long> ids = new ArrayList<Long>();
			for (SysRole sysRole : sysRoles) {
				ids.add(sysRole.getRoleId());
			}
			if (ids.size() > 0 ) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("ids", ids);
				sysMenus = sysMenuDao.findSysMenuByRole(params);
				
			}
			
		}
		
		return sysMenus;
	}

	//查询所有用户 
	@Override
	public PageInfo<SysUser> getAllUserList(Map<String, Object> params) {
		int pageNum = 1;
		int pageSize = 10;
		if (params.get("page") != null && params.get("page") != "") {
			pageNum = Integer.parseInt(params.get("page")+"");
		}
		if (params.get("limit") != null && params.get("limit") != "") {
			pageSize = Integer.parseInt(params.get("limit")+"");
		}
		PageHelper.startPage(pageNum, pageSize);
		List<SysUser> resultList = new ArrayList<SysUser>();
		String username = "";
		if (params.get("username") != null) {
			username = params.get("username")+"";
		}
		resultList =  sysUserDao.getAllUserList(username);
		PageInfo<SysUser> result = new PageInfo<SysUser>(resultList);
		return result;
	}

	//保存用户信息
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void sava(SysUser userInfo) {
		if (userInfo.getPassword() != null && !"".equals(userInfo.getPassword())) {
			//sha256加密
			String salt = RandomStringUtils.randomAlphanumeric(20);
			userInfo.setSalt(salt);
			userInfo.setPassword(ShiroUtils.sha256(userInfo.getPassword(), userInfo.getSalt()));
		}
		sysUserDao.insertSelective(userInfo);
		
		//保存角色关系
		if (userInfo.getRoleIdList() != null && userInfo.getRoleIdList().size() > 0) {
			
			saveOrUpdateUserRole(userInfo.getUserId(),userInfo.getRoleIdList());
			
		}
	}

	//保存用户和角色关系 
	@Override
	public void saveOrUpdateUserRole(Long userId, List<Long> ids) {
		if (userId != null) {
			//删除角色关系
			sysUserRoleDao.deleteRoleyUserId(userId);
			List<SysUserRole> sysUserRoles = new ArrayList<SysUserRole>();
			for(Long id : ids) {
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setRoleId(id);
				sysUserRole.setUserId(userId);
				sysUserRoles.add(sysUserRole);
			}
			
			sysUserRoleDao.insertByBatch(sysUserRoles);
			
			
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(SysUser userInfo) {
		if (StringUtils.isNotBlank(userInfo.getPassword())) {
			userInfo.setPassword(ShiroUtils.sha256(userInfo.getPassword(), userInfo.getSalt()));
		}
		
		sysUserDao.updateByPrimaryKeySelective(userInfo);
		
		//保存用户与角色关系
		if (userInfo.getRoleIdList() != null && userInfo.getRoleIdList().size() > 0) {
			saveOrUpdateUserRole(userInfo.getUserId(),userInfo.getRoleIdList());
		}
	}

	//用户删除，状态为0  
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(List<Long> ids) {
		sysUserDao.deleteByBatch(ids);
	}

	//更新密码  
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updatePassword(Long userId, String newPassword) {
		SysUser userInfo = new SysUser();
		userInfo.setUserId(userId);
		userInfo.setPassword(newPassword);
		sysUserDao.updateByPrimaryKeySelective(userInfo);
	}

	

	

}
