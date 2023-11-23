package com.testemysql.teste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testemysql.teste.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
