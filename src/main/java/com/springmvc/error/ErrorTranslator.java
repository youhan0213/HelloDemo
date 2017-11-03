package com.springmvc.error;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 错误信息翻译
 * @author deadblue
 */
public class ErrorTranslator {

	private static Map<Integer, String> messageMap = null;	
	static {
		messageMap = new HashMap<Integer, String>();
		try {
			Properties props = new Properties();
			ClassLoader cl = ErrorTranslator.class.getClassLoader();
			props.load(cl.getResourceAsStream("error.properties"));
			for(String key : props.stringPropertyNames()) {
				if(!key.matches("^error\\.\\d+$")) continue;
				int errorCode = Integer.parseInt(key.substring(6));
				messageMap.put(errorCode, props.getProperty(key));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getMessage(ServiceError error) {
		String message = messageMap.get(error.getCode());
		if(message == null)
			message = "未知错误！";
		return message;
	}

}
