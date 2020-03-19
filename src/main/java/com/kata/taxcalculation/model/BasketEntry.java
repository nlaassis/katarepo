package com.kata.taxcalculation.model;

import com.kata.taxcalculation.domain.Product;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * DTO encapsulating products and prices
 * 
 * @author nordine.laassis
 *
 */
@Data
@ToString
public class BasketEntry {
    private Product item;
    private int itemCount;
    // Pht = Prix Hors Taxes
    private double PhtPrice;
    // Pttc = Prix Toutes Taxes Comprises
    private double PttcPrice;
    
    @Builder
    public BasketEntry(Product product, int itemCount) {
    	this.item = product;
    	this.itemCount = itemCount;
    }
}
