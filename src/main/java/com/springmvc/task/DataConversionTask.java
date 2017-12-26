package com.springmvc.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataConversionTask {

	private static final Logger logger = LoggerFactory.getLogger(DataConversionTask.class);
	
	public void run () {
		if(logger.isInfoEnabled()) {
			logger.info("数据转换任务线程开始");
		}
	}
}
