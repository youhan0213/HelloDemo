package com.springmvc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.model.AppDetailBean;
import com.springmvc.result.APIResult;
import com.springmvc.result.PageResult;
import com.springmvc.service.AppRepository;
import com.springmvc.service.AppService;
import com.springmvc.util.TimeTranslator;

@Controller
public class AppController {
	
	private static final Logger logger = LoggerFactory.getLogger(AppController.class);
	
	@Autowired
	private AppService appService;
	
/*	@Resource
	private MongoTemplate mongoTemplate;*/
	
//	@Resource
//	private AppRepository appRepository;
	
//	@Resource
//	private ElasticsearchTemplate template;
	
	
	
	@Test
	@RequestMapping("searchApp")
	public APIResult searchApp(String appName,String appType,Integer start,Integer psize,Integer appId,Long appVersionId,Integer onUse,String packageName,String area,Integer menuId){
		int count = appService.countApp(appName,appType,appId,appVersionId,onUse,packageName,area,menuId);
		List<AppDetailBean> appDetail = null;
		
		long s = System.currentTimeMillis();
		if(count > 0){
			 appDetail = appService.searchApp(appName,appType,start,psize,appId,appVersionId,onUse,packageName,area,menuId);
		}
		//appRepository.save(appDetail);
		long end = System.currentTimeMillis();
		logger.info("耗时：[" + (end - s) +"毫秒]");
		return new PageResult<>(appDetail, count, psize);
	}
	
	@RequestMapping("test")
	public APIResult test() {
		return null;
	}
}
