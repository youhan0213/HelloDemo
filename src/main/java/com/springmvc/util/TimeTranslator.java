package com.springmvc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTranslator {

	public static Long convert2long(String date, String format) {
		  try {
		     SimpleDateFormat sf = new SimpleDateFormat(format);
		     return sf.parse(date).getTime();
		   
		  } catch(Exception e) {
		   return null;
		  }
		 
	}
	
	public static String conver2Date(long date){
		Date data = new Date(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatDate = sdf.format(data);
		return formatDate;
	}
	
}
