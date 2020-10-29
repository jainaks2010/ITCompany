package com.company.it.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.it.entity.Address;
import com.company.it.entity.Department;
import com.company.it.entity.Employee;
import com.company.it.entity.Project;
import com.company.it.entity.Stationary;

@RepositoryRestResource(path = "employees")
public interface EmployeeRepository extends  JpaRepository<Employee, Long>{
	
	public List<Employee> findByFirstNameLike(String name);
		
	public List<Employee> findByEmployeeIdGreaterThan(Long employeeId);
	
	public List<Employee> findByFirstNameAndLastName(String firstName,String lastName);
	
	public List<Employee> findByDateOfBirthGreaterThan(@RequestParam("localdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localdate,Pageable pageable);
	
	@Query("select new map(firstName as firstName, count(firstName) as count) from Employee group by firstName order by  count(firstName) desc")
	public List<Map> getNamesCount();
	
	@Query("select e.address from Employee e where e.employeeId=:empId")
	public Address findAddressByEmployeeId(@Param("empId") Long employeeId);

	@Query("select e.department from Employee e where e.employeeId=:empId")
	public Department findDepartmentByEmployeeId(@Param("empId") Long employeeId);
	
	@Query("select e from Employee e where e.department.departmentId = :departmentId")
	public List<Employee> findEmployeesOfDepartment(@Param("departmentId") Integer departmentId);
	
	@Query("select e from Employee e where e.department.departmentId in :departments")
	public List<Employee> findEmployeesOfDepartments(@Param("departments") Collection<Integer> departments);	
	
	@Query("select e.stationaries from Employee e where e.employeeId=:empId ")
	public List<Stationary> findStationariesByEmployeeId(@Param("empId") Long employeeId);
	
	@Query("select e.projectDetails from Employee e where e.employeeId=:empId ")
	public List<Project> findProjectsByEmployeeId(@Param("empId") Long employeeId);

	
}
