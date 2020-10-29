package com.company.it.entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
public class Employee {
	
	public static String ENTITY_NAME = "employee";
	
	public Employee() {}
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private LocalDate dateOfBirth;
	
	@Column(nullable = false)
	private LocalDate dateOfJoining;
	
	@Column(nullable = false)
	private String email;
	
	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<EmployeeProject> projectDetails;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(
			name = "employee_clubs",
			joinColumns = {@JoinColumn(name ="employee_id")},
			inverseJoinColumns = {@JoinColumn(name = "club_id")}
			)
	
	private List<Club> clubs;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	private Department department;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "employee",orphanRemoval = true,fetch = FetchType.LAZY)
	private List<Stationary> stationaries = new ArrayList<Stationary>();
	
	@Embedded
	private Address address;
	
	@Transient
	private int age;
		
	public int getAge() {
		LocalDate now = LocalDate.now();
		Period period = Period.between(dateOfBirth,now);
		return period.getYears();
	}
	
	public void addStationary(Stationary stationary) {
		stationaries.add(stationary);
		stationary.setEmployee(this);
	}

}
