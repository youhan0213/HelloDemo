package com.springmvc.task;

import java.util.HashMap;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJobFactory1 implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("任务运行成功");
		ScheduleJob1 scheduleJob = (ScheduleJob1) context.getMergedJobDataMap().get("scheduleJob");
		System.out.println("任务名称= [" + scheduleJob.getJobName() + "]" );
	}
	
//	private static Map<String,ScheduleJob> jobMap = new HashMap<String,ScheduleJob>();
//	static {
//		for(int i = 0 ;i < 5;i++) {
//			ScheduleJob job = new ScheduleJob();
//	        job.setJobId("10001" + i);
//	        job.setJobName("data_import" + i);
//	        job.setJobGroup("dataWork");
//	        job.setJobStatus("1");
//	        job.setCronExpression("0/5 * * * * ?");
//	        job.setDesc("数据导入任务");
//	        addJob(job);
//		}
//	}
//	/**
//	 * 添加任务
//	 * @param scheduleJob
//	 */
//	public static void addJob(ScheduleJob scheduleJob) {
//	    jobMap.put(scheduleJob.getJobGroup() + "_" + scheduleJob.getJobName(), scheduleJob);
//	}

}
