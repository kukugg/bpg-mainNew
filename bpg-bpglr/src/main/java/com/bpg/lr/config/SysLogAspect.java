package com.bpg.lr.config;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.bpg.common.annotation.SysLogAt;
import org.bpg.common.utils.HttpContextUtils;
import org.bpg.common.utils.IPUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.json.JSONUtils;
import com.bpg.lr.login.model.SysLog;
import com.bpg.lr.login.model.SysUser;
import com.bpg.lr.service.login.SysLogService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 日志切面拦截
 * @author hanjp
 *
 */
@Aspect
@Component
public class SysLogAspect {
	
	@Autowired
	private SysLogService sysLogService;
	
	//切点
	@Pointcut("@annotation(org.bpg.common.annotation.SysLogAt)")
	public void logPointCut() {
		
	}
	
	//环绕
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		//保存日志
		saveSysLog(point, time);

		return result;
	}
	
	
	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		SysLog sysLog = new SysLog();
		SysLogAt syslogAt = method.getAnnotation(SysLogAt.class);
		if(syslogAt != null){
			//注解上的描述
			sysLog.setOperation(syslogAt.value());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");

		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			String params = new ObjectMapper().writeValueAsString(args[0]);
			sysLog.setParams(params);
		}catch (Exception e){

		}

		//获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		//设置IP地址
		sysLog.setIp(IPUtils.getIpAddr(request));

		//用户名
		String username = ((SysUser) SecurityUtils.getSubject().getPrincipal()).getUsername();
		sysLog.setUsername(username);

		sysLog.setTime(time);
		sysLog.setCreateDate(new Date());
		//保存系统日志
		sysLogService.insertSelective(sysLog);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
