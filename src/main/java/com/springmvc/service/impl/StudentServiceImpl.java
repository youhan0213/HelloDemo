package com.springmvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.model.Student;
import com.springmvc.model.itemModel;
import com.springmvc.mybatis.StudentMapper;
import com.springmvc.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Resource
	private StudentMapper stuMapper;
	@Override
	public List<Student> getAll() {
		return stuMapper.getAll();
	}
	@Override
	public List<itemModel> getAllItem() {
		return stuMapper.getAllItem();
	}

}
