package com.springmvc.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.error.ServiceError;
import com.springmvc.model.LangInfo;
import com.springmvc.mybatis.AllAndRecommendDataMapper;
import com.springmvc.result.APIResult;
import com.springmvc.result.MessageResult;
import com.springmvc.util.HttpClientUtil;

@Controller
@RequestMapping("data")
public class AllAndRecommendInitData {
	
	@Resource
	
	private AllAndRecommendDataMapper mapper;
	
	//String url = "http://storeapi.app-vtion.com/storeApi/init/addLang.json?type=2&lang=en&name=Recommend";
	/**
	 * 1:all
	 * 2:recommend
	 * @return
	 */
	@RequestMapping("init")
	public APIResult init() {
		
		String url = "http://storeapi.app-vtion.com/storeApi/init/addLang.json?";
 		List<LangInfo> list = mapper.query();
		if(list != null && list.size() > 0) {
			for (LangInfo langInfo : list) {
				String langCode = langInfo.getLangCode();
				String all = langInfo.getAll();
				String recommend = langInfo.getRecommend();
				StringBuffer buffer1 = new StringBuffer(url);
				buffer1.append("type=1" + "&lang=" + langCode + "&name=" + all);
				StringBuffer buffer2 = new StringBuffer(url);
				buffer2.append("type=2" + "&lang=" + langCode + "&name=" + recommend);
				String b1 = buffer1.toString().replaceAll(" ", "%20");
				String b2 = buffer2.toString().replaceAll(" ", "%20");
				try {
					HttpClientUtil.sendHttpForGet(b1);
					HttpClientUtil.sendHttpForGet(b2);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return new MessageResult();
	}
}
