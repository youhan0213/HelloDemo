package com.springmvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.result.APIResult;
import com.springmvc.result.ArrayResult;
import com.springmvc.result.MapResult;


@Controller
@RequestMapping("/aaaa")
public class demo {
	
	@RequestMapping("/test")
	public APIResult test(Model model){
		Map<String,Object> map =new HashMap<String, Object>();
		map.put("1", "a");
		map.put("2", "b");
		map.put("3", "c");
		map.put("4", "d");
		map.put("5", "e");
		map.put("6", "f");
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
}
