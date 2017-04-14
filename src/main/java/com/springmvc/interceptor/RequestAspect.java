package com.springmvc.interceptor;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springmvc.result.APIResult;
import com.springmvc.util.HttpContext;
import com.springmvc.util.IpUtil;

@Aspect
public class RequestAspect {
	
	protected final static Logger logger = LoggerFactory.getLogger(RequestAspect.class);
	/**
	 * 定义切点要拦截的类
	 */
	@Pointcut("execution (* com.springmvc.controller.*.*(..))")
	private void controllerAspect() {
		
	} 

	/**
	 * 后置通知,获取返回值 
	 * @param jp
	 * @param result
	 */
	@AfterReturning(pointcut = "controllerAspect()", returning = "result")
	public void doAfterReturning(JoinPoint jp,Object result) {
		HttpServletRequest request = HttpContext.getRequest();
		String uri = request.getRequestURI();
		String method = jp.getTarget().getClass().getName()+"."+ jp.getSignature().getName();
		String ip = IpUtil.getIpAddr(request);
		
		Iterator<String> keys = request.getParameterMap().keySet().iterator();
		StringBuffer buf = new StringBuffer();
		buf.append("?");
		while(keys.hasNext()){
			String key = keys.next();
			buf.append(key+"="+request.getParameter(key));
			buf.append("&");
		}
		logger.info("调用方法:" + method);
		logger.info("请求ip:"+ ip);
		logger.info("请求参数:"+buf.toString());
		if(result instanceof APIResult){
			logger.info("返回结果:"+((APIResult)result).getError());
		}
	}
}
