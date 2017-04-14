package com.springmvc.result;

import java.util.ArrayList;
import java.util.List;


public class PageResult<T> extends APIResult {
	private List<T> datas;
	
	private int total;
	
	private int psize;
	
	
	public PageResult(List<T> list,int total) {
		this.datas = list;
		this.total = total;
	}
	
	public PageResult(List<T> list, int total, Integer psize) {
		this.datas = list;
		this.total = total;
		this.psize = psize;
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		if (total < 0 ) {
			total = 0;
		}
		this.total = total;
	}

	public int getPsize() {
		return psize;
	}

	public void setPsize(int psize) {
		this.psize = psize;
	}

}
