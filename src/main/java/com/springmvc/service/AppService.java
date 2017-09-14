package com.springmvc.service;

import java.util.List;

import com.springmvc.model.AppDetailBean;


public interface AppService {
	
	public List<AppDetailBean> searchApp(String appName, String appType,Integer start,Integer psize, Integer appId,Long appVersionId, Integer onUse, String packageName, String area, Integer menuId);

	public int countApp(String appName, String appType, Integer appId,Long appVersionId, Integer onUse, String packageName, String area, Integer menuId);

}
