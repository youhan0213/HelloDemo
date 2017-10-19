package com.springmvc.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.model.AppDetailBean;
import com.springmvc.mybatis.AppMapper;
import com.springmvc.service.AppService;
import com.springmvc.util.TimeTranslator;
@Service
public class AppServiceImpl implements AppService {
	
	@Autowired
	private AppMapper appMapper;
	
	

	

	@Override
	public List<AppDetailBean> searchApp(String appName, String appType,Integer start,Integer psize,Integer appId,Long appVersionId,Integer onUse,String packageName,String area,Integer menuId) {
		List<AppDetailBean> appList = appMapper.searchApp(appName,appType,start,psize,appId,appVersionId,onUse,packageName,menuId);
		
		List<AppDetailBean> searchApp = appMapper.searchApp(appName,appType,0,-1,appId,appVersionId,onUse,packageName,menuId);
		List<AppDetailBean> list = new ArrayList<AppDetailBean>();
		for (AppDetailBean appDetailBean : searchApp) {
			String appArea = appMapper.getAppArea(appDetailBean.getAppVersionId());
			if(StringUtils.isNotBlank(appArea)) {
				appDetailBean.setArea(appArea);
			}
			if(StringUtils.isNotBlank(area) && appArea.contains(area)) {
				list.add(appDetailBean);
			}else if(StringUtils.isBlank(area)){
				list.add(appDetailBean);
			}
		}
		int size = list.size();
		
		List<AppDetailBean> resultList = new ArrayList<AppDetailBean>();
		if(null != appList && appList.size() > 0) {
			for (AppDetailBean detail : appList) {
				if(detail.getAppType().equals("1") || detail.getAppType().equals("2")){
					detail.setPackageName(detail.getLinuxLink());
				}
				detail.setDatetime(TimeTranslator.conver2Date( detail.getUpdateTime()));
				String icon = appMapper.getIconUrl(detail.getAppVersionId());
				if(StringUtils.isNotBlank(icon)) {
					detail.setIconAddress(icon);
				}
				String appArea = appMapper.getAppArea(detail.getAppVersionId());
				if(StringUtils.isNotBlank(appArea)) {
					detail.setArea(appArea);
				}
				if(StringUtils.isNotBlank(area) && appArea.contains(area)) {
					resultList.add(detail);
				}else if(StringUtils.isBlank(area)){
					resultList.add(detail);
				}
				
			}
		}
		return resultList;
	}
	
	@Override
	public int countApp(String appName, String appType,Integer appId,Long appVersionId,Integer onUse,String packageName,String area,Integer menuId) {
		long start = System.currentTimeMillis();
		System.out.println("count start:" + start);
		List<AppDetailBean> searchApp = appMapper.searchApp(appName,appType,0,-1,appId,appVersionId,onUse,packageName,menuId);
		List<AppDetailBean> list = new ArrayList<AppDetailBean>();
		for (AppDetailBean appDetailBean : searchApp) {
			String appArea = appMapper.getAppArea(appDetailBean.getAppVersionId());
			if(StringUtils.isNotBlank(area) && appArea.contains(area)) {
				list.add(appDetailBean);
			}else if(StringUtils.isBlank(area)){
				list.add(appDetailBean);
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("count end" + end);
		System.out.println("count cost: " + (end-start) + "毫秒");
		return list.size();
		
	}


}
