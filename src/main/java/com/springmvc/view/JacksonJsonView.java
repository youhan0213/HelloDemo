package com.springmvc.view;

import java.util.Map;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.springmvc.result.APIResult;


public class JacksonJsonView extends MappingJackson2JsonView {
	
	@Override
	public String getContentType() {
		return "application/json";
	}

	protected Object filterModel(Map<String, Object> model) {
		Object result = null;
		for(Object obj : model.values()) {
			if(APIResult.class.isAssignableFrom(obj.getClass())) {
				result = obj;
				break;
			}
		}
		return result;
	}

}
