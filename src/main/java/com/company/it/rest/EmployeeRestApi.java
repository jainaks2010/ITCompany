package com.company.it.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.it.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeRestApi {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	@RequestMapping("/list")
	public List<Map<String, String>> getAllEmployees(){
		return employeeService.getAllEmployees();
	}

}
