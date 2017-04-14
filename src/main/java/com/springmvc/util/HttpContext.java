package com.springmvc.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HttpContext {

	private static ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();
	private static ThreadLocal<HttpSession> session = new ThreadLocal<HttpSession>();

	public static void init(HttpServletRequest req, HttpServletResponse resp) {
		request.set(req);
		response.set(resp);
		session.set(req.getSession());
	}

	public static HttpServletRequest getRequest() {
		return request.get();
	}

	public static HttpServletResponse getResponse() {
		return response.get();
	}

	public static HttpSession getSession() {
		return session.get();
	}

	public static void release() {
		request.remove();
		response.remove();
		session.remove();
	}

}
