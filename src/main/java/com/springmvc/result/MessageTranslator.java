package com.springmvc.result;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



public class MessageTranslator {
	
	public static String translate(String code,String content){
		ServletRequestAttributes servlet = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servlet.getRequest(); 
        Object obj = request.getSession().getAttribute("USERNAME");
        Locale local = null;
//        if (null != obj) {
//        	Passport account = (Passport)obj;
//        	String area = CommonInitData.getUserArea(account.getUser().getToken());
//        	if (StringUtils.isNotBlank(area)) {
//        		String[] lang_country = area.split("_");
//        		local =new Locale(lang_country[0], lang_country[1]);
//			}else {
//				local =new Locale(Constant.Default.LANG, Constant.Default.COUNTRY);
//			}
//		}else {
//			local =new Locale(Constant.Default.LANG, Constant.Default.COUNTRY);
//		}
	
		ResourceBundle rb = ResourceBundle.getBundle("message", local);
		if (StringUtils.isNotBlank(content)) {
			String[] f = content.split(",");
			MessageFormat mf=new MessageFormat(rb.getString(code));
			return mf.format(f);
		}else {
			return rb.getString(code);
		}
		
	}
	
}
