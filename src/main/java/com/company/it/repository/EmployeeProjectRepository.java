package com.company.it.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.it.entity.Employee;
import com.company.it.entity.EmployeeProject;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long> {
	
	public List<EmployeeProject> findByEmployee(Employee employee);
	
	public List<EmployeeProject> findByEmployeeId(Long employeeId);
	
	@Modifying
	@Query("update employeeProject set isCurrent=false,endDate=:date where employeeId=:employeeId and isCurrent=true")
	public void markEndOfProjectsForEmployee(@Param("date") LocalDate localDate,@Param("employeeId") Long employeeId);
	

}
