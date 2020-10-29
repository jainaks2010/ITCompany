package com.company.it.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.it.entity.Address;
import com.company.it.entity.Club;
import com.company.it.entity.Department;
import com.company.it.entity.Employee;
import com.company.it.entity.Project;
import com.company.it.entity.Stationary;
import com.company.it.entity.StationaryType;
import com.company.it.repository.ClubRepository;
import com.company.it.repository.EmployeeRepository;
import com.company.it.repository.ProjectRepository;
import com.company.it.repository.StationaryRepository;
import com.company.it.utils.DateTimeUtils;
import com.company.it.utils.Utilities;

@Service
@Transactional
public class DataLoaderService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private StationaryRepository stationaryRepository;

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ClubRepository clubRepository;
	
	public void insertEmployeesByCSV(File employeesCsvFile,int limit) throws FileNotFoundException, IOException {
		CSVParser parser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(new FileReader(employeesCsvFile));
		parser.getRecords().stream().limit(100).map(record -> toEmployee(record)).forEach(emp-> employeeRepository.save(emp));
	}
	
	public void insertStationary(int limit) {
		int numberOfEmployees = Utilities.getRandomNaturalNumber(limit);
		IntStream.range(0, numberOfEmployees).forEach(i -> {
			int randomEmployeeId = Utilities.getRandomNaturalNumber(limit);
			Optional<Employee> optional = employeeRepository.findById(Integer.valueOf(randomEmployeeId).longValue());
			Employee emp = optional.get();
			List<Stationary> stationaries = getStationaries(emp);
			stationaries.forEach(stationaryRepository::save);
		});
		
	}
	
	public void insertProjectDetails(){
		/*List<Employee> itDeptEmployees = employeeRepository.findEmployeesOfDepartment(3);
		Integer randomProjectId = Utilities.getRandomNaturalNumber(6);
		itDeptEmployees.stream().forEach(employee->{
			Optional<Project> optional = projectRepository.findById(randomProjectId.longValue());
			optional.ifPresent(project -> {
				employee.setProjects(projects)
			});
			employeeRepository.save(employee);
		});*/
		
	}
	
	public void insertClubDetails() {
		List<Employee> employees = employeeRepository.findEmployeesOfDepartments(List.of(3,5,6));
		System.out.println(employees.size());
		employees.forEach(employee ->{
			Integer randomClubId = Utilities.getRandomNaturalNumber(4);
			Club club = clubRepository.findById(randomClubId).orElseThrow();
			employee.getClubs().add(club);
		});
	}
	private Employee toEmployee(CSVRecord record) {
		LocalDate dob = DateTimeUtils.stringToDate(record.get("Date of Birth"), DateTimeUtils.AMERICAN_DATE_PATTERN);
		LocalDate doj = DateTimeUtils.stringToDate(record.get("Date of Joining"), DateTimeUtils.AMERICAN_DATE_PATTERN);
		Address address = new Address();
		int deptId = Utilities.getRandomNaturalNumber(5);
		Department department = new Department();
		department.setDepartmentId(deptId);
		address.setCity(record.get("City")).setCounty(record.get("County")).setState("State").setZip(record.get("Zip"))
				.setRegion(record.get("Region")).setPlaceName(record.get("Place Name"));
		return new Employee().setFirstName(record.get("First Name")).setLastName(record.get("Last Name"))
				.setDateOfBirth(dob).setDepartment(department).setDateOfJoining(doj).setEmail(record.get("E Mail"))
				.setAddress(address);
	}

	private List<Stationary> getStationaries(Employee employee) {
		int numberOfStationaries = Utilities.getRandomNaturalNumber(6);
		StationaryType[] stationaryTypes = StationaryType.values();
		List<Stationary> stationaries = new ArrayList<Stationary>();
		IntStream.range(0, numberOfStationaries).forEach(i -> {
			int randomOrdinal = Utilities.getRandomNaturalNumber(5);
			Stationary stationary = new Stationary();
			stationary.setStationaryType(stationaryTypes[randomOrdinal]);
			stationary.setEmployee(employee);
			stationaries.add(stationary);
		});
		return stationaries;
	}

	
	
}
