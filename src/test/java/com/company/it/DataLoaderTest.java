package com.company.it;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testng.annotations.Test;

import com.company.it.repository.StationaryRepository;

@SpringBootTest
@ActiveProfiles(profiles = {"load-data","test"})
public class DataLoaderTest {
	
	@Autowired
	private StationaryRepository statinary;
	
	
	@Test
	public void testDataLoad() {
		statinary.count();
	}
	
	
}
