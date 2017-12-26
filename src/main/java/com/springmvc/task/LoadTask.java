package com.springmvc.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadTask {
	
	private static final Logger logger = LoggerFactory.getLogger(LoadTask.class);
	  /** 
     * @param sendTime 发送时间  
     * @return 
     */  
    public static boolean timerTask(long sendTime,long msgId) {  
        //String cron = QuartzManager.getQuartzTime("0/1 * * * * ?");//获得quartz时间表达式，此方法自己写  
    	String cron = "0/5 * * * * ?";//获得quartz时间表达式，此方法自己写  
        ScheduleJob job = new ScheduleJob();  
        String jobName = msgId+"_job";  
        job.setJobId(msgId);  
        job.setJobName(jobName);  
        job.setCreTime(System.currentTimeMillis());  
        job.setJobCron(cron);  
        job.setJobTime(sendTime);  
        job.setJobGroup("MY_JOBGROUP_NAME");  
        job.setJobDesc("");  
        try {  
            //删除已有的定时任务  
            QuartzManager.removeJob(jobName);  
            //添加定时任务  
            QuartzManager.addJob(jobName, QuartzJobFactory.class, cron,job);  
            return true;  
        } catch (Exception e) {  
        	logger.info("加载定时器错误："+e);  
            return false;  
        }  
    }  
    
    /**
     * 停用job
     */
    public static boolean stopJob(long msgId) {
    	try {
			QuartzManager.pauseJob((msgId+"_job"));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    /**
     * 修改任务的执行实行
     */
    public static boolean modifyJobTime(long msgId) {
    	try {
			QuartzManager.modifyJobTime((msgId+"_job"), "0/1 * * * * ?");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    /**
     * 查询任务的执行状态
     */
    public static String jobStatus(long msgId) {
    	String jobStatus = QuartzManager.jobStatus((msgId+"_job"));
    	return jobStatus;
    }
}
