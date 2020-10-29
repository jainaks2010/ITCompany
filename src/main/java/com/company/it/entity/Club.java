package com.company.it.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Club {
	
	public static String ENTITY_NAME ="club";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clubId;
	
	@Column(name = "club_name")
	private String clubName;
	
	@ManyToMany(mappedBy = "clubs")
	private List<Employee> employees;
	
}
