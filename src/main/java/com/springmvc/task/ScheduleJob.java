package com.springmvc.task;

public class ScheduleJob {

	private long jobId;
	private String jobName;
	private String jobCron;
	private String jobGroup;
	private String jobDesc;
	private String curTime;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCurTime() {
		return curTime;
	}
	public void setCurTime(String curTime) {
		this.curTime = curTime;
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
	public ScheduleJob(long jobId, String jobName, String jobCron, String jobGroup, String jobDesc, String curTime,
			String status) {
		super();
		this.jobId = jobId;
		this.jobName = jobName;
		this.jobCron = jobCron;
		this.jobGroup = jobGroup;
		this.jobDesc = jobDesc;
		this.curTime = curTime;
		this.status = status;
	}
	@Override
	public String toString() {
		return "ScheduleJob [jobId=" + jobId + ", jobName=" + jobName + ", jobCron=" + jobCron + ", jobGroup="
				+ jobGroup + ", jobDesc=" + jobDesc + ", curTime=" + curTime + ", status=" + status + "]";
	}
}	
