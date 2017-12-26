package com.springmvc.task;

public class ScheduleJob {

	/*
	 * job.setJobId(msgId);  
        job.setJobName(jobName);  
        job.setCreTime(nowTime);  
        job.setJobCron(cron);  
        job.setJobTime(sendTime);  
        job.setJobGroup("MY_JOBGROUP_NAME");  
        job.setJobDesc(desc);  
	 */
	private long jobId;
	private String jobName;
	private String jobCron;
	private String jobGroup;
	private String jobDesc;
	private long jobTime;
	private long creTime;
	
	public long getCreTime() {
		return creTime;
	}
	public void setCreTime(long creTime) {
		this.creTime = creTime;
	}
	public long getJobTime() {
		return jobTime;
	}
	public void setJobTime(long jobTime) {
		this.jobTime = jobTime;
	}
	public long getJobId() {
		return jobId;
	}
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobCron() {
		return jobCron;
	}
	public void setJobCron(String jobCron) {
		this.jobCron = jobCron;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public ScheduleJob() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ScheduleJob(long jobId, String jobName, String jobCron, String jobGroup, String jobDesc, long jobTime,
			long creTime) {
		super();
		this.jobId = jobId;
		this.jobName = jobName;
		this.jobCron = jobCron;
		this.jobGroup = jobGroup;
		this.jobDesc = jobDesc;
		this.jobTime = jobTime;
		this.creTime = creTime;
	}
	@Override
	public String toString() {
		return "ScheduleJob [jobId=" + jobId + ", jobName=" + jobName + ", jobCron=" + jobCron + ", jobGroup="
				+ jobGroup + ", jobDesc=" + jobDesc + ", jobTime=" + jobTime + ", creTime=" + creTime + "]";
	}
}	
