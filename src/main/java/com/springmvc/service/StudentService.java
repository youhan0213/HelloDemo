package com.springmvc.service;

import java.util.List;

import com.springmvc.model.Student;
import com.springmvc.model.itemModel;

public interface StudentService {

	List<Student> getAll();

	List<itemModel> getAllItem();

}
