package com.company.it.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.company.it.entity.StationaryType;

@SpringBootTest
@ActiveProfiles("test")
public class StationaryRepositoryTest extends AbstractTestNGSpringContextTests{
	
	@Autowired
	private StationaryRepository stationaryRepository;
	
	@Test
	public void testfindBy() {
		Long count = stationaryRepository.findCountOfStationaryTypeByEmployeeId(1L, StationaryType.PENCIL);
		assertThat(count).isNotNull().isLessThanOrEqualTo(10L);
	}
}
