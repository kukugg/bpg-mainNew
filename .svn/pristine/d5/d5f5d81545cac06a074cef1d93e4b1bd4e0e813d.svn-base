package com.bpg.lr.controller.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bpg.common.resultData.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpg.lr.login.model.SysRole;
import com.bpg.lr.service.login.SysRoleService;
import com.github.pagehelper.PageInfo;

/**
 * @Title:SysRoleController
 * @Description:	
 * @Author hjp	
 * @Date 2018年6月15日
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {
	
	
	private static final Logger log = LoggerFactory.getLogger(SysRoleController.class);

	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * @Title: getAllSysRole   
	 * @Description: 获得所有角色  
	 * @param @return   
	 * @return R    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月15日
	 */
	@RequestMapping("/select")
	public ResultData getAllSysRole() {
		
		List<SysRole> sysRoleList = new ArrayList<SysRole>();
		try {
			sysRoleList = sysRoleService.getAllSysRole();
		} catch (Exception e) {
			log.error("获得所有角色  ：" + e);
		}
				
		return ResultData.ok().put("list", sysRoleList);
	}
	
	/**
	 * @Title: getAllRolePage   
	 * @Description: 分页查询角色列表  
	 * @param @param params
	 * @param @return   
	 * @return ResultData    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月27日
	 */
	@RequestMapping("/list")
	public ResultData getAllRolePage(@RequestParam Map<String, Object> params) {
		
		PageInfo<SysRole> pageInfo = new PageInfo<SysRole>();
		try {
			pageInfo = sysRoleService.getAllRolePage(params);
		} catch (Exception e) {
			log.error("查询角色列表出错: "+e);
			return ResultData.error("查询角色列表出错");
		}
		
		return ResultData.ok().put("page", pageInfo);
	}
	
	/**
	 * @Title: save   
	 * @Description:  保存 
	 * @param @param role
	 * @param @return   
	 * @return ResultData    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月27日
	 */
	@RequestMapping("/save")
	public ResultData save(@RequestBody SysRole role) {
		
		try {
			sysRoleService.save(role);
		} catch (Exception e) {
			log.error("保存角色出错: "+e);
			return ResultData.error("保存角色出错");
		}
		
		return ResultData.ok();
		
	}
	
	/**
	 * @Title: info   
	 * @Description:  角色信息 
	 * @param @param roleId
	 * @param @return   
	 * @return ResultData    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月27日
	 */
	@RequestMapping("/info/{roleId}")
	public ResultData info(@PathVariable("roleId") Long roleId) {
		
		SysRole sysRole = new SysRole();
		List<Long> ids = null;
		try {
			
			sysRole = sysRoleService.getSysRoleById(roleId);
			//查询角色对应的菜单
			ids = sysRoleService.getSysMenuIdsByRoleId(roleId);
			
		} catch (Exception e) {
			log.error("查询角色出错: "+e);
			return ResultData.error("查询角色出错");
		}
		
		sysRole.setMenuIdList(ids);
		return ResultData.ok().put("role", sysRole);
	}
	
	
	
	/**
	 * @Title: update   
	 * @Description:  更新
	 * @param @param role
	 * @param @return   
	 * @return ResultData    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月27日
	 */
	@RequestMapping("/update")
	public ResultData update(@RequestBody SysRole role) {
		
		try {
			sysRoleService.update(role);
			
		} catch (Exception e) {
			log.error("更新角色出错: "+e);
			return ResultData.error("更新角色出错");
		}
		
		return ResultData.ok();
		
	}
	
	/**
	 * @Title: delete   
	 * @Description: 删除角色  
	 * @param @param roleIds
	 * @param @return   
	 * @return ResultData    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月27日
	 */
	@RequestMapping("/delete")
	public ResultData delete(@RequestBody Long[] roleIds) {
		try {
			sysRoleService.deleteBatch(roleIds);
		} catch (Exception e) {
			log.error("删除角色出错: "+e);
			return ResultData.error("删除角色出错");
		}
		
		return ResultData.ok();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
