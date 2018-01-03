package com.springmvc.task;

public interface ScheduleService {
	
	public boolean addJob(String cron,long msgId);
	
	public  boolean stopJob(long msgId);
	
	public boolean modifyJobTime(String cron,long jobId);
	
	public  ScheduleJob jobStatus(long msgId);
	 
	public void startJob(long jobId);
	
	public void removeJob(long jobId);

}
