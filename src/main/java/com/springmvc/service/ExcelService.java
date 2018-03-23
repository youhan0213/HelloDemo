package com.springmvc.service;

import java.util.List;

import com.rpc.test.bean.AppStarBean;

public interface ExcelService {

	int batchSave(List<AppStarBean> list);

}
