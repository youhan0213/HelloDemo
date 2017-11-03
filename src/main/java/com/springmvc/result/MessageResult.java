package com.springmvc.result;

import com.springmvc.error.ServiceError;

public class MessageResult extends APIResult {

	private String message;

	
	public MessageResult() {}
	public MessageResult(String message) {
		this.message = message;
	}
	public MessageResult(int error, String message) {
		this.error = error;
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MessageResult(ServiceError error) {
		this.error = error.getCode();
	}
}
