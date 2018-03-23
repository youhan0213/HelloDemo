package com.springmvc.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rpc.test.bean.AppStarBean;

public interface ExcelMapper {

	int batchSave(@Param("list")List<AppStarBean> list);

}
