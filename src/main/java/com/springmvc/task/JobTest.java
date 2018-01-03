package com.springmvc.task;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.result.APIResult;
import com.springmvc.result.MapResult;
import com.springmvc.result.ObjResult;


@Controller
public class JobTest {
	
	@Autowired
	private ScheduleService scheduleService;
	public static void main(String[] args) {
		
//		boolean b = scheduleService.timerTask(System.currentTimeMillis(), 10);
//		System.out.println(b);
	}
	
	
	
//	@Test
//	public void stopJob()  {
//		boolean b = scheduleService.stopJob(10);
//		System.out.println(b);
//	}
	
//	@Test
//	public void modifyJobTime() {
//		boolean b = scheduleService.modifyJobTime(10);
//		
//		System.out.println(b);
//	}
	
//	@Test
//	public void jobStatus() {
//		String status = scheduleService.jobStatus(10);
//		System.out.println(status);
//	}
	
	@RequestMapping("addJob") 
	public APIResult testJob(String cron,long jobId) {
		boolean b = scheduleService.addJob(cron, jobId);
		return new ObjResult(b);
	}
	
	@RequestMapping("pauseJob")
	public APIResult pauseJob(long jobId) {
		boolean b = scheduleService.stopJob(jobId);
		return new ObjResult(b);
	}
	
	@RequestMapping("modifyJob")
	public APIResult modifyJob(String cron,long jobId) {
		boolean b = scheduleService.modifyJobTime(cron,jobId);
		return new ObjResult(b);
	}
	
	@RequestMapping("startJob")
	public void startJob(long jobId) {
		scheduleService.startJob(jobId);
	}
	@RequestMapping("removeJob")
	public void removeJob(long jobId) {
		scheduleService.removeJob(jobId);
	}
	
	@RequestMapping("statusJob")
	public APIResult statucJob(long jobId) {
		ScheduleJob jobStatus = scheduleService.jobStatus(jobId);
		return new ObjResult(jobStatus);
	}
}
