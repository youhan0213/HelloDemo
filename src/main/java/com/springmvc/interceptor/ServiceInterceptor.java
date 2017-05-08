package com.springmvc.interceptor;

import java.util.List;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springmvc.model.Student;
import com.springmvc.result.APIResult;
import com.springmvc.result.ArrayResult;
import com.springmvc.util.Consts;
import com.whalin.MemCached.MemCachedClient;

@Aspect
public class ServiceInterceptor{

		public static final Logger logger = LoggerFactory.getLogger(ServiceInterceptor.class);
		
		@Resource
		private MemCachedClient memcachedCache;
	
		/***
		 * 定义切点
		 */
		@Pointcut("execution (* com.springmvc.controller.*.*(..))")
		//@Pointcut("execution (* com.springmvc.controller.demo.stu")
		public void controllerInterceptor(){
			
		}
		
		/***
		 * 环绕通知
		 */
		@Around("controllerInterceptor()&&args(sno)")
		public APIResult getStus(ProceedingJoinPoint joinPoint,String sno){
			List<Student> list = null;
			ArrayResult<Student> result = new ArrayResult<>();
			
			if(memcachedCache.keyExists(Consts.Memcached.COMMON_KEY +"_"+sno)){
				Object obj = memcachedCache.get(Consts.Memcached.COMMON_KEY +"_"+sno);
				list = (List<Student>) obj;
				logger.info("从缓存中获取学生信息。。");
			}else {
				try {
					result = (ArrayResult<Student>) joinPoint.proceed();
					 list = result.getDatas();
					if(list != null && list.size() > 0){
						memcachedCache.add(Consts.Memcached.COMMON_KEY + "_"+ sno , list);
						logger.info("存入memcached:" + sno);
					}
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
			return new ArrayResult<>(list);
		}
		
}
