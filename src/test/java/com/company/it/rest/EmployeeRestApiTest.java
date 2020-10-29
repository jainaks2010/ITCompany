package com.company.it.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.company.it.entity.Address;
import com.company.it.entity.Department;
import com.company.it.entity.Employee;
import com.company.it.repository.EmployeeRepository;
@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EmployeeRestApiTest extends AbstractTestNGSpringContextTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	private String url;
	
	private int pageSize = 5;
	
	private Map<String, String> parameters = new LinkedHashMap<String, String>();
	
	private Pageable defaultPageble = PageRequest.of(0, pageSize, Sort.by("firstName").and(Sort.by("lastName").and(Sort.by("dateOfBirth"))));
	
	@BeforeClass
	public void init(){
		url = "http://localhost:"+port+"/api/employees";
	}
	
	@BeforeMethod
	public void beforeEach() {
		parameters.clear();
	}
	
	@Test
	public void testEmployeeList() {
	   String url = "http://localhost:"+port+"/employee/list";
	   ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
	   String body = responseEntity.getBody();
	   assertThat(body).isNotEmpty();
	}
	
	@Test
	public void testEmployeeFindByFirstNameLike(){
		url = url+"/search/findByFirstNameLike?name=Carl";
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
		String body = responseEntity.getBody();
		assertThat(body).isNotEmpty();
	}
	
	@Test
	public void testEmployeefindByDateOfBirthGreaterThan() {
		String dateOfBirth = "1970-07-31";
		url = url+"search/findByDateOfBirthGreaterThan?localdate="+dateOfBirth;
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
		List<Employee> employees = employeeRepository.findByDateOfBirthGreaterThan(LocalDate.parse(dateOfBirth, DateTimeFormatter.ISO_DATE),defaultPageble);
		assertThat(employees.size()).isGreaterThan(0);
		String body = responseEntity.getBody();
		assertThat(body).isNotEmpty();		
	}
	
	
	
}
