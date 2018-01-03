package com.springmvc.task;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时任务初始化
 * @author youh
 * @version 2018年1月3日 下午5:50:55
 */
@Component
public class ScheduleJobInit {
	
	@Autowired
	private ScheduleService scheduleService;
	
	private static Logger logger = LoggerFactory.getLogger(ScheduleJobInit.class);
	
	/**
	 * 每隔七天执行一次，每次执行时间为GMT 2:00
	 */
//	private static final String InitCron = "0 0 2 1/7 * ? ";
	private static final String InitCron = "0/10 * * * * ?";
	
	/**
	 * 项目启动时初始化
	 */
	@PostConstruct
	public void init() {
		if(logger.isInfoEnabled()) {
			logger.info("init job");
		}
		scheduleService.addJob(InitCron, 10);
		if(logger.isInfoEnabled()) {
			logger.info("end job");
		}
	}
	
}
