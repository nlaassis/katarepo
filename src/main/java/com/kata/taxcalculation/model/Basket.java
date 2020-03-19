package com.kata.taxcalculation.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

/**
 * Basket containing products
 * 
 * @author nordine.laassis
 *
 */
@Data
public class Basket {
    private List<BasketEntry> content = new ArrayList<BasketEntry>();
    
    private Double totalTtc;
    
    private Double totalHt;
    
    private Double totalTaxes;
    
    /**
     * ToString method to print the ticket
     */
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("*****************************************************************\n");
    	sb.append(content.stream().map((be) -> be.toString()).collect(Collectors.joining("\n")));
    	sb.append("\n\n\n");
    	sb.append("Montant des taxes : ").append(this.totalTaxes).append("\n");
    	sb.append("Total : ").append(this.totalTtc).append("\n\n");
    	sb.append("*****************************************************************");
    	return sb.toString();
    }
}
