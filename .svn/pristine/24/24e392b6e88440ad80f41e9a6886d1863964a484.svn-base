package com.bpg.lr.controller.login;


import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.bpg.common.resultData.ResultData;
import org.bpg.common.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @Title:HomeController
 * @Description: 系统登陆和系统页面视图	
 * @Author hjp	
 * @Date 2018年6月1日
 */
@Controller
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping({"/","/index"})
    public String index(){
	   if (ShiroUtils.getSubject().getPrincipal() == null) {
		   ShiroUtils.logout();
		   return "login";
	   }
       return "index";
    }
	
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
   
    @RequestMapping(value="/loginUser",method = RequestMethod.POST)
    @ResponseBody
    public ResultData loginUser(String username, String password){
    	// 登录失败从request中获取shiro处理的异常信息。  
    	try{
			Subject subject = ShiroUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
		}catch (UnknownAccountException e) {
			return ResultData.error("账号或密码不正确，请重新输入");
		}catch (IncorrectCredentialsException e) {
			log.info("IncorrectCredentialsException -- > 账号或密码不正确：");
			return ResultData.error("账号或密码不正确");
		}catch (LockedAccountException e) {
			log.info("LockedAccountException -- > 账号已被锁定,请联系管理员：");
			return ResultData.error("账号已被锁定,请联系管理员");
		}catch (AuthenticationException e) {
			log.info("AuthenticationException -- > 账户验证失败：失效用户");
			return ResultData.error("账户验证失败,失效用户");
		}
         return ResultData.ok();
    }
    
    //退出
    @RequestMapping("/logOut")
    public String logOut(HttpSession session) {
    	ShiroUtils.logout();
        return "login";
    }
    
    //主页
    @RequestMapping("/main.html")
	public String main(){
		return "main";
	}
    
	
	
	//菜单导航
	@RequestMapping("modules/{module}/{url}.html")
	public String module(@PathVariable("module") String module, @PathVariable("url") String url){
		return "modules/" + module + "/" + url;
	}

}
