package com.springmvc.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CronUtil {

	/*** 
     *  
     * @param date 
     * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss 
     * @return 
     */  
    public static String formatDateByPattern(Date date,String dateFormat){  
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);  
        String formatTimeStr = null;  
        if (date != null ) {  
            formatTimeStr = sdf.format(date);  
        }  
        return formatTimeStr;  
    }  
    
    /*** 
     * convert Date to cron ,eg.  "0 06 10 15 1 ? 2014" 
     * @param date  : 时间点 
     * @return 
     */  
    public static String getCron(java.util.Date  date){  
//        String dateFormat="ss mm HH dd MM ? yyyy";  
        String dateFormat="ss mm HH dd MM ? 0";  
        return formatDateByPattern(date, dateFormat);  
    }  

    public void test_getCron(){  
        String cron=CronUtil.getCron(new Date());  
        System.out.println(new Date());
        System.out.println(cron);  
    }
    
}
