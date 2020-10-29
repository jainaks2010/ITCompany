package com.company.it.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
	
	public static DateTimeFormatter AMERICAN_DATE_PATTERN = DateTimeFormatter.ofPattern("M/d/yyyy");
	
	public static LocalDate stringToDate(String date, DateTimeFormatter formatter) {
		return LocalDate.parse(date,formatter);
	}

}
