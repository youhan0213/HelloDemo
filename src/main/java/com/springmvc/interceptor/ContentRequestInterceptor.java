package com.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springmvc.util.HttpContext;


public class ContentRequestInterceptor extends HandlerInterceptorAdapter {
	
	protected final static Logger logger = LoggerFactory.getLogger(ContentRequestInterceptor.class);
	
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			Exception exception) throws Exception {
		logger.info("finally is here....");
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler){
		HttpContext.init(request, response);
		return true;
	}
}
