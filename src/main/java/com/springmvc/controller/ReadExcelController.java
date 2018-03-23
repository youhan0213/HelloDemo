package com.springmvc.controller;



import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.rpc.test.ReadExcel;
import com.rpc.test.bean.AppStarBean;
import com.springmvc.error.ServiceError;
import com.springmvc.result.APIResult;
import com.springmvc.result.MessageResult;
import com.springmvc.service.ExcelService;

@Controller
@RequestMapping("read")
public class ReadExcelController {

	
	@Resource
	private ExcelService excelService;
	@RequestMapping("test")
	public APIResult ping() {
		return new MessageResult("SUCCESS!");
	}
	
	@RequestMapping("excel")
	public APIResult Read(MultipartFile file,Integer productId) {
		if(file == null || productId == null) {
			return new MessageResult(ServiceError.PARAMETER_EMPTY);
		}
		CommonsMultipartFile cf = (CommonsMultipartFile)file;  
		DiskFileItem fi = (DiskFileItem) cf.getFileItem(); 
		File f = fi.getStoreLocation();
		List<AppStarBean> list = ReadExcel.readWorkbook(f);
		for (AppStarBean bean : list) {
			bean.setProductId(productId);
		}
		int b = excelService.batchSave(list);
		System.out.println(list.size());
		return new MessageResult(ServiceError.SUCCESS);
	}
}
