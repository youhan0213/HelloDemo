package com.springmvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.model.Student;
import com.springmvc.result.APIResult;
import com.springmvc.result.ArrayResult;
import com.springmvc.result.MapResult;
import com.springmvc.result.ObjResult;
import com.springmvc.service.StudentService;
import com.springmvc.util.Consts;
import com.whalin.MemCached.MemCachedClient;



@Controller
@RequestMapping("/aaaa")
public class demo {
	
	@Resource
	private StudentService stuService;
	
	@Resource
	private MemCachedClient mcClient;
	
	private final static Logger logger =  LoggerFactory.getLogger(demo.class);
	@RequestMapping("/test")
	public APIResult test(HttpServletRequest req){
		Map<String,Object> map =new HashMap<String, Object>();
		map.put("1", "a");
		map.put("2", "b");
		map.put("3", "c");
		map.put("4", "d");
		map.put("5", "e");
		map.put("6", "f");
		logger.info("uri:"+"------"+req.getRequestURI());
		logger.info("测试log输出--------");
		return new MapResult(map);
	}
	@RequestMapping("/test2")
	public APIResult test2(){
		List<Object> list= new ArrayList<>();
		list.add("haha1");
		list.add("haha2");
		list.add("haha3");
		list.add("haha4");
		list.add("haha5");
		return new ArrayResult<>(list);
	}
	@RequestMapping("/students")
	public APIResult stu(String sno){
		
		List<Student> list = null;
		Object obj = mcClient.get(Consts.Memcached.COMMON_KEY +"_"+sno);
		if(obj != null){
			 list = (List) obj;
		}else {
			list = stuService.getAll();
			mcClient.set(Consts.Memcached.COMMON_KEY + "_"+ sno , list);
		}
		
		return new ArrayResult<>(list);
	}
	@RequestMapping("/test1")
	public APIResult test1(String t){
		
		Object obj = mcClient.get("a");
		if(obj == null){
			mcClient.set("a", "student");
		}
		return new ObjResult(obj.toString());
	}
	
	
	
}
