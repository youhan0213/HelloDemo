package com.springmvc.mybatis;

import java.util.List;

import com.springmvc.task.ScheduleJob1;

public interface DataWorkContext {
	
	List<ScheduleJob1> getAllJob();
}
