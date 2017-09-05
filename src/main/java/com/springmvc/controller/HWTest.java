package com.springmvc.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HWTest {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring_quartz.xml");
	}
}
