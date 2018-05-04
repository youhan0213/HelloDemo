package com.springmvc.controller;



import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
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
		if(file == null || file.isEmpty() || file.getSize() == 0 || productId == null) {
			return new MessageResult(ServiceError.PARAMETER_EMPTY);
		}
		CommonsMultipartFile cf = (CommonsMultipartFile)file;  
		DiskFileItem fi = (DiskFileItem) cf.getFileItem(); 
		File f = fi.getStoreLocation();
//		List<AppStarBean> list = ReadExcel.readWorkbook(f);
			Map<String, Object> map = ReadExcel.readWorkbook(f);
			if(map.get("error").equals(0)) {
				@SuppressWarnings("unchecked")
				List<AppStarBean> list = (List<AppStarBean>) map.get("data");
				if(CollectionUtils.isNotEmpty(list)) {
					for (AppStarBean bean : list) {
						bean.setProductId(productId);
					}
				}
				//先删除
				int d = excelService.batchDelete(productId);
				System.out.println("delete success : " + d);
				//再添加
				int b = excelService.batchSave(list);
				System.out.println("save success : " + b);
				System.out.println(list.size());
				return new MessageResult("SUCCESS!!");
			}else {
				return new MessageResult("error!");
			}
	}
}
