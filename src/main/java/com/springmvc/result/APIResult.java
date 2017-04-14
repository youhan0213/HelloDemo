package com.springmvc.result;

/**
 * root of all api result
 */
public abstract class APIResult {

	public static final APIResult SUCCESS = new APIResult() {
		public int getError() {
			return 0;
		};
	};

	protected int error;

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

}
