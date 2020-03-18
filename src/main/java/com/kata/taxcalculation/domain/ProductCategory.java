package com.kata.taxcalculation.domain;

public enum ProductCategory {
	FOOD("Nourriture", true, 0), 
	MEDICINE("Médicament", true, 0), 
	BOOK("Livre", false, 10),
	OTHER("Autre", false, 20);
	
	private String name = "";
	private boolean isEssential = false;
	private double tax = 0;
	
	ProductCategory(String name, boolean isEssential, double tax) {
		this.name = name;
		this.isEssential = isEssential;
		this.tax = tax;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
