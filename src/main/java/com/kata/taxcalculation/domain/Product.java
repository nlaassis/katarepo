package com.kata.taxcalculation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column
    String name;

    @Column
	double price;
    
    @Enumerated(EnumType.STRING)
	ProductCategory productCategory;
    
    @Column
    boolean isEssential;
    
    @Column
    boolean isImported;
    
    @Column
    double tax;
    
    @Builder
	public Product(String name, double price, ProductCategory category, 
			boolean isEssential, boolean isImported, double tax) {
		this.name = name;
		this.price = price;
		this.productCategory = category;
		this.isEssential = isEssential;
		this.isImported = isImported;
		this.tax = tax;
	}
    
    @Override
    public String toString() {
    	return String.format("Product[id=%d, name='%s', category='%s', price=%.2f, tax='%f']", 
    			id, name, productCategory, price, tax);
    }
}
