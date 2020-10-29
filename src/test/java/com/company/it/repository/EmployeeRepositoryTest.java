package com.company.it.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.company.it.entity.Address;
import com.company.it.entity.Department;
import com.company.it.entity.Employee;
import com.company.it.entity.Project;
import com.company.it.entity.Stationary;
import com.company.it.utils.DateTimeUtils;
import com.company.it.utils.Utilities;

@SpringBootTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest extends AbstractTestNGSpringContextTests  {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private int pageSize = 5;

	
	@Test
	public void testFindById() {
		Optional<Employee> optionalEmployee = employeeRepository.findById(1L);
		assertThat(optionalEmployee.isPresent()).isTrue();
		assertThat(optionalEmployee.get().getFirstName()).isNotNull();
	}
	
	@Test
	public void testEmployeesPage() {
		int employeeCount = 0;
		Pageable pageableEmployees = PageRequest.of(0, pageSize, Sort.by("firstName").and(Sort.by("lastName").and(Sort.by("dateOfBirth").descending())));
		Page<Employee> pagedEmployees = employeeRepository.findAll(pageableEmployees);
		if(pagedEmployees.hasNext()) {
			do{
				int numberOfElements = pagedEmployees.getNumberOfElements();
				employeeCount = employeeCount + numberOfElements;
				pagedEmployees.stream().forEach(emp -> System.out.println(emp.getFirstName()+" "+emp.getLastName()+" "+emp.getDateOfBirth()));
				pageableEmployees = pagedEmployees.nextPageable();
				pagedEmployees = employeeRepository.findAll(pageableEmployees);
			}while(pagedEmployees.hasNext()); 
		}
		
		assertThat(employeeCount).isGreaterThan(50);
	}
	
	@Test
	public void testCount() {
		List<Map> namesCount = employeeRepository.getNamesCount();
	    namesCount.stream().forEach(map ->{
	    	System.out.println(map.get("firstName")+" - "+map.get("count"));
	    });
		
		assertThat(namesCount.size()).isGreaterThan(0);
	}
	
	@Test
	public void testEmployeeAddressByEmployeeId() {
		Address findAddressByEmployeeId = employeeRepository.findAddressByEmployeeId(10L);
		assertThat(findAddressByEmployeeId).isNotNull();
	}
	
	@Test
	public void testEmployeeDepartmentByEmployeeId() {
		Department department = employeeRepository.findDepartmentByEmployeeId(10L);
		assertThat(department).isNotNull();
		assertThat(department.getName()).isEqualTo("HR");
	}
	
	@Test
	public void testFindEmployeesOfDepartment() {
		List<Employee> employees = employeeRepository.findEmployeesOfDepartment(3);
		assertThat(employees.size()).isGreaterThan(0);
		System.out.println(employees.size());
		employees.stream().forEach(emp->{
			System.out.println(emp.getFirstName());
			System.out.println(emp.getAddress());
		});
	}
	
	@Test
	public void testAddEmployee() {
		Employee employee = getEmployee();
		employeeRepository.save(employee);
	}
	
	@Test
	public void testReadEmployee() {
		List<Employee> employees = employeeRepository.findByFirstNameAndLastName("Test_First_Name1", "Test_Last_Name1");
		assertThat(employees.size()).isGreaterThan(0);
	}
	
	@Test
	public void testUpdateEmployee() {
		List<Employee> employees = employeeRepository.findByFirstNameAndLastName("Test_First_Name1", "Test_Last_Name1");
		assertThat(employees.size()).isGreaterThan(0);
		employees.stream().forEach(employee -> {
			employee.getAddress().setCity("TEST_CITY_UPDATED_1");
			employeeRepository.save(employee);
		});
	}
	
	@Test(dependsOnMethods = {"testAddEmployee"})
	public void testDeleteEmployee() {
		List<Employee> employees = employeeRepository.findByFirstNameAndLastName("Test_First_Name1", "Test_Last_Name1");
		employees.stream().forEach(employeeRepository::delete);
	}
	
	private Employee getEmployee() {
		LocalDate dob = DateTimeUtils.stringToDate("5/8/1990", DateTimeUtils.AMERICAN_DATE_PATTERN);
		LocalDate doj = DateTimeUtils.stringToDate("7/6/2013", DateTimeUtils.AMERICAN_DATE_PATTERN);
		Address address = new Address();
		int deptId = Utilities.getRandomNaturalNumber(5);
		Department department = new Department();
		department.setDepartmentId(deptId);
		address.setCity("Test_city1").setCounty("Test_County_1").setState("Test_State_1").setZip("123456")
				.setRegion("Test_Region").setPlaceName("Test_Place_Name");
		return new Employee().setFirstName("Test_First_Name1").setLastName("Test_Last_Name1")
				.setDateOfBirth(dob).setDepartment(department).setDateOfJoining(doj).setEmail("test@email.com")
				.setAddress(address);
	}
	
	
	@Test
	public void testEmployeeStationaries() {
		List<Stationary> stationaries = employeeRepository.findStationariesByEmployeeId(1L);
		assertThat(stationaries).isNotNull();
		assertThat(stationaries.size()).isGreaterThan(1);
	}
	
	@Test
	public void testGetProjectByEmployeeId() {
		List<Project> projects = employeeRepository.findProjectsByEmployeeId(24L);
		projects.forEach(project -> {
			assertThat(project).isNotNull();
			assertThat(project.getProjectName()).isNotNull();
		});
	}	

	@Test
	public void testChangeProjectEmployeeId() {
		List<Project> projects = employeeRepository.findProjectsByEmployeeId(24L);
		projects.forEach(project -> {
			assertThat(project).isNotNull();
			assertThat(project.getProjectName()).isNotNull();
			
		});
	}	
	
	
	
}
