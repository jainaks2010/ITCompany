package com.company.it.utils;

import java.util.Random;

public class Utilities {
	
	private static Random random = new Random();
	
	public static int getRandomNaturalNumber(int upperBound) {
		upperBound = upperBound+1;
		int r = random.nextInt(upperBound);
		while(r == 0) {
			r = random.nextInt(upperBound);
		}
		return r;
	}
	

}
