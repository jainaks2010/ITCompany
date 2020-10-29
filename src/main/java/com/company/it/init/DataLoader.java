package com.company.it.init;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.company.it.config.DataLoaderConfiguration;
import com.company.it.entity.Address;
import com.company.it.entity.Club;
import com.company.it.entity.Department;
import com.company.it.entity.Employee;
import com.company.it.entity.Project;
import com.company.it.entity.Stationary;
import com.company.it.entity.StationaryType;
import com.company.it.repository.EmployeeRepository;
import com.company.it.repository.StationaryRepository;
import com.company.it.service.DataLoaderService;
import com.company.it.utils.DateTimeUtils;
import com.company.it.utils.Utilities;
import com.fasterxml.jackson.datatype.jdk8.OptionalDoubleSerializer;

@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private Environment environment;
	
	@Autowired
	private DataLoaderService dataloaderService;
	
	@Autowired
	private DataLoaderConfiguration dataLoaderConfiguration;

	@Override
	public void run(String... args) throws Exception {
		String[] activeProfiles = environment.getActiveProfiles();
		List<String> activeProfileList = Arrays.asList(activeProfiles);
		if(activeProfileList.contains("loadData")) {
			String loadEntities = dataLoaderConfiguration.getLoadEntities();
			int loadNumberOfEmployees = dataLoaderConfiguration.getLoadNumberOfEmployees();
			String[] entities = loadEntities.split(",");
			List<String> entityList = Arrays.asList(entities);
			if(entityList.contains(Employee.ENTITY_NAME)){
				Optional<String> fileName = Optional.of("employees.csv");
				if(args.length > 0) {
					fileName = Optional.of(args[0]);
					File employeesCsvFile = resourceLoader.getResource("classpath:init/"+fileName.get()).getFile();
					dataloaderService.insertEmployeesByCSV(employeesCsvFile, loadNumberOfEmployees);
				}				
			}
			if(entityList.contains(Stationary.ENTITY_NAME)) {
				dataloaderService.insertStationary(100);
			}
			if(entityList.contains(Project.ENTITY_NAME)) {
				dataloaderService.insertProjectDetails();
			}
			
			if(entityList.contains(Club.ENTITY_NAME)) {
				dataloaderService.insertClubDetails();
			}
		}
	}

}
