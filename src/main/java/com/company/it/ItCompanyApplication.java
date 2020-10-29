package com.company.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.company.it.entity"})
@ComponentScan(basePackages = {"com.company.it.init","com.company.it.rest","com.company.it.service","com.company.it.config","com.company.it.repository"})
public class ItCompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItCompanyApplication.class, args);
	}

}
