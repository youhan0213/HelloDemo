package com.springmvc.result;


public class ObjResult extends APIResult{
	private Object data = new Object();
	
	public ObjResult(){}
	
	public ObjResult(Object obj) {
		this.data = obj;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
