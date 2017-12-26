package com.springmvc.task;

import org.junit.Test;

public class JobTest {
	public static void main(String[] args) {
		boolean b = LoadTask.timerTask(System.currentTimeMillis(), 10);
		System.out.println(b);
	}
	
//	@Test
//	public void stopJob()  {
//		boolean b = LoadTask.stopJob(10);
//		System.out.println(b);
//	}
	
//	@Test
//	public void modifyJobTime() {
//		boolean b = LoadTask.modifyJobTime(10);
//		
//		System.out.println(b);
//	}
	
	@Test
	public void jobStatus() {
		String status = LoadTask.jobStatus(10);
		System.out.println(status);
	}
}
