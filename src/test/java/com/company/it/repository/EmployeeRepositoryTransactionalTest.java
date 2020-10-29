package com.company.it.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import com.company.it.entity.Employee;
import com.company.it.entity.Project;
import com.company.it.entity.Stationary;
import com.company.it.entity.StationaryType;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
public class EmployeeRepositoryTransactionalTest extends AbstractTransactionalTestNGSpringContextTests {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void testFindByIdLazyFetch() {
		Optional<Employee> optional = employeeRepository.findById(1L);
		optional.ifPresent(employee -> {
			List<Stationary> stationaries = employee.getStationaries();
			Optional<List<Stationary>> optionalStationaries = Optional.of(stationaries);
			optionalStationaries.ifPresent(stationaryList ->{
				System.out.println(stationaryList.size());
			});
		});
	}
	
	
	@Test
	public void testAddStationary() {
		Stationary stationary = new Stationary();
		stationary.setStationaryType(StationaryType.PEN);
		Optional<Employee> optionalEmployee = employeeRepository.findById(6L);
		optionalEmployee.ifPresent(e->{
			e.addStationary(stationary);
			employeeRepository.save(e);
		});;
	}
	
	
	
	


}
