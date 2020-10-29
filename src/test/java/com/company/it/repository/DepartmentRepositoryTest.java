package com.company.it.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import com.company.it.entity.Department;
import com.company.it.entity.Employee;


@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class DepartmentRepositoryTest extends AbstractTransactionalTestNGSpringContextTests{
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Test
	public void testDepartment() {
		Optional<Department>  optionDepartment = departmentRepository.findById(3);
		Department department = optionDepartment.get();
		List<Employee> employees = department.getEmployees();
		System.out.println(employees.size());
		employees.stream().forEach(emp -> {
			System.out.println(emp.getFirstName()+" "+emp.getLastName());
		});
	}

}
