package com.springmvc.controller;

import java.util.concurrent.ConcurrentLinkedDeque;

import org.springframework.http.HttpRequest;

public class demo2 {
	public static ConcurrentLinkedDeque<HttpRequest> queue = new ConcurrentLinkedDeque<>();
}
