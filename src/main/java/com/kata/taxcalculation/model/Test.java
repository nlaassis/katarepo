package com.kata.taxcalculation.model;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Test {
	public static void main(String[] args) {
		double priceHt = 12.99;
		double tax = 10;
		double taxTemp = (priceHt * tax / 100);
		DecimalFormat df = new DecimalFormat("#,##");
		df.setRoundingMode(RoundingMode.HALF_UP);
		String taxTempRounded = df.format(taxTemp);
		double taxRounded = Double.parseDouble(taxTempRounded);
		double priceTtc = priceHt + taxRounded;
		System.out.println(priceTtc);
	}
}
