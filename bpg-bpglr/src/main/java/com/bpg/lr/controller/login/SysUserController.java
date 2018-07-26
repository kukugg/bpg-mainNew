package com.bpg.lr.controller.login;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.bpg.common.resultData.ResultData;
import org.bpg.common.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpg.lr.config.AbstractController;
import com.bpg.lr.login.model.SysDept;
import com.bpg.lr.login.model.SysRole;
import com.bpg.lr.login.model.SysUser;
import com.bpg.lr.service.login.SysDeptService;
import com.bpg.lr.service.login.SysUserService;

import com.github.pagehelper.PageInfo;


/**
 * @Title:SysUserController
 * @Description:用户管理	
 * @Author hjp	
 * @Date 2018年7月4日
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	
	private static final Logger log = LoggerFactory.getLogger(SysUserController.class);

		
	@Autowired
	SysUserService userInfoService;
	
	@Autowired
	private SysDeptService sysDeptService;
	
	/**
	 * @Title: info   
	 * @Description: 获取用户的登陆信息  
	 * @param @return   
	 * @return R    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月12日
	 */
	@RequestMapping("/info")
	public ResultData info() {
		return ResultData.ok().put("user", getUserSession());
	}
	
	/**
	 * @Title: getAllUserList   
	 * @Description:员工列表   
	 * @param @param params
	 * @param @return   
	 * @return R    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月12日
	 */
	@RequestMapping("/list")
	public ResultData getAllUserList(@RequestParam Map<String, Object> params) {
		PageInfo<SysUser> pageInfo = new PageInfo<SysUser>();
		try {
			pageInfo = userInfoService.getAllUserList(params);
		} catch (Exception e) {
			log.error("查询员工列表出错");
		}
		
		return ResultData.ok().put("page", pageInfo);
	
		
	}
	
	
	/**
	 * @Title: save   
	 * @Description: 保存员工列表  
	 * @param @param userInfo
	 * @param @return   
	 * @return R    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月19日
	 */
	@RequestMapping("/save")
	public ResultData save(@RequestBody SysUser userInfo) {
		if (userInfo.getStatus() == null) {
			userInfo.setStatus("1");
		}
		userInfo.setCreateTime(new Date());
		
		try {
			userInfoService.sava(userInfo);
		} catch (Exception e) {
			log.error("保存员工列表出错："+e);
			return ResultData.error("保存员工列表出错");
		}
		return ResultData.ok();
	}
	
	/**
	 * @Title: userInfo   
	 * @Description:  根据id查询员工信息 
	 * @param @param userId
	 * @param @return   
	 * @return ResultData    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月22日
	 */
	@RequestMapping("/info/{userId}")
	public ResultData userInfo(@PathVariable("userId") Long userId) {
		SysUser userInfo = new SysUser();
		try {
			userInfo = userInfoService.selectByPrimaryKey(userId);
			List<SysRole> findSysRoleByUserInfo = userInfoService.findSysRoleByUserInfo(userInfo);		  
			List<Long> longs = new ArrayList<Long>();
			if (findSysRoleByUserInfo != null && findSysRoleByUserInfo.size() > 0) {
				
				for (SysRole sysRole : findSysRoleByUserInfo) {
					longs.add(sysRole.getRoleId());
				}
				
			}
			userInfo.setRoleIdList(longs);
		} catch (Exception e) {
			log.error("查询员工列表出错："+e);
			return ResultData.error("查询员工列表出错");
		}
		return ResultData.ok().put("user", userInfo);
	}
	
	
	/**
	 * @Title: update   
	 * @Description: 更新  
	 * @param @param user
	 * @param @return   
	 * @return ResultData    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月22日
	 */
	@RequestMapping("/update")
	public ResultData update(@RequestBody SysUser user) {
		
		try {
			//先判断是否有修改资格，同部门和子部门
			if (!checkStatus(user.getDeptId())) {
				return ResultData.error("只能修改本部门和子部门！");
			}
			
			userInfoService.update(user);
		} catch (Exception e) {
			log.error("更新员工列表出错："+e);
			return ResultData.error("更新员工列表出错");
		}
		
		return ResultData.ok();
		
	}
	
	/**
	 * @Title: checkStatus   
	 * @Description:  查询是否有资格操作该部门 只能操作下级部门和当前部门 
	 * @param @param deptId
	 * @param @return   
	 * @return boolean    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月23日
	 */
	public boolean checkStatus(Long deptId) {
		Map<String, Object> params = new HashMap<>();
		params.put("deptId", getDeptId());
		boolean delteFlag = false;
		List<SysDept> sysDeptList = sysDeptService.selectDepteTree(params);
		if (sysDeptList != null && sysDeptList.size() > 0) {
			for (SysDept sysDept : sysDeptList) {
				if (sysDept.getDeptId() == deptId) {
					delteFlag = true;
					break;
				} 
			}
			if (!delteFlag) {
				return false;
			}
			
		} else {
			return false;
		}
		
		return true;
	}
	
	/**
	 * @Title: delete   
	 * @Description:  删除用户 
	 * @param @param userIds
	 * @param @return   
	 * @return ResultData    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月23日
	 */
	@RequestMapping("/delete")
	public ResultData delete(@RequestBody Long[] userIds) {
		if(ArrayUtils.contains(userIds, 1L)){
			return ResultData.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return ResultData.error("当前用户不能删除");
		}
		
		try {
			userInfoService.delete(Arrays.asList(userIds));
		} catch (Exception e) {
			log.error("删除员工出错："+e);
			return ResultData.error("删除员工出错");
		}
		
		return ResultData.ok();
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping("/password")
	public ResultData password(String password,String newPassword) {
		
		//原密码
		password = ShiroUtils.sha256(password, getUser().getSalt());
		//新密码
		newPassword = ShiroUtils.sha256(newPassword, getUser().getSalt());
		
        String oldPassword = getUser().getPassword();
        
        if (!password.equals(oldPassword)) {
        	return ResultData.error("原密码不正确");
        }
        
        //更新密码
    	try {
    		userInfoService.updatePassword(getUserId(), newPassword);
		} catch (Exception e) {
			log.error("保存密码出错："+e);
			return ResultData.error("保存密码出错");
		}
        
    	ShiroUtils.logout();
        return ResultData.ok();
		
	}

	
		
}
