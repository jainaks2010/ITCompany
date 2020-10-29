package com.company.it.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;

@Entity(name = "employeeProject")
@Table(name = "employee_project")
@Data
@Accessors(chain = true)
public class EmployeeProject {
	public static String ENTITY_NAME = "employeeProject";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "employee_id", insertable = false, updatable = false)
	private Long employeeId;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;
	
	@Column(name = "is_current")
	private Boolean isCurrent = Boolean.TRUE;
	
	@Column(name = "start_date")
	private LocalDate startDate = LocalDate.now();
	
	@Column(name = "end_date")
	private LocalDate endDate ;
	
	@Column(name = "employee_rating")
	private Integer employeeRating = 5;
	
}
