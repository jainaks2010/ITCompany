package com.company.it.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.it.entity.Stationary;
import com.company.it.entity.StationaryType;

public interface StationaryRepository extends JpaRepository<Stationary, Integer>{
	
	@Query("select count(*) from Stationary s  where s.employee.employeeId=:empId and s.stationaryType=:stationaryType group by s.stationaryType")
	public Long findCountOfStationaryTypeByEmployeeId(@Param("empId") Long employeeId,@Param("stationaryType") StationaryType stationaryType);

}
