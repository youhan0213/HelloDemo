package com.springmvc.error;

/**
 * 参数异常类
 */
public class ParameterException extends RuntimeException{
	private static final long serialVersionUID = 2L;
	private ServiceError error;
	private String[] args;  

	public ParameterException(ServiceError error,String... args) {
		this.error = error;
		this.args = args;
	}
	public ParameterException(ServiceError error, Throwable cause) {
		super(cause);
		this.error = error;
	}
	
	public ServiceError getError() {
		return error;
	}
	public String[] getArgs() {
		return args;
	}
}
