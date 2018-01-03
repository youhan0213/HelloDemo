package com.springmvc.task;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import static org.quartz.JobBuilder.newJob;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

public class QuartzManager {
	
	    private static String JOB_GROUP_NAME = "MY_JOBGROUP_NAME";    
	    private static String TRIGGER_GROUP_NAME = "MY_TRIGGERGROUP_NAME";    
	    
	    /** 
	     * @param schedule 
	     * @Description: 添加一个定时任务，使用默认
	     * @param jobName 任务名 
	     */  
	    public static void addJob(Scheduler schedule, String jobName, Class cls, String time,Object scheduleJob) {    
	        try {    
	            JobDetail job = newJob(cls).withIdentity(jobName, JOB_GROUP_NAME).build();  
	            // 添加具体任务方法  
	            job.getJobDataMap().put("scheduleJob", scheduleJob);  
	            // 表达式调度构建器  
	            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time);  
	            // 按新的cronExpression表达式构建一个新的trigger  
	            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, JOB_GROUP_NAME).withSchedule(scheduleBuilder).build();  
	            //交给scheduler去调度  
	            schedule.scheduleJob(job, trigger);  
	              JobKey key = job.getKey();
	             System.out.println("group:" + key.getGroup());
	             System.out.println("name:" + key.getName());
	             System.out.println(key.toString());
	             // 启动    
	            if (!schedule.isShutdown()) {    
	            	schedule.start();    
	            }    
	        } catch (Exception e) {    
	            throw new RuntimeException(e);    
	        }    
	    }    
	    /** 
	     * @Description: 添加一个定时任务  
	     * @param jobName 任务名 
	     * @param jobGroupName 任务组名 
	     * @param triggerName 触发器名  
	     * @param triggerGroupName 触发器组名 
	     * @param jobClass 任务 
	     * @param time 时间设置，参考quartz说明文档 
	     */  
	    public static void addJob(Scheduler schedule, String jobName, String jobGroupName,    
	            String triggerName, String triggerGroupName, Class jobClass,    
	            String time) {    
	        try {    
	            JobDetail job = newJob(jobClass).withIdentity(jobName, jobGroupName).build();  
	            // 表达式调度构建器  
	            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time);  
	            // 按新的cronExpression表达式构建一个新的trigger  
	            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName).withSchedule(scheduleBuilder).build();  
	            schedule.scheduleJob(job, trigger);   
	            // 启动    
	            if (!schedule.isShutdown()) {    
	            	schedule.start();    
	            }    
	        } catch (Exception e) {    
	            throw new RuntimeException(e);    
	        }    
	    }    
	    
	    /** 
	     * @Description: 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)  
	     * @param jobName 
	     * @param time 
	     */  
	    public static void modifyJobTime(Scheduler schedule,String jobName, String time) {   
	        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, JOB_GROUP_NAME);  
	          
	        try {    
	            CronTrigger trigger =(CronTrigger) schedule.getTrigger(triggerKey);  
	            if (trigger == null) {    
	                return;    
	            }    
	            String oldTime = trigger.getCronExpression();    
	            if (!oldTime.equalsIgnoreCase(time)) {  
	                CronScheduleBuilder scheduleBuilder =CronScheduleBuilder.cronSchedule(time);  
	                //按新的cronExpression表达式重新构建trigger  
	                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();  
	                //按新的trigger重新设置job执行  
	                schedule.rescheduleJob(triggerKey, trigger);  
	            }    
	        } catch (Exception e) {    
	            throw new RuntimeException(e);    
	        }    
	    }    
	    
	    /** 
	     * @Description: 修改一个任务的触发时间  
	     * @param triggerName 
	     * @param triggerGroupName 
	     * @param time 
	     */  
	    public static void modifyJobTime(Scheduler schedule,String triggerName,    
	            String triggerGroupName, String time) {   
	        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);  
	        try {    
	            CronTrigger trigger = (CronTrigger) schedule.getTrigger(triggerKey);    
	            if (trigger == null) {    
	                return;    
	            }    
	            String oldTime = trigger.getCronExpression();    
	            if (!oldTime.equalsIgnoreCase(time)) {    
	                // trigger已存在，则更新相应的定时设置  
	                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time);  
	                // 按新的cronExpression表达式重新构建trigger  
	                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();  
	                // 按新的trigger重新设置job执行  
	                schedule.resumeTrigger(triggerKey);  
	            }    
	        } catch (Exception e) {    
	            throw new RuntimeException(e);    
	        }    
	    }    
	    
	    /** 
	     * @param schedule 
	     * @Description 移除一个任务(使用默认的任务组名，触发器名，触发器组名) 
	     * @param jobName 
	     */  
	    public static void removeJob(Scheduler schedule, String jobName) {   
	        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, JOB_GROUP_NAME);  
	        JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);  
	        try {    
	            Trigger trigger = (Trigger) schedule.getTrigger(triggerKey);    
	            if (trigger == null) {    
	                return;    
	            }  
	            schedule.pauseTrigger(triggerKey);;// 停止触发器    
	            schedule.unscheduleJob(triggerKey);// 移除触发器    
	            schedule.deleteJob(jobKey);// 删除任务    
	        } catch (Exception e) {    
	            throw new RuntimeException(e);    
	        }    
	    }    
	    
	    /**  
	     * @Description: 移除一个任务  
	     * @param jobName  
	     * @param jobGroupName  
	     * @param triggerName  
	     * @param triggerGroupName  
	     */    
	    public static void removeJob(Scheduler schedule, String jobName, String jobGroupName,    
	            String triggerName, String triggerGroupName) {   
	        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, triggerGroupName);  
	        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);  
	        try {    
	        	schedule.pauseTrigger(triggerKey);// 停止触发器    
	        	schedule.unscheduleJob(triggerKey);// 移除触发器    
	        	schedule.deleteJob(jobKey);// 删除任务  
	        } catch (Exception e) {    
	            throw new RuntimeException(e);    
	        }    
	    }   
	    /** 
	     * @Description:暂停一个任务 
	     * @param jobName 
	     * @param jobGroupName 
	     */  
	    public static void pauseJob(Scheduler schedule,String jobName, String jobGroupName) {  
	        JobKey jobKey =JobKey.jobKey(jobName, jobName);  
	        try {  
	        	schedule.pauseJob(jobKey);  
	        } catch (SchedulerException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	    /** 
	     * @Description:暂停一个任务(使用默认组名) 
	     * @param jobName 
	     * @param jobGroupName 
	     */  
	    public static void pauseJob(Scheduler schedule,String jobName) {  
	        JobKey jobKey =JobKey.jobKey(jobName, JOB_GROUP_NAME);  
	        try {  
	            schedule.pauseJob(jobKey);  
	        } catch (SchedulerException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	    /**  
	     * @Description:启动所有定时任务  
	     */  
	    public static void startJobs() {    
	        try {    
	        	SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
	            Scheduler sched = gSchedulerFactory.getScheduler();    
	            sched.start();    
	        } catch (Exception e) {    
	            throw new RuntimeException(e);    
	        }    
	    }    
	    
	    /**
	     * 启动一个任务
	     * @param schedule 
	     */
	    public static void startJob(Scheduler schedule, long jobId) {
	    	try {
				JobKey jobKey = JobKey.jobKey(jobId+"",JOB_GROUP_NAME);
				schedule.resumeJob(jobKey);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
	    }
	    
	    /** 
	     * @Description 关闭所有定时任务  
	     */  
	    public static void shutdownJobs() {    
	        try {    
	        	SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
	            Scheduler sched = gSchedulerFactory.getScheduler();    
	            if (!sched.isShutdown()) {    
	                sched.shutdown();    
	            }    
	        } catch (Exception e) {    
	            throw new RuntimeException(e);    
	        }    
	    } 
	    
	    /**
	     * job运行状态
	     * @param schedule 
	     */
	   public static ScheduleJob jobStatus(Scheduler schedule, String jobName) {
		   ScheduleJob job = new ScheduleJob();
		   try {
			   TriggerKey triggerKey = TriggerKey.triggerKey(jobName, JOB_GROUP_NAME);  
			   TriggerState state = schedule.getTriggerState(triggerKey);
			   String name = state.name();
			   job.setStatus(name);
			   CronTrigger trigger =(CronTrigger) schedule.getTrigger(triggerKey);  
	            if (trigger == null) {    
	                return null;    
	            }    
	            String cron = trigger.getCronExpression();  
	            job.setJobCron(cron);
	            job.setJobGroup(JOB_GROUP_NAME);
	            job.setJobName(jobName);
	            job.setCurTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return job;
	   }
}
