package com.springmvc.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);
	
	@Autowired
	private Scheduler schedule;
	  /** 
     * @param sendTime 发送时间  
     * @return 
     */  
    public boolean addJob(String cron,long msgId) {  
    	try {
			String schedulerName = schedule.getSchedulerName();
			System.out.println(schedulerName);
		} catch (SchedulerException e1) {
			e1.printStackTrace();
		}
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	String time = sdf.format(date);
        ScheduleJob job = new ScheduleJob();  
        String jobName = msgId+"";  
        job.setJobId(msgId);  
        job.setJobName(jobName);  
        job.setCurTime(time);  
        job.setJobCron(cron);  
        job.setJobGroup("MY_JOBGROUP_NAME");  
        job.setJobDesc("");  
        try {  
            //删除已有的定时任务  
            QuartzManager.removeJob(schedule,jobName);  
            //添加定时任务  
            QuartzManager.addJob(schedule,jobName, QuartzJobFactory.class, cron,job);  
            return true;  
        } catch (Exception e) {  
        	logger.info("加载定时器错误："+e);  
            return false;  
        }  
    }  
    
    /**
     * 停用job
     */
    public  boolean stopJob(long msgId) {
    	try {
			QuartzManager.pauseJob(schedule,(msgId+""));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    /**
     * 修改任务的执行实行
     */
    public boolean modifyJobTime(String cron,long jobId) {
    	try {
			QuartzManager.modifyJobTime(schedule,(jobId+""),cron);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    /**
     * 查询任务的执行状态
     */
    public  ScheduleJob jobStatus(long msgId) {
    	ScheduleJob job = QuartzManager.jobStatus(schedule,(msgId+""));
    	return job;
    }
    
    public void startJob(long jobId) {
    	QuartzManager.startJob(schedule,jobId);
    }
    public void removeJob(long jobId) {
    	QuartzManager.removeJob(schedule,jobId+"");
    }
}
