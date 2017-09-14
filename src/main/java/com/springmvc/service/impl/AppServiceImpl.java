package com.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.model.AppDetailBean;
import com.springmvc.mybatis.AppMapper;
import com.springmvc.service.AppService;
@Service
public class AppServiceImpl implements AppService {
	
	@Autowired
	private AppMapper appMapper;
	

	

	@Override
	public List<AppDetailBean> searchApp(String appName, String appType,Integer start,Integer psize,Integer appId,Long appVersionId,Integer onUse,String packageName,String area,Integer menuId) {
		List<AppDetailBean> appList = appMapper.searchApp(appName,appType,start,psize,appId,appVersionId,onUse,packageName,area,menuId);
		return appList;
	}

	@Override
	public int countApp(String appName, String appType,Integer appId,Long appVersionId,Integer onUse,String packageName,String area,Integer menuId) {
		return appMapper.countApp(appName,appType,appId,appVersionId,onUse,packageName,area,menuId);
	}


}
