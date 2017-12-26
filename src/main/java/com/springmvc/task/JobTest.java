package com.springmvc.task;

public class JobTest {
	public static void main(String[] args) {
		boolean b = LoadTask.timerTask(System.currentTimeMillis(), 10);
		System.out.println(b);
	}
}
