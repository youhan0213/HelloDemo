package com.springmvc.result;

import java.util.ArrayList;
import java.util.List;


public class ArrayResult<T> extends APIResult {
	private List<T> datas;
	
	public ArrayResult(){}
	
	public ArrayResult(List<T> list) {
		this.datas = list;
	}
	
	public List<T> getDatas() {
		if(datas == null) 
			datas = new ArrayList<T>();
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	public void addData(T data) {
		if(datas == null) 
			datas = new ArrayList<T>();
		datas.add(data);
	}

}
