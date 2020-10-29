package com.company.it.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.it.entity.Employee;
import com.company.it.entity.EmployeeProject;
import com.company.it.entity.Project;
import com.company.it.repository.EmployeeProjectRepository;
import com.company.it.repository.EmployeeRepository;
import com.company.it.repository.ProjectRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeProjectRepository employeeProjectRepository;
	
	@Autowired
	private ProjectRepository projectRepository; 
	
	
	public List<Map<String, String>> getAllEmployees(){
	    Iterable<Employee> allEmployees = employeeRepository.findAll();
	    List<Map<String, String>> employeesMap = StreamSupport.stream(allEmployees.spliterator(), false).map(emp -> toMap(emp)).collect(Collectors.toList());
	    return employeesMap;
	}
	
	private Map<String,String> toMap(Employee employee){
		Map<String,String> map = new LinkedHashMap<String, String>();
		map.put("employeeId",employee.getEmployeeId().toString());
		map.put("firstName", employee.getFirstName());
		map.put("lastName", employee.getLastName());
		map.put("email", employee.getEmail());
		map.put("dob", employee.getDateOfBirth().toString());
		map.put("doj", employee.getDateOfJoining().toString());
		return map;
	}
	
	
	@Transactional
	public void addNewProjectForEmployee(Long employeeId,Long projectId) {
		Project project = projectRepository.getOne(projectId);
		Employee employee = employeeRepository.findById(employeeId).orElseThrow();
		employeeProjectRepository.markEndOfProjectsForEmployee(LocalDate.now(), employeeId);
		EmployeeProject employeeProject = new EmployeeProject();
		employeeProject.setEmployee(employee);
		employeeProject.setProject(project);
		employeeProjectRepository.save(employeeProject);
	}
}
