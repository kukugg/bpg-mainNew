package com.bpg.lr.config;


import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.bpg.common.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;


/**
 * @Title:ShiroConfiguration
 * @Description:Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。 
既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。	
 * @Author hjp	
 * @Date 2018年6月4日
 */
@Configuration
public class ShiroConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(ShiroConfiguration.class);
	 /** 
     * ShiroFilterFactoryBean 处理拦截资源文件问题。 
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在 
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager 
     * 
        Filter Chain定义说明 
       1、一个URL可以配置多个Filter，使用逗号分隔 
       2、当设置多个过滤器时，全部验证通过，才视为通过 
       3、部分过滤器可指定参数，如perms，roles 
     * 
     */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 设置 SecurityManager  
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
	
		
		//不设置默认获取login页面
		shiroFilterFactoryBean.setLoginUrl("/login");  
		// 登录成功后要跳转的链接  
        shiroFilterFactoryBean.setSuccessUrl("/index"); 
         //未授权界面;  
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        
        //匿名可以访问的链接
        filterChainDefinitionMap.put("/static/*", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/loginUser", "anon");
        
        //过滤链定义，从上向下顺序执行，一般将 放在最为下边 ,authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
   
       
        filterChainDefinitionMap.put("/*", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	
	/**
	 * @Title: securityManager   
	 * @Description:   核心
	 * @param @return   
	 * @return SecurityManager    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月5日
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//注入 shiroRealm
		securityManager.setRealm(shiroRealm());  
		//注入缓存管理器
		securityManager.setCacheManager(ehCacheManager());
		return securityManager;
	}
	
	/**
	 * @Title: shiroRealm   
	 * @Description:  身份证认证的realm注入 ，密码器注入
	 * @param @return   
	 * @return ShiroRealm    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月5日
	 */
	@Bean
	public ShiroRealm shiroRealm() {
		ShiroRealm shiroRealm = new ShiroRealm();
		shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return shiroRealm;
	}
	
	/**
	 * @Title: hashedCredentialsMatcher   
	 * @Description: 凭证匹配器  
	 * @param @return   
	 * @return HashedCredentialsMatcher    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月5日
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);  //散列算法:这里使用SHA-256算法;  
		hashedCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations); //散列的次数，比如散列两次;  
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return hashedCredentialsMatcher;
	}
	
	/**
	 * @Title: authorizationAttributeSourceAdvisor   
	 * @Description:   开启aop 注解支持，使用代理方式
	 * @param @param securityManager
	 * @param @return   
	 * @return AuthorizationAttributeSourceAdvisor    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月5日
	 */
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
	
	/**
	 * @Title: ehCacheManager   
	 * @Description:  shiro缓存管理器 
	 * @param @return   
	 * @return EhCacheManager    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月5日
	 */
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public EhCacheManager ehCacheManager() {
		log.info("ShiroConfiguration.getEhCacheManager()");
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
		return cacheManager;
	}
	
	/**
	 * @Title: lifecycleBeanPostProcessor   
	 * @Description: 管理bean生命周期   
	 * @param @return   
	 * @return LifecycleBeanPostProcessor    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年6月6日
	 */
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		log.info("ShiroConfiguration--lifecycleBeanPostProcessor");
		return new LifecycleBeanPostProcessor();
	}


	/**
	 * 动态代理创建
	 */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    	log.info("ShiroConfiguration--defaultAdvisorAutoProxyCreator");
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
