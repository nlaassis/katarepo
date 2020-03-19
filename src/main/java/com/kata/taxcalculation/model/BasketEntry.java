package com.kata.taxcalculation.model;

import com.kata.taxcalculation.domain.Product;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BasketEntry {
    private Product item;
    private int itemCount;
    //private double tax;
    private double PhtPrice;
    private double PttcPrice;
    
    @Builder
    public BasketEntry(Product product, int itemCount) {
    	this.item = product;
    	this.itemCount = itemCount;
    }
}
