package com.springmvc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
	public static void main(String[] args) {
		Date date = new Date();
		long time = date.getTime();
		System.out.println(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String string = sdf.format(date);
		System.out.println(string);
	}
}
