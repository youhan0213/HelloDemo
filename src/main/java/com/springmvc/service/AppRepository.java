package com.springmvc.service;

import java.awt.print.Pageable;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.springmvc.model.AppDetailBean;

public interface AppRepository extends ElasticsearchRepository<AppDetailBean, Serializable>{
	
	List<AppDetailBean> findByName(String name,Pageable pageable);

}
