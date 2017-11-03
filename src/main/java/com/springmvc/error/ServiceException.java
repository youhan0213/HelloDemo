package com.springmvc.error;

/**
 * @desc:业务异常类
 * @author:weihz
 * @time:2014-12-22下午03:12:42
 */
public class ServiceException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private ServiceError error;

	public ServiceException(ServiceError error) {
		this.error = error;
	}
	public ServiceException(ServiceError error, Throwable cause) {
		super(cause);
		this.error = error;
	}
	
	public ServiceError getError() {
		return error;
	}
}
