package com.testemysql.teste.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testemysql.teste.entities.Department;
import com.testemysql.teste.repositories.DepartmentRepository;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {

	@Autowired
	private DepartmentRepository repository;

	@GetMapping
	public List<Department> findAll() {
		List<Department> result = repository.findAll();
		return result;
	}
	
	@PostMapping
	public Department insert(@RequestBody Department department) {
		Department result = repository.save(department);
		return result;
	}
}
