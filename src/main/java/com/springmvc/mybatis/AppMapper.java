package com.springmvc.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springmvc.model.AppDetailBean;


public interface AppMapper {
	

	public List<AppDetailBean> searchApp(@Param("appName")String appName, @Param("appType")String appType,@Param("start") Integer start,@Param("psize") Integer psize, @Param("appId")Integer appId,@Param("appVersionId")Long appVersionId, @Param("onUse")Integer onUse, @Param("packageName")String packageName,@Param("menuId")Integer menuId);

	
	public int countApp(@Param("appName")String appName, @Param("appType")String appType,@Param("appId")Integer appId,@Param("appVersionId")Long appVersionId, @Param("onUse")Integer onUse, @Param("packageName")String packageName, @Param("area")String area, @Param("menuId")Integer menuId);


	public String getIconUrl(@Param("appVersionId")Long appVersionId);


	public String getAppArea(@Param("appVersionId")Long appVersionId);
}
