package com.bpg.lr.controller.login;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bpg.common.resultData.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bpg.lr.config.AbstractController;
import com.bpg.lr.login.model.SysDept;
import com.bpg.lr.service.login.SysDeptService;



/**
 * @Title:SysDeptController
 * @Description:部门管理	
 * @Author hjp	
 * @Date 2018年6月12日
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController extends AbstractController{
	
	
	private static final Logger log = LoggerFactory.getLogger(SysDeptController.class);

	@Autowired
	private SysDeptService sysDeptService;
	
	/**
	 * @Title: getAllDeptList   
	 * @Description: 部门列表  
	 * @param @return   
	 * @return List<SysDept>    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月13日
	 */
	@RequestMapping("/list")
	public List<SysDept> getAllDeptList() {
		List<SysDept> deptList = null;
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			deptList = sysDeptService.getAllDeptList(params);
			
			//缓存使用例子 需要在配置文件配置缓存名字
		/*	EhcacheUtil instance = EhcacheUtil.getInstance(); 
			if (instance.get("constantCache", "deptList") != null) {
				deptList =  (List<SysDept>) instance.get("constantCache", "deptList");
				System.out.println();
			} else {
				instance.put("constantCache", "deptList", deptList);
			}*/
			
			
		} catch (Exception e) {
			log.error("部门列表查询出错： "+ e);
		}
		
		return deptList;
	}
	
	/**
	 * @Title: getParentId   
	 * @Description: 获取根部门id  
	 * @param @return   
	 * @return R    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月13日
	 */
	@RequestMapping("/info")
	public ResultData getParentId() {
		long deptId = 0; //默认最高层部门父id 为 0 
	/*	List<SysDept> deptList = sysDeptService.getAllDeptList();
		Long parantId = null;
		for (SysDept sysDept : deptList) {
			if (parantId == null) {
				parantId = sysDept.getParentId();
				continue;
			}
			if ()
		}*/
		
		return ResultData.ok().put("deptId", deptId);
	}
	
	/**
	 * @Title: selectDepteTree   
	 * @Description:  加载部门树，只加载自己层级下的 添加，修改部门 
	 * @param @return   
	 * @return R    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月13日
	 */
	@RequestMapping("/select")
	public ResultData selectDepteTree() {
		List<SysDept> deptList = null;
		Long deptId = getUser().getDeptId();
		Map<String, Object> params = new HashMap<String, Object>();
	//	params.put("deptId", deptId);
		try {
			deptList = sysDeptService.selectDepteTree(params);
		} catch (Exception e) {
			log.error("加载部门树出错： "+ e);
		}
		
		return ResultData.ok().put("deptList", deptList);
		
	}
	
	/**
	 * @Title: save   
	 * @Description: 保存  
	 * @param @return   
	 * @return R    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月13日
	 */
	@RequestMapping("/save")
	public ResultData save(@RequestBody SysDept sysDept) {
		try {
			sysDept.setDelFlag("0");
			sysDeptService.save(sysDept);
		} catch (Exception e) {
			log.error("保存 部门出错： "+ e);
			return ResultData.error();
		}
		
		return ResultData.ok();
		
	}
	
	/**
	 * @Title: findDeptByDeptId   
	 * @Description:   根据部门id查询部门信息
	 * @param @param deptId
	 * @param @return   
	 * @return R    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月13日
	 */
	@RequestMapping("/info/{deptId}")
	public ResultData findDeptByDeptId(@PathVariable("deptId") Long deptId) {
		SysDept sysDept = new SysDept();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deptId", deptId);
		try {
			List<SysDept> depts = sysDeptService.findDeptByDeptId(params);
			if (depts != null && depts.size() > 0) {
				sysDept = depts.get(0);
			}	
		} catch (Exception e) {
			log.error("查询部门id出错： "+ e);
		}
		
		return ResultData.ok().put("dept", sysDept);
	}
	
	/**
	 * @Title: update   
	 * @Description:修改  
	 * @param @return   
	 * @return R    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月13日
	 */
	@RequestMapping("/update")
	public ResultData update(@RequestBody SysDept sysDept) {
		try {
			if (!checkStatus(sysDept.getDeptId())) {
				return ResultData.error("只能删除本部门和子部门！");
			}
			sysDeptService.update(sysDept);
		} catch (Exception e) {
			log.error("修改 部门出错： "+ e);
			return ResultData.error();
		}
		
		return ResultData.ok();
		
	}
	
	/**
	 * @Title: delete   
	 * @Description:   删除部门 状态重置为-1
	 * @param @param deptId
	 * @param @return   
	 * @return R    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月14日
	 */
	@RequestMapping("/delete")
	public ResultData delete(long deptId) {
		//先判断是否有删除资格，同部门和子部门
		
		if (!checkStatus(deptId)) {
			return ResultData.error("只能删除本部门和子部门！");
		}
		
		//判断是否有子部门
		Map<String, Object> params = new HashMap<>();
		params.put("deptId", deptId);
		List<SysDept> sysDeptList2 = sysDeptService.selectDepteTree(params);
		if (sysDeptList2.size() != 1) {
			return ResultData.error("请先删除子部门");
		}
		
		try {
			sysDeptService.delete(deptId);
		} catch (Exception e) {
			log.error("删除出错："+e);
			return ResultData.error("删除出错");
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
	
	
	
	

}
