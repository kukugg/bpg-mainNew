package com.bpg.lr.controller.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.bpg.common.resultData.ResultData;
import org.bpg.common.utils.Constant;
import org.bpg.common.utils.RRException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bpg.lr.config.AbstractController;
import com.bpg.lr.login.model.SysMenu;
import com.bpg.lr.service.login.SysMenuService;

/**
 * @Title:SysMenuController
 * @Description:	
 * @Author hjp	
 * @Date 2018年7月6日
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController{
	
	private static final Logger log = LoggerFactory.getLogger(SysMenuController.class);
	
	@Autowired
	SysMenuService sysMenuService;
	
	/**
	 * @Title: nav   
	 * @Description: 导航菜单  
	 * @param @return   
	 * @return R    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月7日
	 */
	@RequestMapping("/nav")
	public ResultData nav() {
		
		List<SysMenu> menuList = sysMenuService.findUserMenuList(getUserId());
		return ResultData.ok().put("menuList", menuList);
		
	}
	
	/**
	 * @Title: list   
	 * @Description: 所有菜单列表  
	 * @param @return   
	 * @return List<SysMenu>    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月25日
	 */
	@RequestMapping("/list")
	public List<SysMenu> list() {
		
		List<SysMenu> menuList = sysMenuService.findAllMenu();
		return menuList;
	}
	
	/**
	 * @Title: select   
	 * @Description:  树菜单 
	 * @param @return   
	 * @return List<SysMenu>    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月25日
	 */
	@RequestMapping("/select")
	public ResultData select() {
		
		List<SysMenu> menuList = sysMenuService.findAllMenu();
		SysMenu sysMenu = new SysMenu();
		sysMenu.setMenuId(0L);
		sysMenu.setName("根菜单");
		sysMenu.setParentId(-1L);
		sysMenu.setOpen(true);
		menuList.add(sysMenu);
		
		return ResultData.ok().put("menuList", menuList);
	}

	/**
	 * @Title: save   
	 * @Description:  保存列表 
	 * @param @param sysMenu
	 * @param @return   
	 * @return ResultData    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月26日
	 */
	@RequestMapping("/save")
	public ResultData save(@RequestBody SysMenu sysMenu) {
		//数据校验
		verifyForm(sysMenu);
		try {
			sysMenuService.insert(sysMenu);
		} catch (Exception e) {
			log.error("保存菜单出错  ：" + e);
			ResultData.error("保存菜单出错");
		}
		
		return ResultData.ok();
	}
	
	
	/**
	 * @Title: info   
	 * @Description:  菜单信息 
	 * @param @param menuId
	 * @param @return   
	 * @return ResultData    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月26日
	 */
	@RequestMapping("/info/{menuId}")
	public ResultData info(@PathVariable("menuId") Long menuId) {
		
		SysMenu sysMenu = new SysMenu();
		try {
			sysMenu = sysMenuService.selectById(menuId);
		} catch (Exception e) {
			log.error("编辑菜单出错  ：" + e);
			ResultData.error("编辑菜单出错");
		}
		
		return ResultData.ok().put("menu", sysMenu);
	}
	
	
	/**
	 * @Title: update   
	 * @Description:  更新菜单 
	 * @param @param sysMenu
	 * @param @return   
	 * @return ResultData    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月26日
	 */
	@RequestMapping("/update")
	public ResultData update(@RequestBody SysMenu sysMenu) {

		verifyForm(sysMenu);
		
		try {
			
			sysMenuService.update(sysMenu);
			
		} catch (Exception e) {
			log.error("更新菜单出错  ：" + e);
			ResultData.error("更新菜单出错");
		}
		
		return ResultData.ok();
		
	}
	
	/**
	 * @Title: delete   
	 * @Description:删除菜单   
	 * @param @param menuId
	 * @param @return   
	 * @return ResultData    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月26日
	 */
	@RequestMapping("/delete")
	public ResultData delete(Long menuId) {
		if (menuId <= 36) {
			return ResultData.error("系统菜单，不能删除");
		}
		
		//判断是否有子菜单
		List<SysMenu> SysMenus = sysMenuService.queryListParentId(menuId);
		
		if (SysMenus.size() > 0) {
			return ResultData.error("请先删除子菜单");
		}
		try {
			sysMenuService.delete(menuId);
		} catch (Exception e) {
			log.error("删除菜单出错  ：" + e);
			ResultData.error("删除菜单出错");
		}
		
		return ResultData.ok();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @Title: verifyForm   
	 * @Description: 验证参数是否正确  
	 * @param @param menu   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月26日
	 */
	private void verifyForm(SysMenu menu) {

		if(StringUtils.isBlank(menu.getName())){
			throw new RRException("菜单名称不能为空");
		}
		
		if(menu.getParentId() == null){
			throw new RRException("上级菜单不能为空");
		}
		
		//菜单
		if(menu.getType() == Constant.MenuType.MENU.getValue()){
			if(StringUtils.isBlank(menu.getUrl())){
				throw new RRException("菜单URL不能为空");
			}
		}
		
		//上级菜单类型
		int parentType = Constant.MenuType.CATALOG.getValue();
		if(menu.getParentId() != 0){
			SysMenu parentMenu = sysMenuService.selectById(menu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//目录、菜单
		if(menu.getType() == Constant.MenuType.CATALOG.getValue() ||
				menu.getType() == Constant.MenuType.MENU.getValue()){
			if(parentType != Constant.MenuType.CATALOG.getValue()){
				throw new RRException("上级菜单只能为目录类型");
			}
			//return ;
		}
		
		//按钮
		if(menu.getType() == Constant.MenuType.BUTTON.getValue()){
			if(parentType != Constant.MenuType.MENU.getValue()){
				throw new RRException("上级菜单只能为菜单类型");
			}
			//return ;
		}
		
	}

}
