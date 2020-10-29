package com.company.it.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties
@Component
@Data
public class DataLoaderConfiguration {
	
	private String loadEntities;
	
	private int loadNumberOfEmployees;
	
}
