package com.springmvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpc.test.bean.AppStarBean;
import com.springmvc.mybatis.ExcelMapper;
import com.springmvc.service.ExcelService;

@Service
public class ExcelServiceImpl implements ExcelService {

	@Resource
	private ExcelMapper excelMapper;
	@Override
	public int batchSave(List<AppStarBean> list) {
		// TODO Auto-generated method stub
		return excelMapper.batchSave(list);
	}

}
