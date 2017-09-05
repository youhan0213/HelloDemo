package com.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldJob implements Job{
	
	private static final Logger logger = LoggerFactory.getLogger(HelloWorldJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		//写定时执行的代码
		logger.info("This is a first spring combine quartz!");
		logger.info("Welcome to spring_quartz World !" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		logger.info("let's begin!");
	}

}
