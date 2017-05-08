package com.springmvc.mybatis;

import java.util.List;

import com.springmvc.model.Student;
import com.springmvc.model.itemModel;

public interface StudentMapper {

	List<Student> getAll();

	List<itemModel> getAllItem();


}
