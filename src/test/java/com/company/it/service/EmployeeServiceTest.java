package com.company.it.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.company.it.entity.Project;
import com.company.it.repository.ProjectRepository;

@SpringBootTest
@ActiveProfiles("test")
public class EmployeeServiceTest extends AbstractTestNGSpringContextTests{
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ProjectRepository projectRepository; 
	
	@Test
	public void testAddProject() {
		employeeService.addNewProjectForEmployee(7L, 1L);
	}

}
