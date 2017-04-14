package com.springmvc.log;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ErrorLog {
	
	protected final static Logger logger = LoggerFactory.getLogger(ErrorLog.class);
	
	public static void exLog(HttpServletRequest req,Exception e){
		String url = req.getRequestURL().toString();
		Iterator<String> keys  = req.getParameterMap().keySet().iterator();
		StringBuffer buf = new StringBuffer();
		while(keys.hasNext()){
			String key = keys.next();
			buf.append("&"+key+"="+req.getParameter(key));
		}
		String ip = req.getRequestURI();
		String os = req.getParameter("os");
		String cver = req.getParameter("cver");
		String deviceMode = req.getParameter("deviceMode");
		String imei = req.getParameter("imei");
		logger.info("==>>request_url:"+url);
		logger.info("==>>request_param:"+buf.toString().replaceFirst("&", ""));
		logger.info("==>>request_visitor:"+ip+" ,brand:"+deviceMode+",os:"+os+cver+",imei:"+imei);
		logger.info("==>>error:",e);
	}

}
