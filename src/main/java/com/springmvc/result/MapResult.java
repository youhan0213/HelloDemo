package com.springmvc.result;

import java.util.HashMap;
import java.util.Map;

public class MapResult extends APIResult {
	Map<String,Object> datas = new HashMap<String,Object>();

	public MapResult(Map<String, Object> datas) {
		this.datas = datas;
	}

	public Map<String, Object> getDatas() {
		return datas;
	}

	public void setDatas(Map<String, Object> datas) {
		this.datas = datas;
	}
	
	public void addData(String key,Object obj){
		this.datas.put(key, obj);
	}
}
