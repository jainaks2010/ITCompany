package com.company.it.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.it.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	
	public Department findByDepartmentId(Integer departmentId);
	
	

}
