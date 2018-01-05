package com.springmvc.task;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * 定时任务运行（反射出来的类） 
 * @Description 
 */  
@DisallowConcurrentExecution
public class QuartzJobFactory implements Job{

	private static final Logger logger = LoggerFactory.getLogger(QuartzJobFactory.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("任务运行开始-------- start --------");   
	        try {  
	            //ScheduleJob任务运行时具体参数，可自定义  
//	            ScheduleJob scheduleJob =(ScheduleJob) context.getMergedJobDataMap().get(  
//	                    "scheduleJob"); 
//	            System.out.println(scheduleJob);
//	        	logger.info("---------------------开始扫描数据--------------------");
//	        	logger.info("----------------------清理完毕----------------------");

	        }catch (Exception e) {  
	        	logger.info("捕获异常==="+e);  
	        }  
	        logger.info("任务运行结束-------- end --------");   
	    }  
		
	}

