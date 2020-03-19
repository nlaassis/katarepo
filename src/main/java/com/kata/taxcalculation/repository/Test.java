package com.kata.taxcalculation.repository;

import java.math.BigDecimal;

public class Test {
	public static void main(String[] args) {
		double oo = 24.98;
		double pp = 14.99;
		double ss = 2.55;
		//double t = oo + pp + ss;
		double t = new BigDecimal(oo).add(new BigDecimal(pp)).add(new BigDecimal(ss)).doubleValue();
		
		System.out.println(t);
	}
}
