package com.springmvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.model.AppDetailBean;
import com.springmvc.result.APIResult;
import com.springmvc.result.PageResult;
import com.springmvc.service.AppService;
import com.springmvc.util.TimeTranslator;

@Controller
public class AppController {
	
	private static final Logger logger = LoggerFactory.getLogger(AppController.class);
	
	@Autowired
	private AppService appService;
	
	@RequestMapping("searchApp")
	public APIResult searchApp(String appName,String appType,Integer start,Integer psize,Integer appId,Long appVersionId,Integer onUse,String packageName,String area,Integer menuId){
		long s = System.currentTimeMillis();
		int count = appService.countApp(appName,appType,appId,appVersionId,onUse,packageName,area,menuId);
		List<AppDetailBean> appDetail = null;
		if(start == null ){
			start = 0;
		}
		if(psize == null){
			psize = 10;
		}
		if(count > 0){
			 appDetail = appService.searchApp(appName,appType,start,psize,appId,appVersionId,onUse,packageName,area,menuId);
			 if(appDetail != null && appDetail.size() > 0){
				 for (AppDetailBean appDetailBean : appDetail) {
					 if(appDetailBean.getAppType().equals("1") || appDetailBean.getAppType().equals("2")){
						 appDetailBean.setPackageName(appDetailBean.getLinuxLink());
					 }
					 appDetailBean.setDatetime(TimeTranslator.conver2Date( appDetailBean.getUpdateTime()));
					 
				 }
			 }
		}
		long end = System.currentTimeMillis();
		logger.info("耗时：[" + (end - s) +"毫秒]");
		return new PageResult<>(appDetail, count, psize);
	}
}
