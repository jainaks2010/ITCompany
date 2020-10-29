package com.company.it.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "Project")
@Data
@Accessors(chain = true)
public class Project {
	
	public static String ENTITY_NAME = "project";
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectId;

	@Column(name = "name")
	private String projectName;
	
	@OneToMany(mappedBy = "employee" , fetch = FetchType.LAZY)
	private List<EmployeeProject> employees;
	
}
