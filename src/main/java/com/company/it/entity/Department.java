package com.company.it.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
public class Department {
	
	public static String ENTITY_NAME = "department";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer departmentId;
	
	private String name;
	
	@OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
	List<Employee> employees;
	
}
