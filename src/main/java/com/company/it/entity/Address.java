package com.company.it.entity;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.experimental.Accessors;

@Embeddable
@Data
@Accessors(chain = true)
public class Address {
	
	private String placeName;
	
	private String county;
	
	private String city;
	
	private String state;
	
	private String zip;
	
	private String region;

}
